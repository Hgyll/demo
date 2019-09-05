package com.bookStore.dao;

import java.util.List;

import com.bookStore.dao.model.ShopCart;

public interface ShopCartMapper {

	List<ShopCart> getlistBycusId(Integer cusId);

	void AddShopcart(ShopCart shopCart);

	List<ShopCart> getlistBycusIdandBookId(int cusId, int bookId);

}
