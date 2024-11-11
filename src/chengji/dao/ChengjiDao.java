package chengji.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chengji.bean.Chengji;
import chengji.util.DBConn;
import chengji.util.Fenye;
import chengji.util.Pager;


public class ChengjiDao {

	
	//插入纪录
	public void insertBean(Chengji bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "insert into t_Chengji(fenshu,kcid,userid) " +
					"values(?,?,?)";
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getFenshu());
			ps.setInt(2, bean.getKcid());
			ps.setInt(3, bean.getUserid());
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
	}
	
	//更新记录
	public void updateBean(Chengji bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "update  t_Chengji set fenshu=?,kcid=?,userid=?" +
					" where id= "+bean.getId();
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getFenshu());
			ps.setInt(2, bean.getKcid());
			ps.setInt(3, bean.getUserid());
			ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
	}
	
	//删除记录
	public void deleteBean(Chengji bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "delete from   t_Chengji  where id="+bean.getId();
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
	}
	

	//按查询条件查询列表信息（支持分页）
	@SuppressWarnings("unchecked")
	public Map<String,List<Chengji>> getList(int pagenum,int pagesize ,String url,String where){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Chengji> list = new ArrayList<Chengji>();
		try{
			String sql = "SELECT * from t_Chengji "+where ;  
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				Chengji bean = new Chengji();
				bean.setId(rs.getInt("id"));
				bean.setFenshu(rs.getString("fenshu"));
				bean.setKcid(rs.getInt("kcid"));
				bean.setUserid(rs.getInt("userid"));
				KcDao kcDao = new KcDao();
				bean.setKc(kcDao.selectBean("where id="+bean.getKcid()));
				UserDao userDao = new UserDao();
				bean.setUser(userDao.selectBean("where id="+bean.getUserid()));
				list.add(bean);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
		int currentpage = pagenum;
		Fenye pm = new Fenye(list, pagesize);
		
		List<Chengji> fenyelist = pm.getObjects(currentpage);
		int total = list.size();
		Map<String,List<Chengji>> map = new HashMap<String,List<Chengji>>();
		map.put(Pager.getPagerNormal(total, pagesize,
				currentpage, url, "共有" + total + "条记录"), fenyelist);
		String pagerinfo = map.keySet().iterator().next();
		List<Chengji> list2 = map.get(pagerinfo);
		if(list2==null){
			map.remove(pagerinfo);
			map.put(pagerinfo, list);
		}
			
		
		return map;

	}
	
	@SuppressWarnings("unchecked")
	public List<Chengji> getList(String where ){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Chengji> list = new ArrayList<Chengji>();
		try{
			String sql = "SELECT * from t_Chengji "+where ;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				Chengji bean = new Chengji();
				bean.setId(rs.getInt("id"));
				bean.setFenshu(rs.getString("fenshu"));
				bean.setKcid(rs.getInt("kcid"));
				bean.setUserid(rs.getInt("userid"));
				KcDao kcDao = new KcDao();
				bean.setKc(kcDao.selectBean("where id="+bean.getKcid()));
				UserDao userDao = new UserDao();
				bean.setUser(userDao.selectBean("where id="+bean.getUserid()));
				list.add(bean);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
		
		
		return list;

	}
	
	
	//按查询条件查询记录信息
	public Chengji selectBean(String where){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Chengji bean =null;
		try{
			String sql = "SELECT * from t_Chengji "+where;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				bean = new Chengji();
				bean.setId(rs.getInt("id"));
				bean.setFenshu(rs.getString("fenshu"));
				bean.setKcid(rs.getInt("kcid"));
				bean.setUserid(rs.getInt("userid"));
				KcDao kcDao = new KcDao();
				bean.setKc(kcDao.selectBean("where id="+bean.getKcid()));
				UserDao userDao = new UserDao();
				bean.setUser(userDao.selectBean("where id="+bean.getUserid()));
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
		return bean;
	}
	
	
	public int selectBeancount(String where){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try{
			String sql = "SELECT count(*) from t_Chengji "+where;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			if(rs!=null){
				count = rs.getInt(1);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
		return count;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
