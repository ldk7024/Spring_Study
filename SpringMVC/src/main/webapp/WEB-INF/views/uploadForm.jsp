<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var ="cpath" value ="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${cpath}/uploadFormAction.do" method="post" enctype="multipart/form-data">
	<input type="text" name="title">
	<input type = "file" name="uploadFile" multiple="multiple">
	<button>파일업로드</button>
</form>
</body>
</html>
