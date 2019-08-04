package com.gura.spring07.users.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring07.users.dto.UsersDto;

public interface UsersService {
	//DB 에 회원정보를 저장하는 비즈니스 로직
	public void addUser(ModelAndView mView, UsersDto dto);
	//UsersDto 에 담긴 id, pwd 가 유효한 정보인지 확인해서 
	//유효한 정보이면 로그인 처리를 하는 메소드
	public void validUser(ModelAndView mView, UsersDto dto,
			HttpSession session);
	//개인 정보를 보여주기위한 비즈니스 로직 처리
	public void showInfo
		(HttpSession session, ModelAndView mView);
	//개인정보 삭제하는 메소드
	public void deleteUser(HttpSession session);
	//아이디 사용가능 여부를 Map 에 담아서 리턴
	public Map<String, Object> canUseId(String inputId);
	//개인정보 수정반영하는 메소드
	public void updateUser(UsersDto dto);
	//비밀번호 수정 반영하는 메소드
	public void updatePwd(HttpServletRequest request);
	//프로파일 이미지를 저장하는 메소드
	public String saveProfileImage(HttpServletRequest request, 
			MultipartFile mFile);
}

















