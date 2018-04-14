package com.baobang.piospa.model;


public class DataResult <E> {
	private int statusCode;
	private String message;
	private E data;

	public DataResult() {
		super();
	}

	public DataResult(int statusCode, String message, E data) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	
}
