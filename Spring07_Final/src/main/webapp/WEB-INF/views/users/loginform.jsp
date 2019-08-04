<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8"/>
<title>users/loginform.jsp</title>
</head>
<body>
<h3>로그인 폼 입니다.</h3>
<form action="login.do" method="post">
	<input type="hidden" name="url" value="${url }" />
	<label for="id">아이디</label>
	<input type="text" id="id" name="id" />
	<label for="pwd">비밀번호</label>
	<input type="password" id="pwd" name="pwd"/>
	<button type="submit">로그인</button>
</form>
</body>
</html>





