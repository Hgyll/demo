package com.bookStore.dao.model;

public class Book {
	private Integer bookId;
	private Integer bookNum;
	private String bookName;
	private String bookAuthor;
	private String bookPublish;
	private String bookBigType;
	private String bookMinType;
	private String bookPrice;
	private String bookImg;
	private String imgurl;

	
	
	public String getImgurl() {
		return bookImg;
	}

	public void setImgurl(String bookImg) {
		this.imgurl = bookImg;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getBookNum() {
		return bookNum;
	}

	public void setBookNum(Integer bookNum) {
		this.bookNum = bookNum;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPublish() {
		return bookPublish;
	}

	public void setBookPublish(String bookPublish) {
		this.bookPublish = bookPublish;
	}

	public String getBookBigType() {
		return bookBigType;
	}

	public void setBookBigType(String bookBigType) {
		this.bookBigType = bookBigType;
	}

	public String getBookMinType() {
		return bookMinType;
	}

	public void setBookMinType(String bookMinType) {
		this.bookMinType = bookMinType;
	}

	public String getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getBookImg() {
		return bookImg;
	}

	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookNum=" + bookNum + ", bookName=" + bookName + ", bookAuthor="
				+ bookAuthor + ", bookPublish=" + bookPublish + ", bookBigType=" + bookBigType + ", bookMinType="
				+ bookMinType + ", bookPrice=" + bookPrice + ", bookImg=" + bookImg + "]";
	}

}