package com.bookStore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookStore.dao.model.Customer;
import com.bookStore.sevice.CustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@ResponseBody
	@RequestMapping("/addCustomer")
     public Map<String,String> addCustomer(Customer customer){		
		System.out.println("电话"+customer.getCusPhone());
		  String num =customer.getCusPhone();
		  System.out.println(num);
    	 Map<String,String>  map=new HashMap<String,String> ();  	 
    	 customerService.addCustomer(customer);
    	 map.put("result","添加成功");  	
    	 return map;   	 
     }
	
	@RequestMapping("/listCustomer")
	public Map<String, Object> getAllCustomer(int page,int rows){//page , rows是easyui的datagrid提交的参数
		System.out.println("查询所有");
		PageHelper.startPage(page, rows);//利用Pagehelper插件进行数据库查询分页
		List<Customer> list =customerService.getAllCustomer();
		//创建分页相关信息对象，参数是数据库返回的list
		PageInfo<Customer> pageInfo = new PageInfo(list);
		//因为datagrid前段组件需要的JSON数据有total和rows两个属性
		//total是表里的总记录数(用PageInfo对象的getTotal方法获取)；rows是当前页需要显示的数据列表
		Map<String,Object> map = new HashMap<>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", list);
		return map;
	}
	
	@RequestMapping("/updateCustomer")
	public Map<String,String> updateCustomer(Customer cus) {
		System.out.println(" getCusId "+cus.getCusId());
		System.out.println(" getCusId "+cus.getCusNo());
		System.out.println(" getCusId "+cus.getCusName());
		System.out.println(" getCusId "+cus.getCusPassword());
		System.out.println(" getCusId "+cus.getCusPhone());
		System.out.println(" getCusId "+cus.getCusType());
		customerService.updateCustomer(cus);
		Map<String,String> map = new HashMap<>();
		map.put("result", "修改成功");    
		return map;
	}
	
	@RequestMapping("/delCustomer")
	public Map<String,String > delCustomer(String cusId){
		customerService.delCustomer(cusId);
		Map<String,String> map = new HashMap<>();
		map.put("result", "删除成功");
		return map;
	}
	
	
	@RequestMapping("/cusgetIdInfo")
	public Customer cusgetIdInfo(String cusId){
		System.out.println("通过ID查询信息");
		 try {
			  Customer cus=	customerService.cusgetIdInfo(cusId);
			  return cus; 
		} catch (Exception e) { 
			 return null; 
		}
    	  
	}
	
	
	
}
