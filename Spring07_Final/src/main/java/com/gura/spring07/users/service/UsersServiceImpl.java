package com.gura.spring07.users.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring07.users.dao.UsersDao;
import com.gura.spring07.users.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService{
	//의존객체 DI
	@Autowired
	private UsersDao dao;

	@Override
	public void addUser(ModelAndView mView, UsersDto dto) {
		//dto 에 저장된 비밀번호를 암호화 해서 
		String plainText=dto.getPwd();
		BCryptPasswordEncoder encoder=
				new BCryptPasswordEncoder();
		//암호화된 비밀번호를 dto 에 넣고 
		dto.setPwd(encoder.encode(plainText));
		//DB 에 저장한다.
		dao.insert(dto);
		//가입된 아이디를 request 에 담기
		mView.addObject("id", dto.getId());
	}

	@Override
	public void validUser(ModelAndView mView, UsersDto dto, HttpSession session) {
		//아이디 비밀번호가 유효한지 여부 
		boolean isValid=false;
		//id 를 이용해서 DB 에 저장된 암호화된 비밀번호를 
		//읽어온다. 
		String pwdHash=dao.getPwdHash(dto.getId());
		if(pwdHash != null){
			//비밀번호가 일치하는지 여부를 isValid 에 대입한다.
			isValid=BCrypt.checkpw(dto.getPwd(), pwdHash);
		}
		
		if(isValid){//아이디 비밀번호가 일치한 경우 
			//로그인 처리를 한다.
			session.setAttribute("id", dto.getId());
			//로그인 성공 여부를 ModelAndView 객체에 담는다.
			mView.addObject("isSuccess", true);
		}else{//아이디 혹은 비밀번호가 틀린경우 
			mView.addObject("isSuccess", false);
		}
		
	}

	@Override
	public void showInfo(HttpSession session, ModelAndView mView) {
		//세션에 저장된 아이디를 읽어와서 
		String id=(String)session.getAttribute("id");
		//개인정보를 얻어오고 
		UsersDto dto=dao.getData(id);
		//request 에 담을수 있도록 
		mView.addObject("dto", dto);
	}

	@Override
	public void deleteUser(HttpSession session) {
		//세션에 있는 아이디를 읽어와서 
		String id=(String)session.getAttribute("id");
		//dao 를 이용해서 삭제 
		dao.delete(id);
		//세션 초기화
		session.invalidate();
	}

	@Override
	public Map<String, Object> canUseId(String inputId) {
		//입력한 아이디가 이미 존재 하는지 여부를 얻어온다.
		boolean isExist=dao.isExist(inputId);
		
		Map<String, Object> map=new HashMap<>();
		//만일 이미 존재 한다면
		if(isExist){
			// Map 에 canUse 라는 key 값으로 false 담기
			map.put("canUse", false);
		}else{//존재 하지 않는다면
			// Map 에 canUse 라는 key 값으로 true 담기
			map.put("canUse", true);
		}
		
		return map;
	}

	@Override
	public void updateUser(UsersDto dto) {
		dao.update(dto);
	}

	@Override
	public void updatePwd(HttpServletRequest request) {
		//비밀번호를 수정할 회원의 아이디를 세션에서 얻어온다. 
		String id=(String)
				request.getSession().getAttribute("id");
		//새 비밀번호를 읽어온다.
		String pwd=request.getParameter("pwd");
		//비밀 번호를 암호화 한다.
		BCryptPasswordEncoder encoder=
				new BCryptPasswordEncoder();
		//UsersDto 객체를 생성해서 담는다.
		UsersDto dto=new UsersDto();
		dto.setId(id);
		dto.setPwd(encoder.encode(pwd));
		//dao 를 이용해서 DB 에 수정 반영한다.
		dao.updatePwd(dto);		
	}

	@Override
	public String saveProfileImage(HttpServletRequest request, 
			MultipartFile mFile) {
		//파일을 저장할 폴더의 절대 경로를 얻어온다.
		String realPath=request.getSession()
				.getServletContext().getRealPath("/upload");
		//원본 파일명
		String orgFileName=mFile.getOriginalFilename();
		//저장할 파일의 상세 경로
		String filePath=realPath+File.separator;
		//디렉토리를 만들 파일 객체 생성
		File file=new File(filePath);
		if(!file.exists()){//디렉토리가 존재하지 않는다면
			file.mkdir();//디렉토리를 만든다.
		}
		//파일 시스템에 저장할 파일명을 만든다. (겹치치 않게)
		String saveFileName=
				System.currentTimeMillis()+orgFileName;
		try{
			//upload 폴더에 파일을 저장한다.
			mFile.transferTo(new File(filePath+saveFileName));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//UsersDao 객체를 이용해서 프로파일 이미지
		//경로를 DB 에 저장하기
		String path="/upload/"+saveFileName;			
		//로그인된 아이디
		String id=(String)
				request.getSession().getAttribute("id");
		//아이디와 프로파일 이미지 경로를 dto 에 담고 
		UsersDto dto=new UsersDto();
		dto.setId(id);
		dto.setProfile(path);
		// UsersDao 를 이용해서 DB 에 반영하기 
		dao.updateProfile(dto);
		
		//프로파일 이미지 경로를 리턴해주기 
		return path;
	}
	
}































