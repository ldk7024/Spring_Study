<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var ="cpath" value ="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  function goDel(idx) {
	location.href = "${cpath}/boardDelete.do?idx="+idx;
}
  </script>
</head>
<body>
 
<div class="container">
  <h2>Spring MVC BOARD</h2>
  <div class="panel panel-default">
    <div class="panel-heading">BOARD VIEW</div>
    <div class="panel-body">
    <form id="frm" method="post" action="${cpath}/boardUpdate.do">
    	<input type ="hidden" name="idx" value="${vo.idx}">
               <div class="form-group">
                  <label>제목: </label> 
                  <input type="text" class="form-control" id="title" name="title" value="${vo.title}">
               </div>
               <div class="form-group">
                  <label>내용:</label> 
                   <textarea class="form-control" rows="5" id=contents name="contents">${vo.contents}</textarea>
               </div>   
               <div class="form-group">
                  <label>작성자: </label> 
                  <input type="text" class="form-control" id="writer" name="writer" value ="${vo.writer}" readonly="readonly">
               </div>               
               <input type="submit" value="수정" class="btn btn-primary btn-sm" >                  
               <input type='reset' value='취소' class='btn btn-warning btn-sm'>
               <input type='button' value='삭제' class='btn btn-info btn-sm' onclick = "goDel(${vo.idx})">  
               <input type='button' value='목록' class='btn btn-success btn-sm'>             
            </form>    
    </div>
    <div class="panel-footer">빅데이터분석 4차(리대교)</div>
  </div>
</div>

</body>
</html>