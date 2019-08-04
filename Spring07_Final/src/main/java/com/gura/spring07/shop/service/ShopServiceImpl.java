package com.gura.spring07.shop.service;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring07.exception.NoDeliveryException;
import com.gura.spring07.exception.NoGoodsException;
import com.gura.spring07.shop.dao.OrderDao;
import com.gura.spring07.shop.dao.ShopDao;
import com.gura.spring07.shop.dto.OrderDto;
import com.gura.spring07.shop.dto.ShopDto;

@Service
public class ShopServiceImpl implements ShopService{
	
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private OrderDao orderDao;
	//상품목록 얻어오는 비즈니스 로직 
	@Override
	public void getList(ModelAndView mView) {
		List<ShopDto> list=shopDao.getList();
		mView.addObject("list", list);
	}
	/*
		- Spring 트랜젝션 설정 방법
		
		1. pom.xml 에 spring-tx dependency 추가
		2. servlet-context.xml 에 transaction 설정 추가
		3. 트랜젝션을 적용할 서비스의 메소드에 
		   @Transactional 어노테이션 추가 
	 */
	
	
	//상품 구입 처리를 하는 비즈니스 로직 
	@Transactional
	@Override
	public void buy(HttpServletRequest request, ModelAndView mView) {
		//로그인된 아이디
		String id=(String)
				request.getSession().getAttribute("id");
		//구입할 상품 번호
		int num=Integer.parseInt(request.getParameter("num"));
		
		//1. 상품의 가격정보 얻어오기
		int price=shopDao.getPrice(num);
		
		//2. 가격만큼 계좌 잔액을 줄인다.
		ShopDto dto=new ShopDto();
		dto.setId(id);
		dto.setPrice(price);
		dto.setNum(num);
		
		shopDao.minusMoney(dto);
		//3. 가격의 10% 를 포인트로 적립
		shopDao.plusPoint(dto);
		//4. 재고의 갯수를 1 줄인다.
		shopDao.minusCount(num); //상품번호 전달 
		//5. 배송 요청 정보를 추가한다.
		OrderDto dto2=new OrderDto();
		dto2.setId(id); //누가
		dto2.setCode(num); //어떤 상품을 
		dto2.setAddr("동아빌딩 15층"); //어디로 ?
		
		//임의의 시점에 테스트로 Exception 발생 시키기 
		//테스트 교육용으로 랜덤으로 설정해놓음
		Random ran=new Random();
		int ranNum=ran.nextInt(2);
		if(ranNum==1){
			//throw new NoDeliveryException("배송불가 지역임");
			throw new NoGoodsException("상품이 없어요");
		}
		
		orderDao.addOrder(dto2);
	}

}






















