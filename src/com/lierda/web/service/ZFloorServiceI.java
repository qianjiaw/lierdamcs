package com.lierda.web.service;

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;

import com.lierda.web.resultEntity.JsonResult;

public interface ZFloorServiceI extends CommonService{
	
	public Map<String, Object> getDeviceStatus(String floorid,String flag);
	
	public String getSql(List<JsonResult> list,String sql);
	
	public String addSql(String roomid,String deviceType);
	
	
}
