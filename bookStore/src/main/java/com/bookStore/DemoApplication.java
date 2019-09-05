package com.bookStore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@Controller
@MapperScan("com.bookStore.dao")
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
    //@ResponseBody
	@RequestMapping("/login")
	public String addbook() { 
		return "login";

	} 
	@RequestMapping("/logina")
	public String logina() { 
		return "logina";
		
	} 
	
	@RequestMapping("/upfaile")
	public String upfaile() { 
		return "upfaile";
		
	} 
	
	@RequestMapping("/indexa")
	public String indexa() { 
		return "indexa";
	} 
	@RequestMapping("/indexb")
	public String indexb() {
		System.out.println("hhhhhhh");
		return "shouye/index";
	} 
	@RequestMapping("/index")
	public String index() {
		System.out.println("hhhhhhh");
		return "index";
	} 

	

}
