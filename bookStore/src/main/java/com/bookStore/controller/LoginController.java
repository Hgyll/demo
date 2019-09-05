package com.bookStore.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookStore.dao.model.Customer;
import com.bookStore.sevice.CustomerService;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private CustomerService customerService;
   
	@RequestMapping("getCookie")
	public Map<String,List> getCookie( HttpServletRequest request,
			HttpServletResponse response){
		Map<String, List> map = new HashMap<String, List>();
		List<Object> list1=new ArrayList<>();
		List<Object> list2=new ArrayList<>();
		
		Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
		if (cookies == null) {
		} else {
           System.out.println("cookies.length=="+cookies.length);
			for (Cookie cookie : cookies) {
				System.out.println("cookie.getName=====" + cookie.getName()); // get the cookie name
		 		System.out.println("cookie.getValue====" + cookie.getValue()); // get the cookie value
			 
				if(cookie.getName().equals("cookiecusName")) {
					list1.add(cookie.getValue());
				}
				if(cookie.getName().equals("cookiecusPassword")) {
					list2.add(cookie.getValue());
				}
				
			}
		}
	    map.put("username", list1);
	    map.put("password", list2);	
		return map;
	}
	
	
	
	@RequestMapping("/cusInfo")
	public Map<String, Object> login(String name, String password, String fxk, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<Customer> list = customerService.selectLoginUserName(name);
		System.out.println(list.size());

		//获取浏览器里cookie存在的值
		Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
		if (cookies == null) {
		} else {
           System.out.println("cookies.length=="+cookies.length);
			for (Cookie cookie : cookies) {
				System.out.println("cookie.getName=====" + cookie.getName()); // get the cookie name
				System.out.println("cookie.getValue====" + cookie.getValue()); // get the cookie value
			}
		}
		
		if (list.size() >= 1) {//判断用户是否存在
			for (Customer cus : list) {

				System.out.println("输入的+" + password.toString());
				System.out.println("查询到的" + cus.getCusPassword().toString());
				System.out.println("判断=" + password.equals(cus.getCusPassword().toString()));
				// ！！ a==b判断的是两个字符串的是否指向相同的对象
				HttpSession session = request.getSession();

				session.setAttribute("cusId", cus.getCusId());

				String sessionId = session.getId();
				System.out.println("sessionId=" + sessionId);
				if (password.equals(cus.getCusPassword().toString())) {
					
					 System.out.println("复选框=="+fxk);
					 if(fxk.equals("true")) { //复选框里有值，则记住用户名和密码30天
						 
							// 设置cookie
							Cookie cookie = new Cookie("cookiecusName", name);
							cookie.setMaxAge(30*24*60*60);//设置记住时间为30天
							cookie.setPath("/");
							response.addCookie(cookie);
							Cookie cookie2 = new Cookie("cookiecusPassword", password);
							cookie2.setMaxAge(1*24*60*60);//设置记住时间为1天
							cookie2.setPath("/");
							response.addCookie(cookie2);
					 }
					
					// 设置cookie 如果没有设置，则设置记住时间为1天
					Cookie cookie = new Cookie("cookiecusName", name);
					cookie.setMaxAge(1*24*60*60);//设置账户记住时间为1天
					cookie.setPath("/");
					response.addCookie(cookie);
					Cookie cookie2 = new Cookie("cookiecusPassword", password);
					cookie2.setMaxAge(1*24*60*60);//设置密码记住时间为1天
					cookie2.setPath("/");
					response.addCookie(cookie2);	
					
					Cookie cookie3 = new Cookie("cookieCustomerId",String.valueOf(cus.getCusId()));
					cookie3.setMaxAge(1*24*60*60);//设置密码记住时间为1天
					cookie3.setPath("/");
					response.addCookie(cookie3);		
					
					Cookie cookie4 = new Cookie("cookieCustomername",cus.getCusName());
					cookie4.setMaxAge(1*24*60*60);//设置密码记住时间为1天
					cookie4.setPath("/");
					response.addCookie(cookie4);		
					
					
					map.put("result", "登录成功");
					if (cus.getCusType().equals("VIP客户")) {
						map.put("result", "VIP客户");
					}
					if (cus.getCusType().equals("管理员")) {
						map.put("result", "管理员");
					}
					if (cus.getCusType().equals("普通客户")) {
						map.put("result", "普通客户");
					}
					return map;
				}
			}
			map.put("result", "密码错误");
			return map;
		}
		map.put("result", "用户名错误");
		return map;
	}

}
