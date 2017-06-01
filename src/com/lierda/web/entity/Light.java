package com.lierda.web.entity;
/**
 * 照明
 * @author  qianjiawei
 *
 */
public class Light {
	/**照明状态*/
	private String status="OFF";
	/**开着的灯数量*/
	private int count;
	public Light(String status, int count) {
		super();
		this.status = status;
		this.count = count;
	}
	public Light() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	
	
}
