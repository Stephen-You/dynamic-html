package cn.ajax_basic.json.objectToJSON;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
/**
 * 
 * @author Yorick
 * 2018年9月24日
 * theme:对象和JSON字符串之间的转变练习
 */
public class JSONDemo {

	@Test
	public void testJSONObject() {
		JSONObject jo = new JSONObject();
		jo.put("name", "Yorick");
		jo.put("age", 18);
		jo.put("sex", "male");
		System.out.println(jo.toString());
	}
	@Test
	public void fromObject() {
		JSONObject jo = JSONObject.fromObject(new Person("Yorick", 19, "male"));
		System.out.println(jo.toString());
	}
	@Test
	public void fromObjectMap() {
		Map<String, String> map = new HashMap<>();
		map.put("name", "aa");
		map.put("sex", "female");
		JSONObject jo = JSONObject.fromObject(map);
		System.out.println(jo.toString());
	}
	@Test
	public void xmlToJson() {
		String xml = "<person><name>zhaoLiu</name><age>59</age><sex>female</sex></person>";
		XMLSerializer xs = new XMLSerializer();
		JSONObject jo = (JSONObject) xs.read(xml);
		System.out.println(jo.toString());
	}
}
