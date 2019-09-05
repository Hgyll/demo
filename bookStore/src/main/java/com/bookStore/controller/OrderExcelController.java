package com.bookStore.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookStore.dao.model.Book;
import com.bookStore.dao.model.ExcelUtil;
import com.bookStore.dao.model.Order;
import com.bookStore.sevice.OrderService;

@RestController
@CrossOrigin
@RequestMapping("/orderExcel")
public class OrderExcelController {
	@Autowired
	private OrderService orderService;
	
	
	@RequestMapping("/order")
	public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Map<Object, Object>> list = new ArrayList<>();
		list = orderService.listExtendOrder();

		// excel标题
		String[] title = { "订单编号", "图书编号", "用户编号", "用户姓名","图书名","价格", "折扣", "数量","总金额","交易时间","地址" };
		String fileName = "订单信息表" + System.currentTimeMillis() + ".xls";
		String sheetName = "订单信息表";
		String[][] content = new String[list.size()][];
		System.out.println("  "+list.size());
		for (int i = 0; i < list.size(); i++) {
			content[i] = new String[list.size() + 7];
			
			Map<Object, Object> obj = list.get(i);
			        content[i][0] =  String.valueOf(obj.get("orderId"));
					content[i][1] =  String.valueOf( obj.get("bookId"));
					content[i][2] =  String.valueOf( obj.get("cusId"));
					content[i][3] =  (String) obj.get("cusName");//
					content[i][4] =  (String) obj.get("productName");
					content[i][5] =  (String) obj.get("productPrice");
					content[i][6] =  (String) obj.get("productDiscount");
					content[i][7] =  String.valueOf(obj.get("productNum"));
					content[i][8] =  (String) obj.get("orderAmount");
					content[i][9] =  (String) obj.get("orderDate");
					
					content[i][10] =  (String) obj.get("cusAddr");

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
