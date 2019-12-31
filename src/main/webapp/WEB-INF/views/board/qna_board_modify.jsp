<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="./header.jsp" />
<script src = "resources/js/boardlist.js"></script>
<meta charset="UTF-8">
<title>게시판 수정</title>
<style>
.container {
   padding-top : 55px;
   margin: 0 auto;
   width: 70%;
   height: 70%;
}
h1 {
   font-size: 1.5rem;
   text-align: center;
   color: #1a92b9;
}
#upfile {
   display: none
}
</style>
</head>
<body>
	<div class="container">
		<form action="BoardModifyAction.bo" method="post" name="modifyform"
			enctype="multipart/form-data">
			<input type="hidden" name="BOARD_NUM" value="${boarddata.BOARD_NUM}">
			<input type="hidden" name = "BOARD_ORIGINAL" value="${boarddata.BOARD_ORIGINAL}">
			<input type="hidden" name= "BOARD_FILE" value ="${boarddata.BOARD_FILE}">
			<input type="hidden" name = "before_file" value ="${boarddata.BOARD_FILE}">
			<h1>자유게시판 - 수정 페이지</h1>
			<div class="form-group">
				<label for="board_name">글쓴이</label> <input type="text"
					class="form-control" value="${boarddata.BOARD_NAME}" readOnly>
			</div>
			
			<div class="form-group">
				<label for="board_subject">제목</label> <input type="text"
					name="BOARD_SUBJECT" id="board_subject" maxlength="100"
					class="form-control" value="${boarddata.BOARD_SUBJECT}">
			</div>
			<div class="form-group">
				<label for="board_content">내용</label>
				<textarea name="BOARD_CONTENT" id="board_content"
					class="form-control" rows="15">${boarddata.BOARD_CONTENT}</textarea>
			</div>
			
			<!--  파일이 첨부되어 있으면 -->
			<c:if test="${boarddata.BOARD_RE_LEV ==0}">
				<div class="form-group read">
					<label for="board_file">파일 첨부</label> 
					<label for="upfile">
						<img src="resources/Image/file.png" alt="파일첨부" width="20px">
					</label> 
					<input type="file" id="upfile" name="uploadfile"> 
					
					<span id="filevalue">${boarddata.BOARD_ORIGINAL}</span> 
					<img src="resources/Image/remove.png" alt="파일삭제" width="10px" class="remove">
				</div>
			</c:if>
			<div class="form-group">
				<label for="board_pass">비밀번호</label> <input name="BOARD_PASS"
					id="board_pass" type="password" maxlength="30" class="form-control"
					placeholder="Enter board_pass" value="">
			</div>

			<div class="form-group">
				<button type="submit" class="btn btn-primary">수정</button>
				<button type="reset" class="btn btn-danger" onClick="history.go(-1)">취소</button>
			</div>
		</form>
	</div>
</body>
</html>