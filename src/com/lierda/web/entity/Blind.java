package com.lierda.web.entity;
/**
 * 窗帘
 * @author  qianjiawei
 *
 */
public class Blind {
	/**窗帘状态*/
	private String status="OFF";

	public Blind(String status) {
		super();
		this.status = status;
	}

	public Blind() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
