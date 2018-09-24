<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>省市联动</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
var xmlHttp;
function createXMLHttpRequest() {
	if(window.XMLHttpRequest){
		//适用于DOM2浏览器
		xmlHttp = new XMLHttpRequest();
	}else if (window.ActiveXObject) {
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			// 适用于IE5.5，以及IE更早版本
			try{
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e){
                 alert("对不起您使用的浏览器版本暂不支持Ajax，请您更换浏览器重试，或升级浏览器！");
                 throw e;
			}
		}
	}
}
//文档加载完成后,加载所有的省份信息
window.onload = function () {
	createXMLHttpRequest();
	xmlHttp.open("GET","<c:url value = '/ProvinceServlet'/>",true);
	xmlHttp.send(null);
	xmlHttp.onreadystatechange = province;
}
//本方法获取服务器响应的所有省份的名称
function province() {
	if(xmlHttp.readyState == 4){
		if(xmlHttp.status == 200){
			// 把服务器响应的省份名称，使用逗号分割成字符串数组
			var provinceNameArray = xmlHttp.responseText.split(",");
			// 遍历每个省份名称，使用每个省份名称创建<option>元素，添加到province的<select>中
			for (var i = 0; i < provinceNameArray.length; i++) {
				//alert("-"+provinceNameArray[i]+"-");
				addProvinceOption(provinceNameArray[i]);
			}
			// 为province的<select>元素添加onchange事件监听
			document.getElementById("province").onchange = loadCities;
		}else {
			window.alert("你请求的页面有异常,服务器不能正确处理!");
		}
	}
}
//使用proName创建<option>元素添加到<select>元素中
function addProvinceOption(proName) {
	var option = document.createElement("option");//创建option节点
	var text = document.createTextNode(proName);//使用省份名称创建文本节点
	option.appendChild(text);//把省份名称的文本节点添加到<option>元素中
	option.setAttribute("value",proName);//使用省份名称来设置<option>元素的value属性
	document.getElementById("province").appendChild(option);//把<option>元素添加到<select>元素中　
}
//本函数在province的<select>元素发送变化时执行！
//本函数会使用当前选中的省份名称为参数，向服务器发送请求，获取当前省份下的所有城市！
function loadCities() {
	var proName = this.value;//获取<select>选择的省份名称
	createXMLHttpRequest();
	xmlHttp.open("POST", "<c:url value='/CityServlet'/>", true);
	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	alert(proName);
	alert(xmlHttp);
	xmlHttp.send("pname=" + proName);//把下拉列表中选择的值发送给服务器！
	xmlHttp.onreadystatechange = cities;
}
//获取响应的所有城市
function cities() {
	if(xmlHttp.readyState == 4){
		if (xmlHttp.status == 200) {
			var doc = xmlHttp.responseXML;//获取服务器响应的xml文档
			// 获取文档中所有city元素
			var cityElementList = doc.getElementsByTagName("city");
			// 获取html元素：city的<select>
			var citySelect = document.getElementById("city");
			// 删除city的<select>元素的所有子元素
			removeChildNodes(citySelect);
			// 创建<option>元素，指定文本内容为“请选择”
			var qxzOption = document.createElement("option");
			var textNode = document.createTextNode("==请选择==");
			qxzOption.appendChild(textNode);
			// 把"请选择"这个<option>添加到<select>元素中
			citySelect.appendChild(qxzOption);
			
			// 循环遍历每个服务器端响应的每个<city>元素
			for (var i = 0; i < cityElementList.length; i++) {
				var cityEle = cityElementList[i];
				var cityName = null;
				// 获取<city>元素的文本内容！处理浏览器差异！
				if(window.addEventListener) {
					cityName = cityEle.textContent;
				} else {
					cityName = cityEle.text;
				}
				// 使用城市名称创建<option>，并添加到<select>元素中
				addCityOption(cityName);
			}
		} else {
			window.alert("你请求的页面有异常,服务器不能正确处理!!");
		}
	}
}
//删除city的<select>元素的所有子元素
function removeChildNodes(citySelect) {
	var nodes = citySelect.childNodes;//获取当前元素的所有子元素集合
	while (nodes.length > 0) {//遍历所有的子元素
		citySelect.removeChild(nodes[0]);//删除子元素	
	}
}
//本函数用来创建城市的<option>，并添加到<select>元素中
function addCityOption(cityName) {
	var citySelect = document.getElementById("city");//获取id为city的<select>
	var cityOption = document.createElement("option");//创建option元素
	var cityTextNode = document.createElement(cityName);//使用cityName创建文本节点
	cityOption.appendChild(cityTextNode);
	cityOption.setAttribute("value",cityName);//设置option的属性
	citySelect.appendChild(cityOption);//将option节点添加到select节点
}
</script>
  </head>
  
  <body>
<h1>省市联动</h1>
<select name="province" id="province">
  <option>===请选择省===</option>
</select>
　　　
<select name="city" id="city">
  <option>===请选择市===</option>
</select>
  </body>
</html>
