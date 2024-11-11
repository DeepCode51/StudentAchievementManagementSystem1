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
import javax.servlet.http.HttpSession;

import chengji.bean.User;
import chengji.dao.BanjiDao;
import chengji.dao.UserDao;



//用户User相关请求Servlet
public class UserServlet extends HttpServlet {

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
		UserDao userDao = new UserDao();
		BanjiDao banjiDao = new BanjiDao();


		
		//首页登录
		if("login".equals(method)){
			//从jsp页面获取用户名和密码
			String username =  request.getParameter("username");
			String password =  request.getParameter("password");
			//查询用户名和密码是否匹配
			User bean = userDao.selectBean(" where username='"+username+"' and password ='"+password+"'  ");
			if(bean!=null){
				HttpSession session = request.getSession();
				session.setAttribute("manage", bean);
				
				writer.print("<script language='javascript'>alert('登录成功');window.location.href='"+basePath+"index.jsp'; </script>");
			}else{
				
				writer.print("<script  language='javascript'>alert('用户名或者密码错误');window.location.href='"+basePath+"login.jsp';</script>");
			}
		}
		
		//退出操作
		 else if("loginout".equals(method)){
			
			 HttpSession session  =request.getSession();
			 session.removeAttribute("manage");
			 writer.print("<script  language='javascript'>alert('退出成功');window.location.href='"+basePath+"login.jsp';</script>");

		}
		
		//跳转到修改密码页面
		else if("password".equals(method)){

				request.setAttribute("biaoti", "修改密码");
				request.setAttribute("url", "userServlet/password2");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/password.jsp");
				dispatcher.forward(request, response);
		}
			
			//修改密码操作
		else if("password2".equals(method)){
				
				//从JSP获取信息
				String password1 = request.getParameter("password1");
				String password2 = request.getParameter("password2");
				HttpSession session = request.getSession();
				User user = (User)session.getAttribute("manage");
				
				User u = userDao.selectBean(" where username='"+user.getUsername()+"' and password='"+password1+"'  ");
				if(u!=null){
					u.setPassword(password2);
					userDao.updateBean(u);
					writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"userServlet/password'; </script>");
				}else{
					writer.print("<script  language='javascript'>alert('操作失败，原密码错误！');window.location.href='"+basePath+"userServlet/password'; </script>");
				}
				
				
				
		}
		
		
		//管理员查看：用户信息列表
		else if("userlist".equals(method)){
			
			//定义跳转的地址
			url = "userServlet/userlist";
			
			//获取查询的信息
			String username = request.getParameter("username");	
            
			String truename = request.getParameter("truename");	
			
			//组装查询的SQL语句
			StringBuffer sb = new StringBuffer();
			sb.append(" where  ");
			
			if(username!=null&&!"".equals(username)){
							
				sb.append(" username like '%"+username+"%' ");
				sb.append(" and ");
				
				request.setAttribute("username", username);
			}
			
			if(truename!=null&&!"".equals(truename)){
				
				sb.append(" truename like '%"+truename+"%' ");
				sb.append(" and ");
				
				request.setAttribute("truename", truename);
			}

			sb.append(" role = '学生用户' order by id desc ");
			String where = sb.toString();

			//获取当前的页数
			if(request.getParameter("pagenum")!=null){
				pagenum = Integer.parseInt(request.getParameter("pagenum"));
			}

			//从数据库查询列表信息，带分页功能
			Map<String,List<User>> map = userDao.getList(pagenum,pagesize,url,where);
			String pagerinfo = map.keySet().iterator().next();
			List<User> list = map.get(pagerinfo);
			
			//返回给jsp页面的信息
			request.setAttribute("pagerinfo", pagerinfo);
			request.setAttribute("list", list);
			request.setAttribute("biaoti", "学生信息列表");
			request.setAttribute("url", "userServlet/userlist");
			request.setAttribute("url2", "userServlet/user");
			
			
			
			//定义跳转的地址
			RequestDispatcher dispatcher = request.getRequestDispatcher("/user/userlist.jsp");
			//跳转操作
			dispatcher.forward(request, response);
		}
		
		
		//跳转到添加学生信息页面
		else if("useradd".equals(method)){
			request.setAttribute("biaoti", "添加学生信息");
			request.setAttribute("banjilist", banjiDao.getList(""));
			request.setAttribute("url", "userServlet/useradd2");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/user/useradd.jsp");
			dispatcher.forward(request, response);
		}
		
		//添加学生信息操作
		else if("useradd2".equals(method)){

			//从JSP获取信息
			String username = request.getParameter("username");
			String truename = request.getParameter("truename");
			String phone = request.getParameter("phone");
			String sex = request.getParameter("sex");
			String banjiid = request.getParameter("banjiid");
			//查询该学生名是否已经注册
			User bean = userDao.selectBean(" where username='"+username+"' ");
			if(bean==null){
				 bean =  new User();
					
					//设置对象的属性
					bean.setUsername(username);
					bean.setTruename(truename);
				    bean.setBanjiid(Integer.parseInt(banjiid));
				    bean.setPhone(phone);
		            bean.setSex(sex);
					bean.setPassword("111111");
					bean.setRole("学生用户");
					
					
					//插入数据库
					userDao.insertBean(bean);
					//返回给JSP页面
					writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"userServlet/userlist'; </script>");
				
			}else{
				
				writer.print("<script  language='javascript'>alert('该用户名已经存在，请重新添加！');window.location.href='"+basePath+"userServlet/userlist';;</script>");
			}
			
			
		}
		
		
		
		//跳转到查看学生信息页面
		else if("usershow".equals(method)){
			request.setAttribute("biaoti", "查看学生信息");
			//通过ID获取对象
			String id = request.getParameter("id");
			User bean = userDao.selectBean(" where id= "+id);
			//把对象传给jsp页面
			request.setAttribute("bean", bean);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/user/usershow.jsp");
			dispatcher.forward(request, response);
		}
		
		
		
		
		//跳转到更新学生信息页面
		else if("userupdate".equals(method)){
			
			//通过ID获取对象
			String id = request.getParameter("id");
			User bean = userDao.selectBean(" where id= "+id);
			//把对象传给jsp页面
			request.setAttribute("bean", bean);
			request.setAttribute("banjilist", banjiDao.getList(""));
			request.setAttribute("biaoti", "更新学生信息");
			request.setAttribute("url", "userServlet/userupdate2?id="+bean.getId());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/user/userupdate.jsp");
			dispatcher.forward(request, response);
		}
		
		//更新学生信息操作
		else if("userupdate2".equals(method)){
			
			//从JSP获取信息
			String truename = request.getParameter("truename");
			String password = request.getParameter("password");
			String banjiid = request.getParameter("banjiid");
			String phone = request.getParameter("phone");
			String sex = request.getParameter("sex");
			//通过ID获取对象
			String id = request.getParameter("id");
			User bean = userDao.selectBean(" where id= "+id);
			//更新对象属性
			bean.setTruename(truename);
			bean.setPassword(password);
		    bean.setBanjiid(Integer.parseInt(banjiid));
		    bean.setPhone(phone);
            bean.setSex(sex);
			
			//更新操作;
			userDao.updateBean(bean);
			
			
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"userServlet/userlist'; </script>");
		}
		
		//学生角色：跳转到更新学生信息页面
		else if("userupdate3".equals(method)){
			
			//通过ID获取对象
			String id = request.getParameter("id");
			User bean = userDao.selectBean(" where id= "+id);
			//把对象传给jsp页面
			request.setAttribute("bean", bean);
			request.setAttribute("banjilist", banjiDao.getList(""));
			request.setAttribute("biaoti", "更新学生信息");
			request.setAttribute("url", "userServlet/userupdate4?id="+bean.getId());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/user/userupdate2.jsp");
			dispatcher.forward(request, response);
		}
		
		//更新学生信息操作
		else if("userupdate4".equals(method)){
			
			//从JSP获取信息
			
			String phone = request.getParameter("phone");
		
			//通过ID获取对象
			String id = request.getParameter("id");
			User bean = userDao.selectBean(" where id= "+id);
			//更新对象属性
	
		    bean.setPhone(phone);
		  
			
			//更新操作;
			userDao.updateBean(bean);
			
			
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"userServlet/userlist2'; </script>");
		}
		
		//删除学生信息操作
		else if("userdelete".equals(method)){
			//通过ID获取对象
			String id = request.getParameter("id");
			User bean = userDao.selectBean(" where id= "+id);
			//删除对象
			userDao.deleteBean(bean);
			
		
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"userServlet/userlist'; </script>");
		}
		
		
		
		//学生成绩管理
		else if("userlist2".equals(method)){
			
			//定义跳转的地址
			url = "userServlet/userlist2";
			
			//获取查询的信息
			String username = request.getParameter("username");	
            
			String truename = request.getParameter("truename");	
			
			//组装查询的SQL语句
			StringBuffer sb = new StringBuffer();
			sb.append(" where  ");
			
			if(username!=null&&!"".equals(username)){
							
				sb.append(" username like '%"+username+"%' ");
				sb.append(" and ");
				
				request.setAttribute("username", username);
			}
			
			if(truename!=null&&!"".equals(truename)){
				
				sb.append(" truename like '%"+truename+"%' ");
				sb.append(" and ");
				
				request.setAttribute("truename", truename);
			}

			sb.append(" role = '学生用户' order by id desc ");
			String where = sb.toString();

			//获取当前的页数
			if(request.getParameter("pagenum")!=null){
				pagenum = Integer.parseInt(request.getParameter("pagenum"));
			}

			//从数据库查询列表信息，带分页功能
			Map<String,List<User>> map = userDao.getList(pagenum,pagesize,url,where);
			String pagerinfo = map.keySet().iterator().next();
			List<User> list = map.get(pagerinfo);
			
			//返回给jsp页面的信息
			request.setAttribute("pagerinfo", pagerinfo);
			request.setAttribute("list", list);
			request.setAttribute("biaoti", "学生成绩管理");
			request.setAttribute("url", "userServlet/userlist2");
			request.setAttribute("url2", "userServlet/user");
			
			
			
			//定义跳转的地址
			RequestDispatcher dispatcher = request.getRequestDispatcher("/user/userlist2.jsp");
			//跳转操作
			dispatcher.forward(request, response);
		}
		
		
		
		//个人信息查询
		else if("userlist3".equals(method)){
			
			//定义跳转的地址
			url = "userServlet/userlist3";
			
			
			
			//组装查询的SQL语句
			StringBuffer sb = new StringBuffer();
			sb.append(" where  ");
			
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("manage");

			sb.append(" id="+user.getId()+" order by id desc ");
			String where = sb.toString();

			//获取当前的页数
			if(request.getParameter("pagenum")!=null){
				pagenum = Integer.parseInt(request.getParameter("pagenum"));
			}

			//从数据库查询列表信息，带分页功能
			Map<String,List<User>> map = userDao.getList(pagenum,pagesize,url,where);
			String pagerinfo = map.keySet().iterator().next();
			List<User> list = map.get(pagerinfo);
			
			//返回给jsp页面的信息
			request.setAttribute("pagerinfo", pagerinfo);
			request.setAttribute("list", list);
			request.setAttribute("biaoti", "个人信息查询");
			request.setAttribute("url", "userServlet/userlist3");
			request.setAttribute("url2", "userServlet/user");
			
			
			
			//定义跳转的地址
			RequestDispatcher dispatcher = request.getRequestDispatcher("/user/userlist3.jsp");
			//跳转操作
			dispatcher.forward(request, response);
		}
		
	}

}
