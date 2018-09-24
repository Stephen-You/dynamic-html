package cn.ajax_basic.xstream;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sun.javafx.scene.paint.GradientUtils.Point;
import com.thoughtworks.xstream.XStream;

public class XStreamTest {
	//返回javabean集合
	public List<Province> getProvinceList(){
		Province p1 = new Province();
		p1.setName("北京");
		p1.addCity(new City("东城区", "DongChengQu"));
		p1.addCity(new City("昌平区", "ChangPingQu"));
		
		Province p2 = new Province();
		p2.setName("辽宁");
		p2.addCity(new City("沈阳", "shenYang"));
		p2.addCity(new City("葫芦岛", "huLuDao"));
		
		//创建集合存放JavaBean对象
		List<Province> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		
		return list;
	}
	/**
<list> --> List类型显示list
  <cn.ajax_basic.xstream.Province> --> javabean的类型为Province，它元素的名称为类的完整名
    <name>北京</name> --> javabean的属性名
    <cities> --> javabean的属性名
      <cn.ajax_basic.xstream.City> --> 类名
        <name>东城区</name> --> 属性名
        <description>DongChengQu</description> --> 属性名
      </cn.ajax_basic.xstream.City>
      <cn.ajax_basic.xstream.City>
        <name>昌平区</name>
        <description>ChangPingQu</description>
      </cn.ajax_basic.xstream.City>
    </cities>
  </cn.ajax_basic.xstream.Province>
  <cn.ajax_basic.xstream.Province>
    <name>辽宁</name>
    <cities>
      <cn.ajax_basic.xstream.City>
        <name>沈阳</name>
        <description>shenYang</description>
      </cn.ajax_basic.xstream.City>
      <cn.ajax_basic.xstream.City>
        <name>葫芦岛</name>
        <description>huLuDao</description>
      </cn.ajax_basic.xstream.City>
    </cities>
  </cn.ajax_basic.xstream.Province>
</list>
	 */
	@Test
	public void listToXML() {
		List<Province> proList = getProvinceList();
		XStream xStream = new XStream();
		String xml = xStream.toXML(proList);
		System.out.println(xml);
	}
	/**
	 * alias别名用法:生成的XML中，与类名对应的元素名称包含了包名部分，这很不好看！
	 * 若想自定义生成的元素名称，需要使用XStream为类名提供别名
	 */
	@Test
	public void alias() {
		List<Province> proList = getProvinceList();
		XStream xStream = new XStream();
		xStream.alias("china", List.class);
		xStream.alias("province", Province.class);
		xStream.alias("city", City.class);
		String xml = xStream.toXML(proList);
		System.out.println(xml);
	}
	/**
	 * 让类的成员字段以元素的属性出现
	 */
	@Test
	public void toXMLAttribute() {
		List<Province> proList = getProvinceList();
		XStream xStream = new XStream();
		xStream.useAttributeFor(Province.class, "name");
		System.out.println(xStream.toXML(proList));
	}
	/**
	 * 有时候我们并不希望xml中完全显示所有的类字段,如province类中cities是一个城市的集合,这是JavaBean中的
	 * 写法,但是在xml中,我们往往希望显示的直接就是城市字段
	 */
	@Test
	public void removeField() {
		List<Province> proList = getProvinceList();
		XStream xStream = new XStream();
		xStream.addImplicitCollection(Province.class, "cities");
		System.out.println(xStream.toXML(proList));
	}
	/**
	 * 不希望某些类的成员在对应的XML文档中出现
	 */
	@Test
	public void omitField() {
		List<Province> proList = getProvinceList();
		XStream xStream = new XStream();
		xStream.omitField(City.class, "description");
		System.out.println(xStream.toXML(proList));
	}
	
}
