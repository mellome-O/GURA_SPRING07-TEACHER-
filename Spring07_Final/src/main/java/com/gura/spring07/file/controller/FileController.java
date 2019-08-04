package com.gura.spring07.file.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring07.file.dto.FileDto;
import com.gura.spring07.file.service.FileService;

@Controller
public class FileController {
	@Autowired
	private FileService service;
	
	@RequestMapping("/file/list")
	public ModelAndView list(HttpServletRequest request){
		service.getList(request);
		
		//view page 로 forward 이동 
		return new ModelAndView("file/list");
	}
	
	@RequestMapping("/file/upload_form")
	public ModelAndView 
		authUploadForm(HttpServletRequest request){
		
		return new ModelAndView("file/upload_form");
	}
	
	//파일 업로드 요청 처리
	@RequestMapping("/file/upload")
	public ModelAndView 
		authUpload(HttpServletRequest request,
				@ModelAttribute FileDto dto){
		//서비스 객체를 이용해서 파일을 저장한다. 
		service.saveFile(dto, request);
		return new ModelAndView("redirect:/file/list.do");
	}
	
	@RequestMapping("/file/download")
	public ModelAndView 
		download(ModelAndView mView,
				@RequestParam int num){
		//파일을 다운로드하기 위한 비즈니스 로직 수행
		service.getFileData(mView, num);
		service.addDownCount(num);
		//view page 의 이름으로 다운로드할 
		//View 의 이름(bean 의 이름) 을 지정한다.
		mView.setViewName("fileDownView");
		return mView;
	}
	@RequestMapping("/file/delete")
	public ModelAndView 
		authDelete(HttpServletRequest request,
				@RequestParam int num,
				HttpServletResponse response){
		
		service.removeFileInfo(num, request, response);
		
		return new ModelAndView("redirect:/file/list.do");
		
	}
}












































