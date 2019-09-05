package com.bookStore.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookStore.dao.model.Book;
import com.bookStore.sevice.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;

	@RequestMapping("bookInfo")
	public Book bookInfo(String bookId) {
      System.out.println("book"+bookId);
		try {
			Book book = bookService.getSelectID(bookId);
			return book;
		} catch (Exception e) {
			return null;
		}

	}

	@RequestMapping("/addBook")
	public Map<String, Object> addBook(Book book, HttpServletRequest request) {

		HttpSession sessoina = request.getSession();
		String img = sessoina.getAttribute("img").toString();
		System.out.println("图片" + img);
		book.setBookImg(img);

		bookService.addBook(book);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("result", "添加成功");
		return map;
	}

	@RequestMapping("listTool")
	public List<Book> listTool() {
		System.out.println("工具书区");
		List<Book> list = bookService.selectAllBook();
		System.out.println("list.size() ==" + list.size());
		if (list.size() > 7) {
			List<Book> newList = list.subList(0, 7);// 取前四条数据
			return newList;// 返回新的list
		} else {

			return list;
		}

	}

	@RequestMapping("list")
	public List<Book> list() {
		System.out.println("教科书区");
		List<Book> list = bookService.selectAllBook();
		System.out.println("list.size() ==" + list.size());
		if (list.size() > 15) {
			List<Book> newList = list.subList(0, 15);// 取前四条数据
			return newList;// 返回新的list
		} else {

			return list;
		}

	}

	@RequestMapping("listBook")
	public Map<String, Object> selectAllBook(int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(page, rows);// 利用Pagehelper插件进行数据库查询分页
		List<Book> list = bookService.selectAllBook();

		for (Book la : list) {
			System.out.println(la.getBookImg());
			System.out.println(la.getImgurl());

		}

		PageInfo<Book> pageInfo = new PageInfo<>(list);

		map.put("total", pageInfo.getTotal());
		map.put("rows", list);

		return map;
	}

	@RequestMapping("/updateBook")
	public Map<String, String> updateBook(Book book) {
		Map<String, String> map = new HashMap<String, String>();
		bookService.updateBook(book);
		map.put("result", "修改成功");
		return map;
	}

	@RequestMapping("/delBook")
	public Map<String, String> delBook(int bookId) {
		System.out.println("---------------");
		System.out.println("bookID====" + bookId);
		Map<String, String> map = new HashMap<String, String>();
		bookService.delBook(bookId);
		map.put("result", "删除成功");
		return map;
	}

}
