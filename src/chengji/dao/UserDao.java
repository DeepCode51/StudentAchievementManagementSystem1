package chengji.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chengji.bean.User;
import chengji.util.DBConn;
import chengji.util.Fenye;
import chengji.util.Pager;


public class UserDao {

	
	//插入纪录
	public void insertBean(User bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "insert into t_User(username,truename,sex,password,phone,role,banjiid) " +
					"values(?,?,?,?,?,?,?)";
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getUsername());
			ps.setString(2, bean.getTruename());
			ps.setString(3, bean.getSex());
			ps.setString(4, bean.getPassword());
			ps.setString(5, bean.getPhone());
			ps.setString(6, bean.getRole());
			ps.setInt(7, bean.getKcid());
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
	}
	
	//更新记录
	public void updateBean(User bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "update  t_User set username=?,truename=?,sex=?,password=?,phone=?,role=?,banjiid=?" +
					" where id= "+bean.getId();
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getUsername());
			ps.setString(2, bean.getTruename());
			ps.setString(3, bean.getSex());
			ps.setString(4, bean.getPassword());
			ps.setString(5, bean.getPhone());
			ps.setString(6, bean.getRole());
			ps.setInt(7, bean.getKcid());
			ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
	}
	
	//删除记录
	public void deleteBean(User bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "delete from   t_User  where id="+bean.getId();
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
	public Map<String,List<User>> getList(int pagenum,int pagesize ,String url,String where){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		try{
			String sql = "SELECT * from t_User "+where ;  
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				User bean = new User();
				bean.setId(rs.getInt("id"));
				bean.setUsername(rs.getString("username"));
				bean.setTruename(rs.getString("truename"));
				bean.setSex(rs.getString("sex"));
				bean.setPassword(rs.getString("password"));
				bean.setPhone(rs.getString("phone"));
				bean.setRole(rs.getString("role"));
				bean.setKcid(rs.getInt("banjiid"));
				BanjiDao banjiDao = new BanjiDao();
			    bean.setBanji(banjiDao.selectBean("where id="+bean.getBanjiid()));
				list.add(bean);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
		int currentpage = pagenum;
		Fenye pm = new Fenye(list, pagesize);
		
		List<User> fenyelist = pm.getObjects(currentpage);
		int total = list.size();
		Map<String,List<User>> map = new HashMap<String,List<User>>();
		map.put(Pager.getPagerNormal(total, pagesize,
				currentpage, url, "共有" + total + "条记录"), fenyelist);
		String pagerinfo = map.keySet().iterator().next();
		List<User> list2 = map.get(pagerinfo);
		if(list2==null){
			map.remove(pagerinfo);
			map.put(pagerinfo, list);
		}
			
		
		return map;

	}
	
	@SuppressWarnings("unchecked")
	public List<User> getList(String where ){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		try{
			String sql = "SELECT * from t_User "+where ;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				User bean = new User();
				bean.setId(rs.getInt("id"));
				bean.setUsername(rs.getString("username"));
				bean.setTruename(rs.getString("truename"));
				bean.setSex(rs.getString("sex"));
				bean.setPassword(rs.getString("password"));
				bean.setPhone(rs.getString("phone"));
				bean.setRole(rs.getString("role"));
				bean.setBanjiid(rs.getInt("banjiid"));
				BanjiDao banjiDao = new BanjiDao();
			    bean.setBanji(banjiDao.selectBean("where id="+bean.getBanjiid()));
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
	public User selectBean(String where){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User bean =null;
		try{
			String sql = "SELECT * from t_User "+where;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				bean = new User();
				bean.setId(rs.getInt("id"));
				bean.setUsername(rs.getString("username"));
				bean.setTruename(rs.getString("truename"));
				bean.setSex(rs.getString("sex"));
				bean.setPassword(rs.getString("password"));
				bean.setPhone(rs.getString("phone"));
				bean.setRole(rs.getString("role"));
				bean.setBanjiid(rs.getInt("banjiid"));
				BanjiDao banjiDao = new BanjiDao();
			    bean.setBanji(banjiDao.selectBean("where id="+bean.getBanjiid()));
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
			String sql = "SELECT count(*) from t_User "+where;
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
