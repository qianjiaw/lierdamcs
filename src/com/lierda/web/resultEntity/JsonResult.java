package com.lierda.web.resultEntity;

import java.util.List;

public class JsonResult {
	private String roomid;
	private List<String> type;
	public String getRoomid() {
		return roomid;
	}
	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}
	public List<String> getType() {
		return type;
	}
	public void setType(List<String> type) {
		this.type = type;
	}
	public JsonResult(String roomid, List<String> type) {
		super();
		this.roomid = roomid;
		this.type = type;
	}
	
	public JsonResult() {
		super();
	}
	
	
}
