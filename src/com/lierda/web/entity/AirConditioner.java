package com.lierda.web.entity;

import javax.persistence.Entity;

/**
 * 空调
 * @author qianjiawei
 */
public class AirConditioner {
	/**空调状态*/
	private String status="OFF";
	/**空调模式*/
	private String mode;
	/**空调温度*/
	private String temperature;
	/**空调风速*/
	private String windSpeed;
	public AirConditioner(String status, String mode, String temperature,
			String windSpeed) {
		super();
		this.status = status;
		this.mode = mode;
		this.temperature = temperature;
		this.windSpeed = windSpeed;
	}
	public AirConditioner() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}
	
	
}
