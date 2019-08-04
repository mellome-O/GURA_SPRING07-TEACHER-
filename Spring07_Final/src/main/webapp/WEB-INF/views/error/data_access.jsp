<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8"/>
<title>error/data_access.jsp</title>
</head>
<body>
<p>DB 관련 작업 중에 오류가 발생했습니다.</p>
<p>오류 메세지 : ${exception.message }</p>
<a href="${pageContext.request.contextPath }/">인덱스로 가기</a>
</body>
</html>











