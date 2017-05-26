package com.lierda.web.resultEntity;

public class ZFloorResult {
	/**id*/
	private java.lang.String id;
	/**楼层名*/
	private java.lang.String floorname;
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getFloorname() {
		return floorname;
	}
	public void setFloorname(java.lang.String floorname) {
		this.floorname = floorname;
	}
	public ZFloorResult(String id, String floorname) {
		super();
		this.id = id;
		this.floorname = floorname;
	}
	
	

}
