package com.gura.spring07.users.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring07.users.dto.UsersDto;
import com.gura.spring07.users.service.UsersService;

@Controller
public class UsersController {
	
	//의존 객체(DI)
	@Autowired
	private UsersService service; 
	
	//회원 가입 폼 요청 처리 
	@RequestMapping("/users/signup_form")
	public String signupForm(){
		//수행할 비즈니스 로직은 없다.
		
		// view page 로 forward 이동해서 회원 가입 폼 출력
		return "users/signup_form";
	}
	//회원 가입 요청처리
	@RequestMapping(value="/users/signup", 
			method=RequestMethod.POST)
	public ModelAndView signup(ModelAndView mView,
			@ModelAttribute UsersDto dto){
		//dto 에는 폼 전송된 내용이 들어 있다.
		//UsersService 를 이용해서 회원 가입 처리 하고
		service.addUser(mView, dto);
		//view page 정보를 ModelAndView 객체에 담아서 
		mView.setViewName("users/greeting");
		//응답하기 
		return mView;
	}
	//로그인 폼 요청 처리
	@RequestMapping("/users/loginform")
	public String loginform(HttpServletRequest request){
		String url=request.getParameter("url");
		if(url == null){//목적지 정보가 없으면
			String cPath=request.getContextPath();
			url=cPath+"/"; //인덱스 페이지로 갈수 있도록 
		}
		// request 영역에 view page 에 전달할 모델을 담고
		request.setAttribute("url", url);		
		
		return "users/loginform";
	}
	//로그인 요청 처리
	@RequestMapping(value="/users/login",
			method=RequestMethod.POST)
	public ModelAndView login(ModelAndView mView,
			@ModelAttribute UsersDto dto,
			HttpSession session,
			HttpServletRequest request){
		//UsersService 를 이용해서 로그인 관련 비즈니스
		//로직 처리 
		service.validUser(mView, dto, session);
		//월래 가려던 url 정보를 request 에 담는다.
		String encodedUrl=
			URLEncoder.encode(request.getParameter("url"));
		request.setAttribute("encodedUrl", encodedUrl);
		
		mView.setViewName("users/login");
		return mView;
	}
	//로그아웃 요청 처리
	@RequestMapping("/users/logout")
	public String logout(HttpSession session){
		//세션 초기화
		session.invalidate();
		return "redirect:/home.do";
	}
	
	//개인 정보 보기 요청 처리
	@RequestMapping("/users/info")
	public ModelAndView authInfo(HttpServletRequest request,
			ModelAndView mView){
		//UsersService 를 이용해서 비즈니스 로직 처리
		service.showInfo(request.getSession(), mView);
		
		//view page 로 forward 이동 
		mView.setViewName("users/info");
		return mView;
	}
	
	//개인 정보 삭제 요청 처리
	@RequestMapping("/users/delete")
	public ModelAndView authDelete(HttpServletRequest request){
		service.deleteUser(request.getSession());
		// new ModelAndView("view page 정보");
		return new ModelAndView("users/delete");
	}
	/*
	 * [JSON 문자열 응답하는 방법]
	 * 
	 * 1. pom.xml에 jackson data bind 라이브러리 dependency 설정
	 * 2. Map 혹은 Dto 를 리턴해준다.
	 * 3. @ResponseBody 어노테이션을 컨트롤러의 메소드에 붙인다.
	 */
	//아이이 중복확인 ajax 요청 처리
	@RequestMapping("/users/checkid")
	@ResponseBody
	public Map<String, Object> 
		checkid(@RequestParam String inputId){
		//서비스를 통해서 리턴받은 값을 리턴해 준다. 
		return service.canUseId(inputId);
	}
	@RequestMapping("/users/updateform")
	public ModelAndView authUpdateform(HttpServletRequest request,
			ModelAndView mView){
		
		service.showInfo(request.getSession(), mView);
		
		mView.setViewName("users/updateform");
		return mView;
	}
	
	@RequestMapping(value="/users/update", 
			method=RequestMethod.POST)
	public ModelAndView authUpdate(HttpServletRequest request,
			@ModelAttribute UsersDto dto){
		//서비스를 이용해서 회원정보를 수정반영하고
		service.updateUser(dto);
		//개인 정보보기로 리다일렉트 시킨다.
		return new ModelAndView("redirect:/users/info.do");
	}
	//비밀번호 수정폼 요청 처리 
	@RequestMapping("/users/pwd_updateform")
	public ModelAndView authPwdForm(HttpServletRequest request){
		
		return new ModelAndView("users/pwd_updateform");
	}
	
	//비밀번호 수정반영 요청 처리
	@RequestMapping(value="/users/update_pwd",
			method=RequestMethod.POST)
	public ModelAndView authUpdatePwd(HttpServletRequest request){
		//비밀번호를 수정하는 비즈니스 로직을 수행하고 
		service.updatePwd(request);
		//view page 로 forward 이동해서 응답
		return new ModelAndView("users/update_pwd");
	}
	
	// ajax 프로파일 이미지 업로드 요청 처리
	@RequestMapping("/users/profile_upload")
	@ResponseBody
	public Map<String, Object> 
		authProfileUpload(HttpServletRequest request,
				@RequestParam MultipartFile profileImage){
		//서비스를 이용해서 프로파일 이미지를 저장하고
		//이미지 저장경로를 리턴 받는다.
		String path=
			service.saveProfileImage(request, profileImage);
		
		//이미지 저장경로를 Map 에 path 라는 키값으로
		//저장된 이미지 경로를 담아서 리턴한다. 
		Map<String, Object> map=new HashMap<>();
		map.put("path", path);
		return map;
	}
}








































