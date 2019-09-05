package com.bookStore.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.dao.CustomerMapper;
import com.bookStore.dao.model.Customer;

@Service
public class CustomerService {
	
	
	@Autowired
	private CustomerMapper customerMapper;
	
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerMapper.insertCustomer(customer);
	}

	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return customerMapper.selectAllCustomer();
	}

	public List<Customer> selectLoginUserName(String name) {
		// TODO Auto-generated method stub
		return customerMapper.selectLoginUserName(name);
	}

	public void updateCustomer(Customer cus) {
		customerMapper.updateCustomer(cus);
		
	}

	public void delCustomer(String cusId) {
		// TODO Auto-generated method stub
		customerMapper.delCustomer(cusId);
	}

	public Customer cusgetIdInfo(String cusId) {
	   return 	customerMapper.cusgetIdInfo(cusId);
	}

	 

}
