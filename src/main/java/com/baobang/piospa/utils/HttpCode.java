package com.baobang.piospa.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

/**
  * @author BaoBang
  * @Created Apr 14, 2018
  * 
  */
public enum HttpCode {
	OK(200, "Ok"),
	CREATED(201, "Created"),
	ACCEPTED(202, "Accepted"),
	NO_CONTENT(204, "No Content"),
	BAD_REQUEST(400, "Bad Request"),
	UNAUTHORIZED(401, "Unauthorized"),
	NOT_FOUND(404, "Not Found"),
	NOT_IMPLEMENTED(501, "Not Implemented");
	
	private int code;
	@JsonIgnore
	private String description;
	
	@JsonCreator
	private HttpCode(int code, String description) {
		this.code = code;
		this.description = description;
	}
	public String getDescription() {
		return description;
	}


	@JsonValue
	public int getCode() {
		return code;
	}
	
	
}
