package com.lierda.web.entity;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

/**
 * 人感
 * @author qianjiawei
 *
 */
public class SenseHuman {
	/**人感状态*/
	private String status="OFF";

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SenseHuman(String status) {
		super();
		this.status = status;
	}

	public SenseHuman() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
