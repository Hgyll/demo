package com.bookStore.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bookStore.dao.model.Customer;

public class UploadFileController {

//	@RequestMapping("importUsers")
//	@ResponseBody
//	public String importUsers(@RequestParam MultipartFile file,Integer clientid,HttpServletRequest request) throws IOException{
//		
//		boolean FLAG;//身份状态
//		
//		List<Customer> list = new ArrayList<Customer>();
//		XSSFWorkbook workbook =null;
//		
//		//把MultipartFile转化为File
//		CommonsMultipartFile cmf= (CommonsMultipartFile)file;
//		DiskFileItem dfi=(DiskFileItem) cmf.getFileItem();
//		File fo=dfi.getStoreLocation();
// 
//		//创建Excel，读取文件内容
//		  workbook = new XSSFWorkbook(FileUtils.openInputStream(fo));
// 
//		//获取第一个工作表
//		XSSFSheet sheet = workbook.getSheet("学员信息");
//		
//		//获取sheet中第一行行号
//		int firstRowNum = sheet.getFirstRowNum();
//		//获取sheet中最后一行行号
//		int lastRowNum = sheet.getLastRowNum();
//		
//		try {
//			//循环插入数据
//			for(int i=firstRowNum+1;i<=lastRowNum;i++){
//				XSSFRow row = sheet.getRow(i);
//				
//				Customer users = new Customer();	
//				XSSFCell username = row.getCell(0);//学员名称
//				if(username!=null){
//					username.setCellType(Cell.CELL_TYPE_STRING);
//					users.setCusName(username.getStringCellValue());
//				}
//				
//				XSSFCell phone = row.getCell(1);//联系方式
//				if(phone!=null){
//					phone.setCellType(Cell.CELL_TYPE_STRING);
//					users.setCusPhone((phone.getStringCellValue()));
//				}
//				
//				XSSFCell post = row.getCell(2);//职位
//				if(post!=null){
//					post.setCellType(Cell.CELL_TYPE_STRING);
//					users.setCusType((post.getStringCellValue()));
//				}
//				
//				XSSFCell identitys = row.getCell(3);//身份
//			       if(identitys!=null) {
//			    	   identitys.setCellType(Cell.CELL_TYPE_STRING);
//			    	   users.setCusType((identitys.getStringCellValue()));
//			       }					
//			       list.add(users);
//				}
// 
//			//usersMapper.insert(list);//往数据库插入数据
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			//workbook.close();
//	     // workbook.
//		     
//		}
//		
//	} 
	 
}
