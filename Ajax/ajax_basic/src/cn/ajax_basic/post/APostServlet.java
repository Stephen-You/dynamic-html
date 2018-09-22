package cn.ajax_basic.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author Yorick
 * 2018年9月22日
 * theme:进行Ajax的基础笔记总结所做简单练习--Ajax_post请求
 */
public class APostServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		String username = req.getParameter("username");
		System.out.println(req.getParameter("a"));
		System.out.println("POST： Hello AJAX!" + username);
		resp.getWriter().print(username);
	}

}
