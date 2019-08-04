<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8"/>
<title>home.jsp</title>
</head>
<body>
<c:choose>
	<c:when test="${empty id }">
		<a href="users/loginform.do">로그인</a>
		<a href="users/signup_form.do">회원가입</a>
	</c:when>
	<c:otherwise>
		<p>
			<a href="users/info.do">${id }</a> 님 로그인중...
			<a href="users/logout.do">로그아웃</a>
		</p>
	</c:otherwise>
</c:choose>

<h3>인덱스 페이지 입니다.</h3>
<ul>
	<li><a href="play.do">로그인 해야 갈수 있는곳</a></li>
	<li><a href="cafe/list.do">카페 글 목록보기</a></li>
	<li><a href="file/list.do">자료실 목록 보기</a></li>
	<!-- <li><a href="gallery/list.do">겔러리 목록 보기</a></li> -->
	<li><a href="shop/list.do">상품 목록보기(트랜잭션 연습)</a></li> 
	<!-- <li><a href="counter/counter2.html">counter 예제</a></li>
	<li><a href="product/index.html">produce 예제</a></li> -->
</ul>
<h4>공지사항</h4>
<ul>
	<c:forEach var="tmp" items="${noticeList }">
		<li>${tmp }</li>
	</c:forEach>
</ul>
</body>
</html>




















