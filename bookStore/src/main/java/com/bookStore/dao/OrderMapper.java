package com.bookStore.dao;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.T;

import com.bookStore.dao.model.Order;

public interface OrderMapper {

	void addOrder(Order order);

	List<Order> listOrder();

	List<Map<Object, Object>> listExtendOrder();

	void delOrder(int orderId);

	List<Map<Object, Object>> listExtendOrdercensus();

}
