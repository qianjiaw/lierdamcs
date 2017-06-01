package com.lierda.web.entity;
/**
 * 音乐
 * @author qianjiawei
 *
 */
public class Bgm {
	/**音乐状态*/
	private String status="OFF";
	/**音乐开启数量*/
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
	public Bgm(String status, int count) {
		super();
		this.status = status;
		this.count = count;
	}
	public Bgm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
