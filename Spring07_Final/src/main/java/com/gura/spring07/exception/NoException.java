package com.gura.spring07.exception;


public class NoException extends RuntimeException{
	private String msg;
	
	//생성자 
	public NoException(String msg){
		this.msg=msg;
	}
	//필드에 저장된 내용을 리턴하는 메소드 
	public String getMsg() {
		return msg;
	}
}
