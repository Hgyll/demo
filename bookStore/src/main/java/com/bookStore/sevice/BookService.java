package com.bookStore.sevice;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bookStore.dao.BookMapper;
import com.bookStore.dao.model.Book;
import com.bookStore.dao.model.MyException;

@Service
public class BookService {

	@Autowired
	private BookMapper bookMapper;
	
	public void addBook(Book book) {
		 
		bookMapper.insertBook(book);
	}

	public List<Book> selectAllBook() { 
		return bookMapper.selectAllBook();
	}

	public void updateBook(Book book) {
		 
		bookMapper.updateBook(book);
	}

	public void delBook(int bookId) {
		bookMapper.deleteBook(bookId);
		
	}
	
	public boolean uploadImg(String fileName, MultipartFile file) throws Exception{
		
		return false;
	}
	
	

	@Transactional(readOnly = false,rollbackFor = Exception.class)
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		 boolean notNull = false;
	      List<Book> bookList = new ArrayList<>();
	      if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
	         throw new MyException("上传文件格式不正确");
	      }
	      boolean isExcel2003 = true;
	      if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
	         isExcel2003 = false;
	      }
	      InputStream is = file.getInputStream();
	      Workbook wb = null;
	      if (isExcel2003) {
	         wb = new HSSFWorkbook(is);
	      } else {
	         wb = new XSSFWorkbook(is);
	      }
	      Sheet sheet = wb.getSheetAt(0);
	      if(sheet!=null){
	         notNull = true;
	      }
	      Book book;
	      for (int r = 1; r <= sheet.getLastRowNum(); r++) {//r = 1表示从第二行开始循环 如果你的第三行开始是数据
	         Row row = sheet.getRow(r);//通过sheet表单对象得到 行对象
	         if (row == null){
	            continue;
	         }
	         //sheet.getLastRowNum() 的值是 10，所以Excel表中的数据至少是10条；不然报错 NullPointerException
	         book = new Book();
	         if( row.getCell(0).getCellType() !=1){//循环时，得到每一行的单元格进行判断
	            throw new MyException("导入失败(第"+(r+1)+"行,用户名请设为文本格式)");
	         }
	         
	         
	         String bookname = row.getCell(0).getStringCellValue();//得到每一行第一个单元格的值
	         if(bookname == null || bookname.isEmpty()){//判断是否为空
	            throw new MyException("导入失败(第"+(r+1)+"行,用户名未填写)");
	         }
	         
	         row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
	         String  bookAuthor= row.getCell(1).getStringCellValue();
	         if(bookAuthor==null || bookAuthor.isEmpty()){
	            throw new MyException("导入失败(第"+(r+1)+"行,密码未填写)");
	         }
	         
	         row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
	         String bookPublish = row.getCell(2).getStringCellValue();
	         if(bookPublish==null || bookPublish.isEmpty()){
	        	 throw new MyException("导入失败(第"+(r+1)+"行,密码未填写)");
	         }
	         row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
	         String bookBigType = row.getCell(3).getStringCellValue();
	         if(bookBigType==null || bookBigType.isEmpty()){
	        	 throw new MyException("导入失败(第"+(r+1)+"行,密码未填写)");
	         }
	         row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
	         String bookMinType = row.getCell(4).getStringCellValue();
	         if(bookMinType==null || bookMinType.isEmpty()){
	        	 throw new MyException("导入失败(第"+(r+1)+"行,密码未填写)");
	         }
	         row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
	         String bookPrice = row.getCell(5).getStringCellValue();
	         if(bookPrice==null || bookPrice.isEmpty()){
	        	 throw new MyException("导入失败(第"+(r+1)+"行,密码未填写)");
	         }
	         row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
	         String booknum = row.getCell(6).getStringCellValue();
	         if(booknum==null || booknum.isEmpty()){
	        	 throw new MyException("导入失败(第"+(r+1)+"行,密码未填写)");
	         }
	         row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
	         String img = row.getCell(7).getStringCellValue();
	         if(img==null || img.isEmpty()){
	        	 throw new MyException("导入失败(第"+(r+1)+"行,密码未填写)");
	         }
	         	         
	         
	         //完整的循环一次 就组成了一个对象
//	         user.setUsername(username);
//	         user.setPassword(password);
//	         bookList.add(user);
	          
	         book.setBookName(bookname);
	         book.setBookAuthor(bookAuthor);
	         book.setBookPublish(bookPublish);
	         book.setBookBigType(bookBigType);
	         book.setBookMinType(bookMinType);
	         book.setBookPrice(bookPrice);
	         book.setBookNum(Integer.parseInt(booknum));
	         book.setBookImg(img);
	         bookList.add(book);
	         
	      }
	      for (Book bookResord : bookList) {
	         //String name = bookResord.getBookName();
	         
	           bookMapper.insertBook(bookResord);
               System.out.println(" 插入 "+bookResord);
	         
//	         int cnt = bookMapper.selectByName(name);
//	         if (cnt == 0) {
//	            userMapper.addUser(bookResord);
//	            System.out.println(" 插入 "+bookResord);
//	         } else {
//	            userMapper.updateUserByName(bookResord);
//	            System.out.println(" 更新 "+bookResord);
//	         }
	      }
	      return notNull;
	   }

	public Book getSelectID(String bookId) {
		 
		return bookMapper.getSelectID(bookId);
	}

	public void minusBookNum(int bookNum, Integer bookId) {
		 
		bookMapper.minusBookNum(bookNum,bookId);
	} 
	 

}
