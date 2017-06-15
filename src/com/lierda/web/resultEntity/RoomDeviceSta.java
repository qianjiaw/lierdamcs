package com.lierda.web.resultEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author qianjiawei
 *
 */
public class RoomDeviceSta {
	/**ddcmac*/
	private String ddcmac;
	/**设备id*/
	private String deviceId;
	/**设备状态*/
	private String attributes;
	/**是否可调光*/
	boolean canAdjust;
	/**设备类型*/
	private String type;
	/**设备详细信息*/
	String deviceInfo;
	
	public String getDdcmac() {
		return ddcmac;
	}
	public void setDdcmac(String ddcmac) {
		this.ddcmac = ddcmac;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public boolean isCanAdjust() {
		return canAdjust;
	}
	public void setCanAdjust(boolean canAdjust) {
		this.canAdjust = canAdjust;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	
}
