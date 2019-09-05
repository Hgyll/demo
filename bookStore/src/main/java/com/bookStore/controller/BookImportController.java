package com.bookStore.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookStore.sevice.BookService;

@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookImportController {

	@Autowired
	private BookService bookService;
	
	  @RequestMapping("/import")
	   public Map<String, Object> exImport(@RequestParam(value = "filename")MultipartFile file, HttpSession session) {
	      System.out.println("导入数据");
		  boolean a = false;
	      String fileName = file.getOriginalFilename();
	      try {
	         a = bookService.batchImport(fileName, file);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      Map<String, Object> map=new HashMap<String, Object>();
	      map.put("result", "导入成功");
	      return map;
	   }
	 
}
