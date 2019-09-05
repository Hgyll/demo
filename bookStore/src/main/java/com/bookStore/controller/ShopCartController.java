package com.bookStore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookStore.dao.model.ShopCart;
import com.bookStore.sevice.ShopCartSevice;

@RestController
@CrossOrigin
@RequestMapping("/shopCart")
public class ShopCartController {

	@Autowired
	private ShopCartSevice cartSevice;
	
	
	@RequestMapping("getlistID")
	public List<ShopCart> getlistID(Integer cusId) {

	//	Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<ShopCart> list = cartSevice.getlistBycusId(cusId);
			//map.put("rows", list);
			return list;
		} catch (Exception e) {
			return null;
		}
	}
	

	@ResponseBody
	@RequestMapping("getlistBycusId")
	public Map<String, Object> getlistBycusId(Integer cusId) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<ShopCart> list = cartSevice.getlistBycusId(cusId);
			map.put("rows", list);
			return map;
		} catch (Exception e) {
			return null;
		}
	}

	//@ResponseBody
	@RequestMapping("AddShopcart")
	public Map<String, Object> AddShopcart(ShopCart shopCart) {
		System.out.println(shopCart.getCusId());
		System.out.println(shopCart.getBookId());
		System.out.println(shopCart.getBookName());
		int cusId = shopCart.getCusId();
		int bookId=shopCart.getBookId();
		List<ShopCart> list = cartSevice.getlistBycusIdandBookId(cusId,bookId);
		
		System.out.println("list.size()"+list.size());
		
		 Map<String, Object>  map=new HashMap<String, Object>();
		if (list.size() > 0) { 
			System.out.println("购物车已存在");
			map.put("result", "购物车已存在");
			return map;
		} else { 
			java.util.Calendar c = java.util.Calendar.getInstance();
			java.text.SimpleDateFormat f = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			shopCart.setShopDate(f.format(c.getTime()));// 当前日期 
			//Map<String, Object> map = new HashMap<String, Object>();
			try {
				cartSevice.AddShopcart(shopCart); 
				map.put("result", "已添加到购物车");
				return map;
			} catch (Exception e) { 
				map.put("result", "添加失败");
				return map;
			}
		}

	}

}
