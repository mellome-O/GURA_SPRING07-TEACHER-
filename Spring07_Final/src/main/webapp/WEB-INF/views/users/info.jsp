<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8"/>
<title>users/info.jsp</title>
<style>
	.profile{
		width: 50px;
		height: 50px;
		border-radius: 50%;
	}
	#profileForm{
		display:none;
	}
</style>
</head>
<body>
<h3>개인 정보 입니다.</h3>
<table>
	<thead>
		<tr>
			<th>항목</th>
			<th>정보</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th>아이디</th>
			<td>${dto.id }</td>
		</tr>
		<tr>
			<th>프로필 이미지</th>
			<td>
				<a href="javascript:" id="profileLink">
				<c:choose>
					<c:when test="${empty dto.profile }">
						<img class="profile" src="${pageContext.request.contextPath }/resources/images/default_user.jpg" />
					</c:when>
					<c:otherwise>
						<img class="profile" src="${pageContext.request.contextPath }${dto.profile}"/>
					</c:otherwise>
				</c:choose>
				</a>
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${dto.email }</td>
		</tr>
		<tr>
			<th>가입일</th>
			<td>${dto.regdate }</td>
		</tr>
	</tbody>
</table>
<a href="updateform.do">회원정보 수정</a>
<a href="javascript:deleteConfirm()">회원 탈퇴</a>

<form id="profileForm" action="profile_upload.do" 
	method="post" enctype="multipart/form-data">
	<input type="file" name="profileImage" id="profileImage" 
	accept=".jpg, .jpeg, .png, .JPG, .JPEG"/>
</form>

<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.min.js"></script>
<script>
	//1. 프로파일 링크를 클릭했을때 사진 선택창을 띄운다.
	$("#profileLink").click(function(){
		$("#profileImage").click();
	});
	
	//2. 이미지를 선택했을때 폼을 강제 제출한다.
	$("#profileImage").on("change", function(){
		$("#profileForm").submit();
	});
	
	//3. 폼이 제출될때 ajax 로 제출되게 하기
	$("#profileForm").ajaxForm(function(responseData){
		// responseData => {path:"/upload/xxx.jpg"}
		console.log(responseData);
		//업로드된 이미지가 보이도록 
		var src="${pageContext.request.contextPath}"+
			responseData.path;
		$(".profile").attr("src", src);
	});

	function deleteConfirm(){
		var isDelete=confirm("${id} 회원님 탈퇴 하시겠습니까?");
		if(isDelete){
			location.href="${pageContext.request.contextPath}/users/delete.do";
		}
	}
</script>
</body>
</html>





