<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'ajax1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
// 创建异步对象
function createXMLHttpRequest() {
	try {
		return new XMLHttpRequest();//大多数浏览器
	} catch (e) {
		try {
			return ActvieXObject("Msxml2.XMLHTTP");//IE6.0
		} catch (e) {
			try {
				return ActvieXObject("Microsoft.XMLHTTP");//IE5.5及更早版本	
			} catch (e) {
				alert("哥们儿，您用的是什么浏览器啊？");
				throw e;
			}
		}
	}
}

window.onload = function() {//文档加载完毕后执行
	var btn = document.getElementById("btn");
	btn.onclick = function() {//给按钮的点击事件注册监听
		
		var xmlHttp = createXMLHttpRequest();
		
		xmlHttp.open("GET", "<c:url value='/BServlet'/>", true);
		
		xmlHttp.send(null);//GET请求没有请求体，但也要给出null，不然FireFox可能会不能发送！
		
		xmlHttp.onreadystatechange = function() {//当xmlHttp的状态发生变化时执行
			// 双重判断：xmlHttp的状态为4（服务器响应结束），以及服务器响应的状态码为200（响应成功）
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				// 获取服务器的响应结果（xml）
				var doc = xmlHttp.responseXML;
				// 查询文档下名为student的所有元素，得到数组，再取下标0元素
				var stuEle = doc.getElementsByTagName("student")[0];
				var number = stuEle.getAttribute("name");//获取元素名为number的属性值
				var name;
				var age;
				var sex;
				
				// 处理浏览器的差异----window.addEventListener为true说明不是IE
				if(window.addEventListener) {
					name = ele.getElementsByTagName("name")[0].textContent;//其他浏览器
				} else {
					name = ele.getElementsByTagName("name")[0].text;//IE支持
				}
				if(window.addEventListener) {
					age = ele.getElementsByTagName("age")[0].textContent;//其他浏览器
				} else {
					age = ele.getElementsByTagName("age")[0].text;//IE支持
				}
				if(window.addEventListener) {
					sex = ele.getElementsByTagName("sex")[0].textContent;//其他浏览器
				} else {
					sex = ele.getElementsByTagName("sex")[0].text;//IE支持
				}

				var text = number + ", " + name + ", " + age + ", " + sex;
				document.getElementById("h1").innerHTML = text;
			}
		};
	};
};
</script>
  </head>
  
  <body>
<button id="btn">点击这里</button>
<h1 id="h1"></h1>
  </body>
</html>
