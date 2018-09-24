package cn.ajax_basic.get;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
/**
 * 
 * @author Yorick
 * 2018年9月23日
 * theme:省市联动
 */
public class ProvinceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置响应类型和编码格式
		resp.setContentType("text/html;charset=utf-8");
		//使用DOM4j解析xml文档
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("china.xml");
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(input);
			// xpath查询所有province元素的name属性,得到一个由属性名和属性值构成的属性的集合
			List<Attribute> provinceNameAttributeList = document.selectNodes("//province/@name");
			// 用来装载所有name属性值
			List<String> provinceNames = new ArrayList<>();
			// 遍历每个属性，获取属性名称，添加到list中
			for (Attribute proAttr : provinceNameAttributeList) {
				provinceNames.add(proAttr.getValue());
			}
			System.out.println(provinceNames);
			// 把list转换成字符串
			String str = provinceNames.toString();
			// 把字符串前后中的[]去除发送给客户端
			resp.getWriter().print(str.substring(1, str.length()-1));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

}
