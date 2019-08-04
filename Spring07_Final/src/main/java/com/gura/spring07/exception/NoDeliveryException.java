package com.gura.spring07.exception;

import org.springframework.dao.DataAccessException;

/*
 *  배송 불가 지역인 경우에 발생시킬 Exception 
 */
public class NoDeliveryException extends DataAccessException{

	public NoDeliveryException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}








