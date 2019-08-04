package com.gura.spring07.file.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.gura.spring07.file.dto.FileDto;

public interface FileService {
	//파일 목록 비즈니스 로직 처리 
	public void getList(HttpServletRequest request);
	//파일 삭제 비즈니스 로직 처리 
	public void removeFileInfo(int num, 
			HttpServletRequest request, 
			HttpServletResponse response);
	//파일 저장 비즈니스 로직 처리 
	public void saveFile(FileDto dto, 
			HttpServletRequest request);
	//파일 정보 가져오는 비즈니스 로직 처리 
	public void getFileData(ModelAndView mView, int num);
	//다운 카운트를 올리는 비즈니스 로직 처리 
	public void addDownCount(int num);
}










