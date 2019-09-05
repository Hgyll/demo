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

import com.bookStore.dao.model.Customer;
import com.bookStore.dao.model.ExcelUtil;
import com.bookStore.sevice.CustomerService;

@RestController
@CrossOrigin
@RequestMapping("/CustomerExcel")
public class CustomerExcelController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/customer")
	public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Customer> list = new ArrayList<>();
		list = customerService.getAllCustomer();

		// excel标题
		String[] title = { "用户编号", "用户名称", "用户密码", "地址","电话","用户类型" };
		String fileName = "用户信息表" + System.currentTimeMillis() + ".xls";
		String sheetName = "用户信息表";
		String[][] content = new String[list.size()][];

		System.out.println("用户长度：  " + list.size());
		for (int i = 0; i < list.size(); i++) {
			content[i] = new String[list.size() + 3];  //每次使用时都要实例化长度
			Customer obj = list.get(i);
//					content[i][0] = String.valueOf(obj.getCusId());
//					content[i][1] =  obj.getProductName().toString();  
//					content[i][2] = obj.getProductDiscount().toString(); 
//					content[i][3] = String.valueOf(obj.getProductNum());
//					content[i][4] = obj.getOrderAmount().toString();
//					content[i][5] = obj.getOrderDate().toString();
			content[i][0] = String.valueOf(obj.getCusNo());
			content[i][1] = obj.getCusName();
			content[i][2] = obj.getCusPassword();
			content[i][3] = obj.getCusAddr();
			content[i][4] = obj.getCusPhone();
			content[i][5] = obj.getCusType();
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
