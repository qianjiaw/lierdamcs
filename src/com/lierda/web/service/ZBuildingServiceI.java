package com.lierda.web.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jeecgframework.core.common.service.CommonService;

import com.lierda.web.entity.PowerRecordingEntity;
import com.lierda.web.entity.ZFloorEntity;

public interface ZBuildingServiceI extends CommonService{

	public Double AvgPower(List<Double> powerArray);
	
	public Double[] getPower(List<PowerRecordingEntity> entities,long timeStart);
	
	public Map<String, String> getPowerMap(Set<String> macids,List<PowerRecordingEntity> recordingEntities);
	
	public Map<String, Object> getTotalPower(Map<String, Object> currentPower);
	
	public Map<String, double[]> getPowerByType(List<PowerRecordingEntity> recordingEntities,Map<String, Object> currentPower);
	
	public Map<String, Object> getCurrentPower(long timeStart,Map<String, String> powerMap);
	
	public Map<String, Object>  getDeviceCount(List<ZFloorEntity> floors);
}
