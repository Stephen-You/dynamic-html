package cn.ajax_basic.json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author Yorick
 * 2018年9月24日
 * theme:对比XML和JSON传输及js解析
 */
public class XMLServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/xml;charset=utf-8");
		String xml = "<students>" +
				"<student number='1001'>" +
				"<name>zhangSan</name>" +
				"<age>18</age>" + 
				"<sex>male</sex>" +
				"</student>" +
				"</students>";
		System.out.println(xml);
		System.out.println(req.getParameter("name"));
		resp.getWriter().print(xml);
	}
}
