package com.bookStore.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/index")
public class IndexController {

	@RequestMapping("/provingCus")
	public Map<String, List> provingCus(HttpServletRequest request, HttpServletResponse response) {
		Map<String, List> map = new HashMap<String, List>();
		List<Object> list1 = new ArrayList<>();
		List<Object> list2 = new ArrayList<>();

		Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
		if (cookies == null) {
		} else {
			System.out.println("cookies.length==" + cookies.length);
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("cookieCustomername")) {
					list1.add(cookie.getValue());
				}
				if (cookie.getName().equals("cookieCustomerId")) {
					list2.add(cookie.getValue());
				}
			}
		}
		map.put("cusName", list1);
		map.put("cusId", list2);
		return map;
	}

	@RequestMapping("/out")
	public Map<String, Object> out(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();
		Cookie cookie = new Cookie("cookieCustomerId", null);// cookie名字要相同
		cookie.setMaxAge(0); //
		cookie.setPath("/"); // 相同路径
		response.addCookie(cookie);
		System.out.println( );

		Cookie cookie2 = new Cookie("cookieCustomername", null);// cookie名字要相同
		cookie2.setMaxAge(0); //
		cookie2.setPath("/"); // 相同路径
		response.addCookie(cookie2);
		map.put("result", "注销成功");
		
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
		} else {
			System.out.println("cookies.length==" + cookies.length);
			for (Cookie c : cookies) {
				if (c.getName().equals("cookieCustomername")) {
					System.out.println("清除后用户名为===="+c.getValue());
				}
				if (c.getName().equals("cookieCustomerId")) {
					System.out.println("清除后ID===="+c.getValue());
				}
			}
		}	
		return map;
	}

}
