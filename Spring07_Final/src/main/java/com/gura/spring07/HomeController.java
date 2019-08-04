package com.gura.spring07;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring07.exception.SleepyException;


@Controller
public class HomeController {
	
	@RequestMapping("/home.do")
	public String home(HttpServletRequest request){
		
		//오늘의 공지 사항을 request 에 담기
		List<String> noticeList=new ArrayList<>();
		noticeList.add("곧 여름 입니다.");
		noticeList.add("수료가 얼마 남지 않았네요");
		noticeList.add("어쩌구...");
		noticeList.add("저쩌구...");
		
		request.setAttribute("noticeList", noticeList);
		
		// view page 로 forward 이동 해서 응답 
		return "home";
	}
	//로그인 해야지만 정상 처리가 되는 메소드 
	@RequestMapping("/play")
	public ModelAndView authPlay(HttpServletRequest request,
			ModelAndView mView){
		
		//ExceptionController 테스트임 //exception 발생하면 졸리다페이지
		Random ran=new Random();
		int ranNum=ran.nextInt(2);
		if(ranNum==0){
			throw new SleepyException();
		}
		
		
		mView.setViewName("play");
		return mView;
	}
}





















