package com.lierda.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lierda.web.entity.PowerRecordingEntity;
import com.lierda.web.service.ZBuildingServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("zBuildingService")
@Transactional
public class ZBuildingServiceImpl extends CommonServiceImpl implements ZBuildingServiceI {
	/**
	 * 获取单个时间段内功率
	 * @param powerArray
	 * @return
	 */
	public Double AvgPower(List<Double> powerArray){
		int count=powerArray.size();
		Double total=(double) 0;
		for (Double power : powerArray) {
			total=power+total;
		}
		return total/count;
	}
	
	/**
	 * 获取各个时间段内功率
	 * @param entities
	 * @param timeStart
	 * @return
	 */
	public Double[] getPower(List<PowerRecordingEntity> entities,long timeStart){
		List<Double> powerArray0=new ArrayList<Double>();
		List<Double> powerArray1=new ArrayList<Double>();
		List<Double> powerArray2=new ArrayList<Double>();
		List<Double> powerArray3=new ArrayList<Double>();
		List<Double> powerArray4=new ArrayList<Double>();
		List<Double> powerArray5=new ArrayList<Double>();
		List<Double> powerArray6=new ArrayList<Double>();
		List<Double> powerArray7=new ArrayList<Double>();
		List<Double> powerArray8=new ArrayList<Double>();
		List<Double> powerArray9=new ArrayList<Double>();
		for (PowerRecordingEntity powerRecordingEntity : entities) {
			long date=powerRecordingEntity.getSavingtime().getTime()/1000;
			if(date<timeStart+3600*1){
				powerArray0.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*2) {
				powerArray1.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*3) {
				powerArray2.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*4) {
				powerArray3.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*5) {
				powerArray4.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*6) {
				powerArray5.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*7) {
				powerArray6.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*8) {
				powerArray7.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*9) {
				powerArray8.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*10) {
				powerArray9.add(powerRecordingEntity.getRealtimepower());
			}
		}
		Double[] avgPower={AvgPower(powerArray0),AvgPower(powerArray1),AvgPower(powerArray2),AvgPower(powerArray3),AvgPower(powerArray4),AvgPower(powerArray5),AvgPower(powerArray6),AvgPower(powerArray7),AvgPower(powerArray8),AvgPower(powerArray9)};
		return avgPower;
	}
}