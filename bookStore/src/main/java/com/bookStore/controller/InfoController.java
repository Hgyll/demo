package com.bookStore.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bookStore.dao.model.Book;

import com.bookStore.sevice.BookService;

@CrossOrigin
@Controller
@RequestMapping("Store")
public class InfoController {

	@Autowired
	private BookService bookService;

	@RequestMapping("BookmessageInfo")
	public String Book(String bookId) {
		Book book = bookService.getSelectID(bookId);
        
		System.out.println("aaaaaaaaa"+bookId);
		try {
			if (book.getBookId() == null) {
				System.out.println("11");
				return "index";				
			}
		} catch (Exception e) {
			System.out.println("222");
			return "redirect:../index";
		}
		 
		System.out.println("333333");
		return "Store/shopdetail";
	}

}
