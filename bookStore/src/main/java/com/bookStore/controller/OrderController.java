package com.bookStore.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookStore.dao.model.Book;
import com.bookStore.dao.model.Customer;
import com.bookStore.dao.model.Order;
import com.bookStore.sevice.BookService;
import com.bookStore.sevice.CustomerService;
import com.bookStore.sevice.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private BookService bookService;
	@Autowired
	private CustomerService customerService;

	@ResponseBody
	@RequestMapping("/delOrder")
	public Map<String, String> delOrder(int orderId) {
		System.out.println("------删除订单---------");
		System.out.println("orderId====" + orderId);
		Map<String, String> map = new HashMap<String, String>();
		orderService.delOrder(orderId);
		map.put("result", "删除成功");
		return map;
	}

	@ResponseBody
	@RequestMapping("/orderCensus")
	public Map<String, List<Object>> orderCensus() {
		Map<String, List<Object>> map = new HashMap<>();
		List<Object> xList = new ArrayList<>();
		List<Object> dList = new ArrayList<>();
		List<Map<Object, Object>> crs = orderService.listExtendOrdercensus();
		for (Map<Object, Object> cr : crs) {
			xList.add(cr.get("cusName"));
			dList.add(cr.get("sumPrice")); //
		}
		map.put("x", xList);
		map.put("d", dList);
		return map;
	}

	@ResponseBody
	@RequestMapping("/listOrder")
	public Map<String, Object> listOrder(int page, int rows) {

		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(page, rows);// 利用Pagehelper插件进行数据库查询分页
		List<Order> list = orderService.listOrder();

		List<Map<Object, Object>> lista = orderService.listExtendOrder();

		for (Map<Object, Object> cr : lista) {
			System.out.println(cr.get("cusName"));
		}

		PageInfo<Map<Object, Object>> pageInfo = new PageInfo<>(lista);
		map.put("total", pageInfo.getTotal());
		map.put("rows", lista);

		return map;
	}

	// @ResponseBody
	@RequestMapping("/CreateOrder") // 创建订单，获取用户和图书信息， 修改book表的数量，创建order数量
	public String CreateOrder(Customer customer, Book book, Integer bookNum, Order ordera) {

		try {
			if (customer.getCusId() == 0 || book.getBookId() == null || bookNum == null) {
				return "redirect:../index";
			}
			Order order = new Order();
			order.setCusId(customer.getCusId());
			order.setBookId(book.getBookId());
			order.setProductName(book.getBookName());
			order.setProductNum(bookNum);
			order.setProductPrice(book.getBookPrice());
			order.setCusAddr(customer.getCusAddr());
			int productDiscount = 1;
			try {
				if (ordera.getProductDiscount() == null || ordera.getProductDiscount().equals("")) {
					productDiscount = 1;
				} else {
					productDiscount = Integer.valueOf(ordera.getProductDiscount()).intValue();
				}
			} catch (Exception e) {
				productDiscount = 1;
			}
			order.setProductDiscount("" + productDiscount);
			double price = Double.parseDouble(book.getBookPrice());
			java.text.DecimalFormat myformat = new java.text.DecimalFormat("#0.00");
			price = Double.parseDouble(myformat.format(price));
			double amount = 0;
			amount = price * bookNum * productDiscount;
			amount = Double.parseDouble(myformat.format(amount));
			order.setOrderAmount("" + amount);
			java.util.Calendar c = java.util.Calendar.getInstance();
			java.text.SimpleDateFormat f = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			order.setOrderDate(f.format(c.getTime()));// 当前日期
			System.out.println("========================");
			System.out.println(order.getOrderAmount());
			System.out.println(order.getOrderDate());
			System.out.println(order.getProductDiscount());
			System.out.println(order.getProductName());
			System.out.println(order.getProductNum());
			System.out.println(order.getProductPrice());
			System.out.println(order.getBookId());
			System.out.println(order.getCusId());
			System.out.println(order.getCusAddr());
			bookService.minusBookNum(bookNum, book.getBookId());
			orderService.addOrder(order);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", "添加成功,即将返回首页");
			return "redirect:success.html";
		} catch (Exception e) {
			return "redirect:../index";
		}
	}

	@RequestMapping("order")
	public String Book(String bookId) {
		System.out.println("跳转订单页面");
		Book book = bookService.getSelectID(bookId);

		System.out.println("aaaaaaaaa" + bookId);
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
		return "order/order";
	}

}
