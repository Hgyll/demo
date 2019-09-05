package com.bookStore.dao.model;

public class Customer {

	private int cusId;
	private int cusNo;
	private String cusPhone;
	private String cusName;
	private String cusPassword;
	private String cusAddr;
	private String cusType;

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getCusNo() {
		return cusNo;
	}

	public void setCusNo(int cusNo) {
		this.cusNo = cusNo;
	}

 

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusPassword() {
		return cusPassword;
	}

	public void setCusPassword(String cusPassword) {
		this.cusPassword = cusPassword;
	}

	public String getCusAddr() {
		return cusAddr;
	}

	public void setCusAddr(String cusAddr) {
		this.cusAddr = cusAddr;
	}

	public String getCusType() {
		return cusType;
	}

	public void setCusType(String cusType) {
		this.cusType = cusType;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	@Override
	public String toString() {
		return "Customer [cusId=" + cusId + ", cusNo=" + cusNo + ", cusPhone=" + cusPhone + ", cusName=" + cusName
				+ ", cusPassword=" + cusPassword + ", cusAddr=" + cusAddr + ", cusType=" + cusType + "]";
	}

}
