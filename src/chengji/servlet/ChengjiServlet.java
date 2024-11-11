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

import chengji.bean.Chengji;
import chengji.bean.User;
import chengji.dao.ChengjiDao;
import chengji.dao.KcDao;

//成绩Chengji相关请求Servlet
public class ChengjiServlet extends HttpServlet {

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

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 设置获取的参数的编码格式
		request.setCharacterEncoding("utf-8");

		// 获取绝对地址
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		// 设置响应输出的字符串格式
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		// 获取输出对象
		PrintWriter writer = response.getWriter();
		// 获取页面请求地址
		String uri = request.getRequestURI();
		String[] s = uri.split("/");
		String method = s[3];

		// 初始化跳转的地址
		String url = "";

		int pagenum = 1;// 当前页
		int pagesize = 15;// 每页显示的数量

		// 初始化调用的数据库操作对象
		ChengjiDao chengjiDao = new ChengjiDao();
		KcDao kcDao = new KcDao();

		// 管理员查看：成绩信息列表
		if ("chengjilist".equals(method)) {

			// 定义跳转的地址
			url = "chengjiServlet/chengjilist";

			// 获取查询的信息

			String userid = request.getParameter("userid");

			request.setAttribute("userid", userid);

			// 组装查询的SQL语句
			StringBuffer sb = new StringBuffer();
			sb.append(" where  ");

			sb.append(" userid=" + userid + " order by id desc ");
			String where = sb.toString();

			// 获取当前的页数
			if (request.getParameter("pagenum") != null) {
				pagenum = Integer.parseInt(request.getParameter("pagenum"));
			}

			// 从数据库查询列表信息，带分页功能
			Map<String, List<Chengji>> map = chengjiDao.getList(pagenum,
					pagesize, url, where);
			String pagerinfo = map.keySet().iterator().next();
			List<Chengji> list = map.get(pagerinfo);

			// 返回给jsp页面的信息
			request.setAttribute("pagerinfo", pagerinfo);
			request.setAttribute("list", list);
			request.setAttribute("biaoti", "成绩信息列表");
			request.setAttribute("url", "chengjiServlet/chengjilist");
			request.setAttribute("url2", "chengjiServlet/chengji");

			// 定义跳转的地址
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/chengji/chengjilist.jsp");
			// 跳转操作
			dispatcher.forward(request, response);
		}

		// 跳转到添加成绩信息页面
		else if ("chengjiadd".equals(method)) {
			request.setAttribute("biaoti", "添加成绩信息");
			String userid = request.getParameter("userid");
			request.setAttribute("kclist", kcDao.getList(""));
			request.setAttribute("url", "chengjiServlet/chengjiadd2?userid="
					+ userid);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/chengji/chengjiadd.jsp");
			dispatcher.forward(request, response);
		}

		// 添加成绩信息操作
		else if ("chengjiadd2".equals(method)) {

			// 从JSP获取信息
			String fenshu = request.getParameter("fenshu");
			String userid = request.getParameter("userid");
			String kcid = request.getParameter("kcid");
			Chengji bean = chengjiDao.selectBean("where kcid=" + kcid + " and userid="+userid+"");
			if (bean == null) {
				bean = new Chengji();

				// 设置对象的属性
				bean.setFenshu(fenshu);
				bean.setKcid(Integer.parseInt(kcid));
				bean.setUserid(Integer.parseInt(userid));

				// 插入数据库
				chengjiDao.insertBean(bean);
				// 返回给JSP页面
				writer
						.print("<script  language='javascript'>alert('操作成功');window.location.href='"
								+ basePath
								+ "chengjiServlet/chengjilist?userid="
								+ userid
								+ "'; </script>");
			} else {
				writer
						.print("<script  language='javascript'>alert('该课程的分数已经添加,请不要重复添加');window.location.href='"
								+ basePath
								+ "chengjiServlet/chengjilist?userid="
								+ userid
								+ "'; </script>");

			}

		}

		// 查看成绩列表
		else if ("chengjishow".equals(method)) {
			request.setAttribute("biaoti", "查看成绩信息");
			// 通过ID获取对象
			String id = request.getParameter("id");
			Chengji bean = chengjiDao.selectBean(" where id= " + id);
			// 把对象传给jsp页面
			request.setAttribute("bean", bean);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/chengji/chengjishow.jsp");
			dispatcher.forward(request, response);
		}

		// 跳转到更新成绩信息页面
		else if ("chengjiupdate".equals(method)) {

			// 通过ID获取对象
			String id = request.getParameter("id");
			Chengji bean = chengjiDao.selectBean(" where id= " + id);
			// 把对象传给jsp页面
			request.setAttribute("bean", bean);
			request.setAttribute("biaoti", "更新成绩信息");
			request.setAttribute("url", "chengjiServlet/chengjiupdate2?id="
					+ bean.getId());
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/chengji/chengjiupdate.jsp");
			dispatcher.forward(request, response);
		}

		// 更新成绩信息操作
		else if ("chengjiupdate2".equals(method)) {

			// 从JSP获取信息
			String fenshu = request.getParameter("fenshu");
			// 通过ID获取对象
			String id = request.getParameter("id");
			Chengji bean = chengjiDao.selectBean(" where id= " + id);
			// 更新对象属性
			bean.setFenshu(fenshu);

			// 更新操作;
			chengjiDao.updateBean(bean);

			int userid = bean.getUserid();

			writer
					.print("<script  language='javascript'>alert('操作成功');window.location.href='"
							+ basePath
							+ "chengjiServlet/chengjilist?userid="
							+ userid + "'; </script>");
		}

		// 删除班级信息操作
		else if ("chengjidelete".equals(method)) {
			// 通过ID获取对象
			String id = request.getParameter("id");
			Chengji bean = chengjiDao.selectBean(" where id= " + id);

			int userid = bean.getUserid();

			// 删除对象
			chengjiDao.deleteBean(bean);

			writer
					.print("<script  language='javascript'>alert('操作成功');window.location.href='"
							+ basePath
							+ "chengjiServlet/chengjilist?userid="
							+ userid + "'; </script>");
		}
		
		
		//个人成绩查询
		if ("chengjilist2".equals(method)) {

			// 定义跳转的地址
			url = "chengjiServlet/chengjilist2";

			// 获取查询的信息
			
			

			// 组装查询的SQL语句
			StringBuffer sb = new StringBuffer();
			sb.append(" where  ");
			
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("manage");
			
			request.setAttribute("userid", user.getId());

			sb.append(" userid=" + user.getId() + " order by id desc ");
			String where = sb.toString();

			// 获取当前的页数
			if (request.getParameter("pagenum") != null) {
				pagenum = Integer.parseInt(request.getParameter("pagenum"));
			}

			// 从数据库查询列表信息，带分页功能
			Map<String, List<Chengji>> map = chengjiDao.getList(pagenum,
					pagesize, url, where);
			String pagerinfo = map.keySet().iterator().next();
			List<Chengji> list = map.get(pagerinfo);

			// 返回给jsp页面的信息
			request.setAttribute("pagerinfo", pagerinfo);
			request.setAttribute("list", list);
			request.setAttribute("biaoti", "个人成绩查询");
			request.setAttribute("url", "chengjiServlet/chengjilist2");
			request.setAttribute("url2", "chengjiServlet/chengji");

			// 定义跳转的地址
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/chengji/chengjilist2.jsp");
			// 跳转操作
			dispatcher.forward(request, response);
		}

	}

}
