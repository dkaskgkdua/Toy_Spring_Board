<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../board/header.jsp" />
<style>
.table {
	background-color: white !important;
}

th, td {
	color: black !important;
	text-align: center;
}
</style>
<script>
	$(function() {
		var gender = ${memberinfo.gender};
		
		if (gender == 1) {
			$('#Umale').attr('checked', 'checked');
		} else {
			$('#Ufemale').attr('checked', 'checked');
		}
	});
</script>
</head>
<body>

	<div class="container">
		<form action="my_update.net" id="updateMember_form"
			name="joinform" method="post">
			<fieldset>
				<div class="form-group">
					<label for="updateMember_id">아이디</label> <input type="text"
						class="form-control" id="updateMember_id" placeholder="Enter id"
						name="id" value="${memberinfo.id}" readOnly>
				</div>
				<div class="form-group">
					<label for="updateMember_pass">비밀번호</label> <input type="password"
						class="form-control" id="updateMember_pass"
						placeholder="Enter password" name="password" required
						maxLength="20" value="${memberinfo.password}">
				</div>
				<div class="form-group">
					<label for="updateMember_name">이름</label> <input type="text"
						id="updateMember_name" class="form-control" name="name"
						placeholder="Enter name" required maxLength="15"
						value="${memberinfo.name}">
				</div>
				<div class="form-group">
					<label for="updateMember_age">나이</label> <input type="text"
						id="updateMember_age" class="form-control" name="age"
						value="${memberinfo.age}" placeholder="Enter age" required>
				</div>
				<div class="form-group">
					<div>
						<label for="male">성별</label>
					</div>
					<input type="radio" id="Umale" name="gender" value="1"> 
					<label for="male">남</label> 
					<input type="radio" id="Ufemale" name="gender" value="2"> 
					<label for="female">여</label>
				</div>


				<div class="form-group">
					<label for="updateMember_email">이메일</label> <input type="email"
						id="updateMember_email" class="form-control" name="email"
						placeholder="Enter email" required value="${memberinfo.email}">
				</div>



				<button class="update_button" id="updateMember_button" type="submit">수정</button>
				<button class="update_button cancelbtn" data-dismiss="modal"
					type="button">돌아가기</button>
			</fieldset>
		</form>
	</div>
</body>
</html>