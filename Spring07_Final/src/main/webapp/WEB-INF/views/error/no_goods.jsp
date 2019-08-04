<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8"/>
<title>error/no_goods.jsp</title>
</head>
<body>
<p>정상적인 처리가 되지 않았습니다.</p>
<p>정보:${exception.message }</p>
</body>
</html>