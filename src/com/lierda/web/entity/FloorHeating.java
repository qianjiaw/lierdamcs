package com.lierda.web.entity;
/**
 * 地暖
 * @author qianjiawei
 *
 */
public class FloorHeating {
	/**地暖状态*/
	private String status="OFF";
	/**开着的地暖数量*/
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
	public FloorHeating(String status, int count) {
		super();
		this.status = status;
		this.count = count;
	}
	public FloorHeating() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
