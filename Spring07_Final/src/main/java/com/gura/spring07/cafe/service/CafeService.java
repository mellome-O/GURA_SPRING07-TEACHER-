package com.gura.spring07.cafe.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.gura.spring07.cafe.dto.CafeCommentDto;
import com.gura.spring07.cafe.dto.CafeDto;

public interface CafeService {
	public void getList(HttpServletRequest request);
	public void saveContent(CafeDto dto);
	public void getDetail(HttpServletRequest request);
	public void saveComment(HttpServletRequest request);
	public void updateComment(CafeCommentDto dto);
	public void deleteComment(int num);
	//원글 삭제 하는 메소드 
	public void deleteContent(int num);
	//원글 수정폼 출력에 필요한 메소드 
	public void getUpdateData(ModelAndView mView, int num);
	//원글 수정하는 메소드 
	public void updateContent(CafeDto dto);
}












