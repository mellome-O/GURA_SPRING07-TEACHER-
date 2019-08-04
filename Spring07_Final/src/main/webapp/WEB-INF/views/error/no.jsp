<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8"/>
<title>error/no.jsp</title>
</head>
<body>
<p>${exception.msg }</p>
<p>요청이 정상적으로 처리 되지 않았습니다.</p>
<a href="${pageContext.request.contextPath }/">인덱스로</a>
</body>
</html>