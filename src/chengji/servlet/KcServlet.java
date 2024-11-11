package chengji.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chengji.bean.Kc;
import chengji.dao.KcDao;




//课程Kc相关请求Servlet
     public class KcServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	
	public void destroy() {
		
		super.destroy();
	}


	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//设置获取的参数的编码格式
		request.setCharacterEncoding("utf-8");
		
		//获取绝对地址
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		//设置响应输出的字符串格式
		response.setCharacterEncoding("utf-8");response.setContentType("text/html; charset=utf-8");
		//获取输出对象
		PrintWriter writer = response.getWriter();
		//获取页面请求地址
		String uri = request.getRequestURI();	
		String[] s = uri.split("/");
		String method = s[3];
		
		//初始化跳转的地址
		String url ="";
		
		int pagenum =  1;//当前页
		int pagesize = 15;//每页显示的数量
		
		
		//初始化调用的数据库操作对象
		KcDao kcDao = new KcDao();
		

		
	
		
		
		//课程信息列表
		if("kclist".equals(method)){
			
			//定义跳转的地址
			url = "kcServlet/kclist";
			
			//获取查询的信息
			String kcname = request.getParameter("kcname");	
            
		
			
			//组装查询的SQL语句
			StringBuffer sb = new StringBuffer();
			sb.append(" where  ");
			
			if(kcname!=null&&!"".equals(kcname)){
							
				sb.append(" kcname like '%"+kcname+"%' ");
				sb.append(" and ");
				
				request.setAttribute("kcname", kcname);
			}
			
			

			sb.append(" 1=1 order by id desc ");
			String where = sb.toString();

			//获取当前的页数
			if(request.getParameter("pagenum")!=null){
				pagenum = Integer.parseInt(request.getParameter("pagenum"));
			}

			//从数据库查询列表信息，带分页功能
			Map<String,List<Kc>> map = kcDao.getList(pagenum,pagesize,url,where);
			String pagerinfo = map.keySet().iterator().next();
			List<Kc> list = map.get(pagerinfo);
			
			//返回给jsp页面的信息
			request.setAttribute("pagerinfo", pagerinfo);
			request.setAttribute("list", list);
			request.setAttribute("biaoti2", "课程信息列表");
			request.setAttribute("url", "kcServlet/kclist");
			request.setAttribute("url2", "kcServlet/kc");
			
			
			
			//定义跳转的地址
			RequestDispatcher dispatcher = request.getRequestDispatcher("/kc/kclist.jsp");
			//跳转操作
			dispatcher.forward(request, response);
		}
		
		
		
		
		
		//用户查看课程信息列表
		else  if("kclist2".equals(method)){
			
			//定义跳转的地址
			url = "kcServlet/kclist2";
			
			//获取查询的信息
			String biaoti = request.getParameter("biaoti");	
            
		
			
			//组装查询的SQL语句
			StringBuffer sb = new StringBuffer();
			sb.append(" where  ");
			
			if(biaoti!=null&&!"".equals(biaoti)){
							
				sb.append(" biaoti like '%"+biaoti+"%' ");
				sb.append(" and ");
				
				request.setAttribute("biaoti", biaoti);
			}
			
			

			sb.append(" 1=1 order by id desc ");
			String where = sb.toString();

			//获取当前的页数
			if(request.getParameter("pagenum")!=null){
				pagenum = Integer.parseInt(request.getParameter("pagenum"));
			}

			//从数据库查询列表信息，带分页功能
			Map<String,List<Kc>> map = kcDao.getList(pagenum,pagesize,url,where);
			String pagerinfo = map.keySet().iterator().next();
			List<Kc> list = map.get(pagerinfo);
			
			//返回给jsp页面的信息
			request.setAttribute("pagerinfo", pagerinfo);
			request.setAttribute("list", list);
			request.setAttribute("biaoti2", "查看课程列表");
			request.setAttribute("url", "kcServlet/kclist");
			request.setAttribute("url2", "kcServlet/kc");
			
			
			
			//定义跳转的地址
			RequestDispatcher dispatcher = request.getRequestDispatcher("/kc/kclist2.jsp");
			//跳转操作
			dispatcher.forward(request, response);
		}
		
		
		//跳转到添加课程信息页面
		else if("kcadd".equals(method)){
			request.setAttribute("biaoti", "添加课程信息");
			request.setAttribute("url", "kcServlet/kcadd2");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/kc/kcadd.jsp");
			dispatcher.forward(request, response);
		}
		
		//添加课程信息操作
		else if("kcadd2".equals(method)){

			//从JSP获取信息
			String kcname = request.getParameter("kcname");
			Kc bean= kcDao.selectBean("where kcname='"+kcname+"'");
			if(bean==null){
				bean = new Kc();
				//设置对象的属性
				bean.setKcname(kcname);
			
				//插入数据库
				kcDao.insertBean(bean);
				//返回给JSP页面
				writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"kcServlet/kclist'; </script>");
			}else{
				writer.print("<script  language='javascript'>alert('该课程已经存在,请重新添加');window.location.href='"+basePath+"kcServlet/kclist'; </script>");
			}
			
			}
			
			
	
			
		
		
		
		//跳转到查看课程信息页面
		else if("kcshow".equals(method)){
			request.setAttribute("biaoti", "查看课程信息");
			//通过ID获取对象
			String id = request.getParameter("id");
			Kc bean = kcDao.selectBean(" where id= "+id);
			//把对象传给jsp页面
			request.setAttribute("bean", bean);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/kc/kcshow.jsp");
			dispatcher.forward(request, response);
		}
			
		
		
		//删除课程信息操作
		else if("kcdelete".equals(method)){
			//通过ID获取对象
			String id = request.getParameter("id");
			Kc bean = kcDao.selectBean(" where id= "+id);
			//删除对象
			kcDao.deleteBean(bean);
			
		
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"kcServlet/kclist'; </script>");
		}
		
	}

}
