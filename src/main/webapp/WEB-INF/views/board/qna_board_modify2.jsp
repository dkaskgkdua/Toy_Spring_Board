<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"/>
<script src="resources/js/writeform.js" charset="utf-8"></script>
<style>
tr.center-block{text-align:center}
h1{font-size:1.5rem; text-align:center; color:#1a92b9}
.container{width:60%}
label{font-weight:bold}
#upfile{display:none}
img{width:20px}
</style>


<meta charset="UTF-8">
<title>질문 게시판</title>
</head>
<body>
  <div class="container">
    <form action = "BoardModifyAction.bo" method="post" name="boardform" enctype="multipart/form-data">
    <input type="hidden" name="BOARD_NUM" value="${boarddata.BOARD_NUM }">
    <input type="hidden" name="BOARD_ORIGINAL" value="${boarddata.BOARD_ORIGINAL}">
    <h1>MVC 게시판-수정 페이지</h1>
    <div class="form-group">
      <label for="board_name">글쓴이</label>
      <input name="BOARD_NAME" id="board_name" value="${boarddata.BOARD_NAME}" readOnly type="text" size="10" maxlength="30" class="form-control" >
    </div>
          
      <div class="form-group">
      <label for="board_subject">수정할 제목</label>
      <input  name="BOARD_SUBJECT"  id="board_subject" type="text" maxlength="100" class="form-control" placeholder="제목 입력" value="${boarddata.BOARD_SUBJECT }">
    </div>
     
     <div class="form-group">
      <label for="board_content">수정할 내용</label>
      <textarea name="BOARD_CONTENT" id="board_content"   rows="15" class="form-control">${boarddata.BOARD_CONTENT}</textarea>
    </div>
    
    
    <!-- 파일이 첨부되어 있으면 -->
    <c:if test="${!empty boarddata.BOARD_FILE}">
    <div class="form-group">
      <label for="board_file">파일 첨부</label>
      <label for="upfile">
        <img src="resources/image/attach.png" alt="사막"></label>
      <input type="file" id="upfile" name="uploadfile"><span id="filevalue">${boarddata.BOARD_FILE }</span>
      </div>
      </c:if>
      
       <div class="form-group">
      <label for="board_pass">비밀번호</label>
      <input name="BOARD_PASS" id="board_pass" value=""  type="password"  maxlength="30" class="form-control" placeholder="비밀 번호 입력">
    </div>

   
      <div class="form-group">
      <button type="submit" class="btn btn-primary">수정</button>
      <button type="reset" class="btn btn-primary" onClick="history.go(-1)">취소</button>
    </div>     
   </form>
  </div>   
</body>
</html>