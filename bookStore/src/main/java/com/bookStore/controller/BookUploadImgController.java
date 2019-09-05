package com.bookStore.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookStore.dao.model.Book;
import com.bookStore.sevice.BookService;

@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookUploadImgController {
	
	public Map<String, Object> uploadImg(){
		
		 Map<String, Object> map=new HashMap<>();
		 
		 map.put("result","上传成功");
		 return map;
	}
	
	

	@Autowired
	private BookService bookService;
	
	  @RequestMapping("/uploadImg")
	   public Map<String, Object> exImport(@RequestParam(value = "fileImg")MultipartFile file,  Model model, HttpSession session) {
	      System.out.println("上传图片");
	      
	      if (file.isEmpty()) {
	            System.out.println("文件为空空");
	        }
	        String fileName = file.getOriginalFilename();  // 文件名
	        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
	        String filePath = "D://课程软件//spring//bookStore//src//main//resources//static//book//img//";
	        // 上传后的路径  D:\课程软件\spring\bookStore\src\main\resources\static\book\img
	        //String filePath = "D://temp-rainy//"; // 上传后的路径
	        fileName = UUID.randomUUID() + suffixName; // 新文件名
	        File dest = new File(filePath + fileName);
	        if (!dest.getParentFile().exists()) {
	            dest.getParentFile().mkdirs();
	        }
	        try {
	            file.transferTo(dest);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	       
	        
	        String filenamea = "/temp-rainy/" + fileName;
	       
	        model.addAttribute("filename", filenamea);
 
	      
//		  boolean a = false;
//	      String fileName = file.getOriginalFilename();
//	      try {
//	         a = bookService.uploadImg(fileName, file);
//	      } catch (Exception e) {
//	         e.printStackTrace();
//	      }
 	      Map<String, Object> map=new HashMap<String, Object>();
	      map.put("result", "上传成功");
	      String filename = "" + fileName;
	      System.out.println(filename);
	      map.put("upImg", filename);
	      return map;
	   }
	  
	  
	
	  @RequestMapping("/shangchuanImg")//Model model
	  public Map<String, Object> shangchuanImg(@RequestParam(value = "upload")MultipartFile file,  Model model, HttpSession session) {
		  System.out.println("上传图片");
		  
		  if (file.isEmpty()) {
			  System.out.println("文件为空空");
		  }
		  String fileName = file.getOriginalFilename();  // 文件名
		  String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
		  String filePath = "D://课程软件//spring//bookStore//src//main//resources//static//book//img//";
		  // 上传后的路径  D:\课程软件\spring\bookStore\src\main\resources\static\book\img
		  //String filePath = "D://temp-rainy//"; // 上传后的路径
		  fileName = UUID.randomUUID() + suffixName; // 新文件名
		  File dest = new File(filePath + fileName);
		  if (!dest.getParentFile().exists()) {
			  dest.getParentFile().mkdirs();
		  }
		  try {
			  file.transferTo(dest);
		  } catch (IOException e) {
			  e.printStackTrace();
		  }
		  
		  
		  String filenamea = "/temp-rainy/" + fileName;
		  
		  model.addAttribute("filename", filenamea);
		  
		  
//		  boolean a = false;
//	      String fileName = file.getOriginalFilename();
//	      try {
//	         a = bookService.uploadImg(fileName, file);
//	      } catch (Exception e) {
//	         e.printStackTrace();
//	      }
		  Map<String, Object> map=new HashMap<String, Object>();
		  map.put("result", "上传成功");
		  String filename = "" + fileName;
		  System.out.println(filename);
		  map.put("upImg", filename);
		  return map;
	  }
	  
	
	  
	  
	  @RequestMapping("/shangchuanImgdemo")//Model model
	  public Map<String, Object> shangchuanImgdemo(Book book,@RequestParam(value = "upload")MultipartFile file, HttpServletRequest request, HttpSession session) {
		  System.out.println("上传图片");
		  
		  System.out.println(book.getBookAuthor());
		  System.out.println(book.getBookPublish());
		  
		  
		  if (file.isEmpty()) {
			  System.out.println("文件为空空");
		  }
		  String fileName = file.getOriginalFilename();  // 文件名
		  String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
		  String filePath = "D://课程软件//spring//bookStore//src//main//resources//static//book//img//";
		  // 上传后的路径  D:\课程软件\spring\bookStore\src\main\resources\static\book\img
		  //String filePath = "D://temp-rainy//"; // 上传后的路径
		  fileName = UUID.randomUUID() + suffixName; // 新文件名
		  File dest = new File(filePath + fileName);
		  if (!dest.getParentFile().exists()) {
			  dest.getParentFile().mkdirs();
		  }
		  try {
			  file.transferTo(dest);
		  } catch (IOException e) {
			  e.printStackTrace();
		  }
		  
//		  boolean a = false;
//	      String fileName = file.getOriginalFilename();
//	      try {
//	         a = bookService.uploadImg(fileName, file);
//	      } catch (Exception e) {
//	         e.printStackTrace();
//	      }
		  Map<String, Object> map=new HashMap<String, Object>();
		  map.put("result", "上传成功");
		  String filename = "" + fileName;
		  System.out.println(filename);
		  
		  HttpSession sessoina=request.getSession();//这就是session的创建
		  sessoina.setAttribute("img",filename);//session.getAttribute("img");
		  map.put("upImg",filename);
		  return map;
	  }
	  
	  
	  
}
