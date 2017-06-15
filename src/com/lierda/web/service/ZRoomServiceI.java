package com.lierda.web.service;

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;

import com.lierda.web.resultEntity.RoomDeviceSta;

public interface ZRoomServiceI extends CommonService{

	public List<RoomDeviceSta> getDeviceDetail(String roomid,String type);
}
