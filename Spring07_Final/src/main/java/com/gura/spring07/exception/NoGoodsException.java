package com.gura.spring07.exception;

import org.springframework.dao.DataAccessException;

public class NoGoodsException extends DataAccessException{

	public NoGoodsException(String msg) {
		super(msg);
		
	}

}
