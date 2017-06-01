package com.lierda.web.entity;

import javax.persistence.Entity;


/**
 * 门锁
 * @author  qianjiawei
 *
 */
public class Lock {
	/**门锁状态*/
	private String status="OFF";
	/**开启门锁数量*/
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
	
	public Lock(String status, int count) {
		super();
		this.status = status;
		this.count = count;
	}
	
	public Lock() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
