package com.bookStore.dao;

import java.util.List;

import com.bookStore.dao.model.Book;

public interface BookMapper {

	void insertBook(Book book);

	List<Book> selectAllBook();

	void updateBook(Book book);

	void deleteBook(int bookId);

	Book getSelectID(String bookId);

	void minusBookNum(int bookNum, Integer bookId);
	

}
