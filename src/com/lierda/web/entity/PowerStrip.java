package com.lierda.web.entity;
/**
 * 插座
 * @author  qianjiawei
 *
 */
public class PowerStrip {
	/**插座状态*/
	private String status="OFF";
	/**打开的插座数量*/
	private int count;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public PowerStrip(String status, int count) {
		super();
		this.status = status;
		this.count = count;
	}
	public PowerStrip() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
