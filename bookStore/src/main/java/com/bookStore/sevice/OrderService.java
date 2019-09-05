package com.bookStore.sevice;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.dao.OrderMapper;
import com.bookStore.dao.model.Order;

@Service
public class OrderService {
	@Autowired
	private OrderMapper orderMapper;

	public void addOrder(Order order) {
	 
		orderMapper.addOrder(order);
	}

	public List<Order> listOrder() {
		// TODO Auto-generated method stub
		return orderMapper.listOrder();
	}

	public List<Map<Object, Object>> listExtendOrder() { 
		return  orderMapper.listExtendOrder();
	}
	public List<Map<Object, Object>> listExtendOrdercensus() { 
		return  orderMapper.listExtendOrdercensus();
	}

	public void delOrder(int orderId) {
		orderMapper.delOrder(orderId);
		
	}
	
	
}
