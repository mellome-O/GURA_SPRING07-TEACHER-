package com.gura.spring07.cafe.dao;

import java.util.List;

import com.gura.spring07.cafe.dto.CafeCommentDto;

public interface CafeCommentDao {
	//댓글 목록 
	public List<CafeCommentDto> getList(int ref_group);
	//댓글 삭제
	public void delete(int num);
	//댓글 추가 
	public void insert(CafeCommentDto dto);
	//추가할 댓글의 primary key 값 얻어오기 
	public int getSequence();
	//댓글 수정 
	public void update(CafeCommentDto dto);
}









