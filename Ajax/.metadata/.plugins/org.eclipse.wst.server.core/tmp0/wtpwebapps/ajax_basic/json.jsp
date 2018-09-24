<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>JOSN出现之前</title>
<script type="text/javascript">
window.onload = function() {
	var p = new Person("yeeku","male");
	alert(p.name + p.sex);
}
//定义一个函数作为构造器
function Person(name,sex) {
	this.name = name;
	this.sex = sex;
}
</script>
</head>
<body>
<h1>JSON出现之前</h1>
</body>
</html>