package com.bookStore.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookStore.dao.model.Book;
import com.bookStore.dao.model.ExcelUtil;
import com.bookStore.sevice.BookService;

@RestController
@CrossOrigin
@RequestMapping("/bookExcel")
public class BookExcelController {

	@Autowired
	private BookService bookService;

	@RequestMapping("/book")
	public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Book> list = new ArrayList<>();
		list = bookService.selectAllBook();

		// excel标题
		String[] title = { "图书编号", "图书名称", "作者", "出版社","类型","子类型", "价格", "数量","封面地址" };
		String fileName = "图书信息表" + System.currentTimeMillis() + ".xls";
		String sheetName = "图书信息表";
		String[][] content = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			content[i] = new String[list.size() + 3];
			Book obj = list.get(i);
			content[i][0] = String.valueOf(obj.getBookId());
			content[i][1] = obj.getBookName();
			content[i][2] = obj.getBookAuthor();
			content[i][3] = obj.getBookPublish();
			content[i][4] = obj.getBookBigType();
			content[i][5] = obj.getBookMinType();
			content[i][6] = obj.getBookPrice();
			content[i][7] = String.valueOf(obj.getBookNum());
			content[i][8] = String.valueOf(obj.getBookImg());

			System.out.println(content[i][1] + "--------" + content[i][5]);
		}

		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

		try {
			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(), "ISO8859-1");// 
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
