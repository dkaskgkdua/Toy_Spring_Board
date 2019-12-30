<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html>
<head>
<style>
	body{background-color:#f7bfbf; text-align:center}
</style>
<meta charset="UTF-8">
<title>error 페이지</title>
</head>
<body>
<img src="resources/Image/remove.png" width='100px'><br>
죄송합니다.<br>
요청하신 <b>${url}</b> 이 존재하지 않습니다.
<hr>
${message}
</body>
</html>