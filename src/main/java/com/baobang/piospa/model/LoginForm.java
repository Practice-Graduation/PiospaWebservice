package com.baobang.piospa.model;
/**
  * @author BaoBang
  * @Created Apr 18, 2018
  * 
  */
public class LoginForm {

	private String username;
	private String password;
	public LoginForm(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public LoginForm() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
