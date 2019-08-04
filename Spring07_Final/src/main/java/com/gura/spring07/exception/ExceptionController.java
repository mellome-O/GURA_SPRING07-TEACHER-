package com.gura.spring07.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*
 *  Spring 에서 예외 처리하기
 *  
 *  - 컨트롤러를 bean 으로 만들기 위해 @ControllerAdvice 를
 *    클래스위에 작성하고 컴포넌트 스캔을 한다. 
 */

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(SleepyException.class)
	public ModelAndView sleepy(){
		ModelAndView mView=new ModelAndView();
		mView.addObject("msg", "너무 졸려서 처리 못하겠으~");
		mView.setViewName("error/sleepy");
		return mView;
	}
	@ExceptionHandler(NoException.class)
	public ModelAndView no(NoException ne){
		//발생한 예외 객체를 메소드의 인자로 전달 받을수 있다.
		
		ModelAndView mView=new ModelAndView();
		// "exception" 이라는 키값으로 예외 객체를 담는다.
		mView.addObject("exception", ne);
		mView.setViewName("error/no");
		return mView;
	}
	
	// DataAccessException 클래스를 상속받은
	// NoGoodsException 처리 하기 
	@ExceptionHandler(NoGoodsException.class)
	public ModelAndView noGoods(NoGoodsException nge){
		ModelAndView mView=new ModelAndView();
		mView.addObject("exception", nge);
		mView.setViewName("error/no_goods");
		return mView;
	}
	
	/*
	 *  @Repository 어노테이션이 작성된 Dao 에서 
	 *  DB 관련 Exception 이 발생하면 스프링 프레임 워크가
	 *  DataAccessException 예외를 발생시킨다.
	 *  예외 객체는 메소드의 인자로 전달되고
	 *  예외 객체는 getMessage() 라는 메소드가 있고
	 *  그 메소드를 호출하면 예외 메세지를 리턴해 준다.  
	 */
	@ExceptionHandler(DataAccessException.class)
	public ModelAndView dataAccess(DataAccessException dae){
		ModelAndView mView=new ModelAndView();
		mView.addObject("exception", dae);
		// view page 에서 ${exception.message }
		// 해서 예외 정보를 출력할수 있다. 
		mView.setViewName("error/data_access");
		return mView;
	}
}

























