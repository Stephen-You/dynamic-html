package cn.ajax_basic.get;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author Yorick
 * 2018年9月22日
 * theme:简易版股票交易所
 */
public class PriceServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		Random rand = new Random(System.currentTimeMillis());
		System.out.println("PriceServlet");
		resp.getWriter().println(rand.nextInt(10) + "$" + rand.nextInt(10) + "$" + rand.nextInt(10));
	}
}
