package com.bookStore.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.dao.ShopCartMapper;
import com.bookStore.dao.model.ShopCart;

@Service
public class ShopCartSevice {

	@Autowired
	private ShopCartMapper cartMapper;

	public List<ShopCart> getlistBycusId(Integer cusId) {
		// TODO Auto-generated method stub
		return cartMapper.getlistBycusId(cusId);
	}

	public void AddShopcart(ShopCart shopCart) {
		 
		 cartMapper.AddShopcart(shopCart);
	}

	public List<ShopCart> getlistBycusIdandBookId(int cusId, int bookId) {
		 
		return cartMapper.getlistBycusIdandBookId(cusId,bookId);
	}
	
	
}
