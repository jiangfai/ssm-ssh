package com.qfedu.house.dto;

import javax.validation.Valid;

import com.qfedu.house.domain.User;

public class UserDTO {

	@Valid
	private User user;
	private String ipAdress;
	
	private String code;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
