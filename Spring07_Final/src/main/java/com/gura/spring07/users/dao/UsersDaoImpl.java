package com.gura.spring07.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring07.users.dto.UsersDto;

@Repository
public class UsersDaoImpl implements UsersDao{
	//의존 객체(DI)
	@Autowired
	private SqlSession session;
	//회원 정보 저장
	@Override
	public void insert(UsersDto dto) {
		session.insert("users.insert", dto);
	}
	//암호화된 비밀번호를 리턴해주는 메소드 
	@Override
	public String getPwdHash(String id) {
		String hash=session.selectOne("users.getPwdHash", id);
		return hash;
	}
	//아이디에 해당하는 개인정보를 리턴하는 메소드 
	@Override
	public UsersDto getData(String id) {
		UsersDto dto=session.selectOne("users.getData", id);
		return dto;
	}
	//아이디에 해당하는 개인정보를 삭제 하는 메소드 
	@Override
	public void delete(String id) {
		session.delete("users.delete", id);
	}
	@Override
	public boolean isExist(String inputId) {
		// id 를 이용해서 select 해보고
		String selectedId=
			session.selectOne("users.isExist", inputId);
		//존재하는지 여부를 리턴한다.
		if(selectedId==null){
			return false;
		}else{
			return true;
		}
	}
	//개인 정보 수정반영
	@Override
	public void update(UsersDto dto) {
		session.update("users.update", dto);
	}
	//비밀번호 수정반영 
	@Override
	public void updatePwd(UsersDto dto) {
		session.update("users.updatePwd", dto);
	}
	@Override
	public void updateProfile(UsersDto dto) {
		session.update("users.updateProfile", dto);
	}
	
}

















