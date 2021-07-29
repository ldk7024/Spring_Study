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
<script type="text/javascript">
	function goDown(uploadPath,uuid,fileName) {
		var fileCallPath = uploadPath+"\\"+uuid+"_"+fileName;
		location.href = '${cpath}/download.do?fileName='+encodeURIComponent(fileCallPath);
/* 		alert(encodeURIComponent(fileCallPath)); */
	}
</script>
</head>
<body>
	<c:forEach var="vo" items="${list}">
	<li><a href="javascript:goDown('${vo.uploadPath}','${vo.uuid}','${vo.fileName}')">${vo.fileName}</a></li>
	</c:forEach>
</body>
</html>
