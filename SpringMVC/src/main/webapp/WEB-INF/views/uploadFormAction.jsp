<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var ="cpath" value ="${pageContext.request.contextPath}"/>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>굿</title>
</head>
<body>
	<c:forEach var="vo" items="${list}">
	<li><a href="${cpath}/download.do?uploadPath=${fn:replace(vo.uploadPath,'\\','-')}&uuid=${vo.uuid}&fileName=${vo.fileName}">${vo.fileName }</a></li>
	</c:forEach>
</body>
</html>
