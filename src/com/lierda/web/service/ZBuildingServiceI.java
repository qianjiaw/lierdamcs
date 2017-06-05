package com.lierda.web.service;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import com.lierda.web.entity.PowerRecordingEntity;

public interface ZBuildingServiceI extends CommonService{

	public Double AvgPower(List<Double> powerArray);
	
	public Double[] getPower(List<PowerRecordingEntity> entities,long timeStart);
}
