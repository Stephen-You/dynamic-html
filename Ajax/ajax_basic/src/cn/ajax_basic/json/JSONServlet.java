package cn.ajax_basic.json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author Yorick
 * 2018年9月25日
 * theme:发送json字符串集js解析
 */
public class JSONServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String json = "{\"students\":[{\"number\":\"1001\",\"name\":\"zhangSan\",\"age\":18,\"sex\":\"male\"}]}";
		System.out.println(req.getParameter("a"));
		resp.getWriter().print(json);
	}
}
