package com.qf.domain;

import java.io.Serializable;



public class Contacter implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private String contacterName;

	private String contacterTel;

	private String contacterQQ;

	private String contacterEmail;

	public String getContacterName() {
		return contacterName;
	}

	public void setContacterName(String contacterName) {
		this.contacterName = contacterName;
	}

	public String getContacterTel() {
		return contacterTel;
	}

	public void setContacterTel(String contacterTel) {
		this.contacterTel = contacterTel;
	}

	public String getContacterQQ() {
		return contacterQQ;
	}

	public void setContacterQQ(String contacterQQ) {
		this.contacterQQ = contacterQQ;
	}

	public String getContacterEmail() {
		return contacterEmail;
	}

	public void setContacterEmail(String contacterEmail) {
		this.contacterEmail = contacterEmail;
	}

}
