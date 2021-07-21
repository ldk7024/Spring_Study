<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var ="cpath" value ="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
 <script type="text/javascript"> //자바 스크립트 
  function goWrite() {
   location.href = "${cpath}/boardForm.do"; // 게시판 글쓰기
} 
  </script>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body> 
<div class="container">
  <h2>대교의 편한 세상</h2>
  <div class="panel panel-default">
    <div class="panel-heading">BOARD LIST</div>
    <div class="panel-body">
        <table class="table"> <!-- bootstrap은 border을 주지않고 클래스를 주면됨 -->
        <tr>
           <td>번호</td>
           <td>제목</td>
           <td>조회수</td>
           <td>작성자</td>
           <td>작성일</td>
        </tr>
       <c:forEach var="vo" items="${list}">
         <tr>
           <td>${vo.idx}</td>
           <td>${vo.title}</td>
           <td>${vo.count}</td>
           <td>${vo.writer}</td>
           <td>${vo.indate}</td>
        </tr>
      </c:forEach>
      </table>
      <button class ="btn btn-info btn-sm" onclick="goWrite()">글쓰기</button>
    </div>
    <div class="panel-footer">빅데이터 그 자체 이대교</div>
  </div>
</div>
</body>
</html>