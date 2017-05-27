package com.lierda.web.resultEntity;

public class SqlResult {
	/**macid*/
	private java.lang.String macid;
	/**ddcmac*/
	private java.lang.String ddcmac;
	/**serverip*/
	private java.lang.String serverip;
	/**ddcid*/
	private java.lang.String ddcid;
	/**deviceid*/
	private java.lang.String deviceid  ;
	public java.lang.String getMacid() {
		return macid;
	}
	public void setMacid(java.lang.String macid) {
		this.macid = macid;
	}
	public java.lang.String getDdcmac() {
		return ddcmac;
	}
	public void setDdcmac(java.lang.String ddcmac) {
		this.ddcmac = ddcmac;
	}
	public java.lang.String getServerip() {
		return serverip;
	}
	public void setServerip(java.lang.String serverip) {
		this.serverip = serverip;
	}
	public java.lang.String getDdcid() {
		return ddcid;
	}
	public void setDdcid(java.lang.String ddcid) {
		this.ddcid = ddcid;
	}
	public java.lang.String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(java.lang.String deviceid) {
		this.deviceid = deviceid;
	}
	public SqlResult(String macid, String ddcmac, String serverip,
			String ddcid, String deviceid) {
		super();
		this.macid = macid;
		this.ddcmac = ddcmac;
		this.serverip = serverip;
		this.ddcid = ddcid;
		this.deviceid = deviceid;
	}
	
	
}
