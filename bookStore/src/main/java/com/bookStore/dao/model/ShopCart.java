package com.bookStore.dao.model;

public class ShopCart {

	private Integer shoppingId;
	private Integer cusId;
	private Integer bookId;
	private String bookName;
	private String shopDate;

	public Integer getShoppingId() {
		return shoppingId;
	}

	public void setShoppingId(Integer shoppingId) {
		this.shoppingId = shoppingId;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getShopDate() {
		return shopDate;
	}

	public void setShopDate(String shopDate) {
		this.shopDate = shopDate;
	}

	@Override
	public String toString() {
		return "ShopCart [shoppingId=" + shoppingId + ", cusId=" + cusId + ", bookId=" + bookId + ", bookName="
				+ bookName + ", shopDate=" + shopDate + "]";
	}

}
