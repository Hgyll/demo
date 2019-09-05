package com.bookStore.dao;

import java.util.List;

import com.bookStore.dao.model.Customer;

public interface CustomerMapper {

  	void insertCustomer(Customer customer);

	List<Customer> selectAllCustomer();

	List<Customer> selectLoginUserName(String name);

	void updateCustomer(Customer cus);

	void delCustomer(String cusId);

	Customer cusgetIdInfo(String cusId);

}
