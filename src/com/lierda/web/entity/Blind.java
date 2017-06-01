package com.lierda.web.entity;



/**
 * 窗帘
 * @author  qianjiawei
 *
 */
public class Blind {
	/**窗帘状态*/
	private String status="OFF";
	/**窗帘动作*/
	private String action="";
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Blind(String status, String action) {
		super();
		this.status = status;
		this.action = action;
	}
	public Blind() {
		super();
		// TODO Auto-generated constructor stub
	}
}
