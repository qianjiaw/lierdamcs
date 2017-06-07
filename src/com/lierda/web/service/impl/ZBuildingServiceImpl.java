package com.lierda.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lierda.web.entity.PowerRecordingEntity;
import com.lierda.web.entity.ZFloorEntity;
import com.lierda.web.service.ZBuildingServiceI;
import com.lierda.web.service.ZFloorServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("zBuildingService")
@Transactional
public class ZBuildingServiceImpl extends CommonServiceImpl implements ZBuildingServiceI {
	
	@Autowired
	private ZFloorServiceI zFloorService;
	
	/**
	 * 获取单个时间段内功率
	 * @param powerArray
	 * @return
	 */
	public Double AvgPower(List<Double> powerArray){
		int count=powerArray.size();
		if(count==0){
			return (double)0;
		}
		Double total=(double) 0;
		for (Double power : powerArray) {
			total=power+total;
		}
		return (total/count)/1000;
	}
	
	/**
	 * 各个时间段所有计量总功率
	 * @param currentPower
	 * @return
	 */
	public Map<String, Object> getTotalPower(Map<String, Object> currentPower){
		Map<String, Object> currentPowerTotal=new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it=currentPower.entrySet().iterator();
		double[] hour=new double[24];
		
		while(it.hasNext()){
			Map.Entry<String, Object> entry=it.next();
			Double[] value=(Double[]) entry.getValue();
			for (int i = 0; i < 24; i++) {
				hour[i]=hour[i]+value[i];
			}
		}
		
		for (int i = 0; i < 24; i++) {
			currentPowerTotal.put(i+"", hour[i]);
		}
		
		return currentPowerTotal;
	}
	
	/**
	 * 获取各个时间段内功率
	 * @param entities
	 * @param timeStart
	 * @return
	 */
	public Double[] getPower(List<PowerRecordingEntity> entities,long timeStart){
		Double[] avgPower=new Double[24];
		Map<String, List<Double>> hourPower=new HashMap<String, List<Double>>();
		String hour="";
		for (int i = 0; i <24; i++) {
			List<Double> powerArray=new ArrayList<Double>();
			hour=i+"";
			hourPower.put(hour, powerArray);
		}
		
		timeStart=timeStart*1000;
		for (PowerRecordingEntity powerRecordingEntity : entities) {
			long date=powerRecordingEntity.getSavingtime().getTime();
			for (int i = 0; i < 24; i++) {
				if(date<timeStart+3600*(i+1)*1000){
					hourPower.get(i+"").add(powerRecordingEntity.getRealtimepower());
					if(powerRecordingEntity.getMacid().equals("00124B000A993D58")){
						System.out.println(powerRecordingEntity.getRealtimepower()+"power");
						System.out.println(i);
					}
					break;
				}
			}
		}
		
		for (int i = 0; i < 24; i++) {
			avgPower[i]=AvgPower(hourPower.get(i+""));
		}
		return avgPower;
	}
	
	/**
	 * 根据macid将所有能耗信息分组
	 * 并将macid作为map的key，对应信息作为value存入map中
	 * @param macids
	 * @param recordingEntities
	 */
	public Map<String, String> getPowerMap(Set<String> macids,List<PowerRecordingEntity> recordingEntities){
		Map<String, String> powerMap=new HashMap<String, String>();
		for (String  macid : macids) {
			String key=macid;
			StringBuffer value=new StringBuffer("");
			for (PowerRecordingEntity powerRecordingEntity : recordingEntities) {
				if(powerRecordingEntity.getMacid().equals(macid)){
					String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(powerRecordingEntity.getSavingtime());
					value.append("{\"time\":\""+time+"\",\"power\":\""+powerRecordingEntity.getRealtimepower()+"\"};");
				}
			}
			powerMap.put(key, value.toString());
		}
		return powerMap;
	}
	
	/**
	 * 按照计量类型计算各时段功率
	 * @param recordingEntities
	 * @param currentPower
	 */
	public Map<String, double[]> getPowerByType(List<PowerRecordingEntity> recordingEntities,Map<String, Object> currentPower){
		Set<String> type1=new HashSet<String>();
		Set<String> type15=new HashSet<String>();
		Set<String> type26=new HashSet<String>();
		Map<String, double[]> powerMap=new HashMap<String, double[]>();
		double[] total1=new double[24];
		double[] total15=new double[24];
		double[] total26=new double[24];
		
		for (PowerRecordingEntity powerRecordingEntity : recordingEntities) {
			if(powerRecordingEntity.getType().equals("1")){
				type1.add(powerRecordingEntity.getMacid());
			}else if (powerRecordingEntity.getType().equals("15")) {
				type15.add(powerRecordingEntity.getMacid());
			}else if (powerRecordingEntity.getType().equals("26")) {
				type26.add(powerRecordingEntity.getMacid());
			}
		}
		
		if(type1.size()!=0){
			for (String string : type1) {
				Double[] avgPower=(Double[]) currentPower.get(string);
				for(int i=0;i<24;i++){
					total1[i]=total1[i]+avgPower[i];
				}
			}
		}
		
		if(type15.size()!=0){
			for (String string : type15) {
				Double[] avgPower=(Double[]) currentPower.get(string);
				for(int i=0;i<24;i++){
					total15[i]=total15[i]+avgPower[i];
				}
			}
		}
		
		if(type26.size()!=0){
			for (String string : type26) {
				Double[] avgPower=(Double[]) currentPower.get(string);
				for(int i=0;i<24;i++){
					total26[i]=total26[i]+avgPower[i];
				}
			}
		}
		
		powerMap.put("1", total1);
		powerMap.put("15", total15);
		powerMap.put("26", total26);
		
		return powerMap;
	}
	
	/**
	 * 获取各个计量各时间段功率
	 * @param timeStart
	 * @param powerMap
	 * @return
	 */
	public Map<String, Object> getCurrentPower(long timeStart,
			Map<String, String> powerMap) {
		Map<String, Object> currentPower = new HashMap<String, Object>();
		Set<Entry<String, String>> entrySet = powerMap.entrySet();
		List<PowerRecordingEntity> entities = new ArrayList<PowerRecordingEntity>();
		for (Entry<String, String> entry : entrySet) {
			if (entities.size() != 0) {
				entities.clear();
			}

			String[] values = entry.getValue().split(";");
			for (String string : values) {
				PowerRecordingEntity entity = new PowerRecordingEntity();
				JSONObject object = JSONObject.parseObject(string);
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date = null;
				try {
					date = sdf.parse((String) object.get("time"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				entity.setMacid(entry.getKey());
				entity.setSavingtime(date);
				entity.setRealtimepower(Double.valueOf((String) object
						.get("power")));
				entities.add(entity);
			}

			Double[] avgPower = getPower(entities, timeStart);
			currentPower.put(entry.getKey(), avgPower);

		}

		return currentPower;
	}

	
	/**
	 * 获取指定建筑物内所有设备总数量和正在使用总数量
	 * @param floors
	 * @return
	 */
	public Map<String, Object> getDeviceCount(List<ZFloorEntity> floors) {
		String floorId = "";
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> countMap = new HashMap<String, Object>();
		long[] counts = new long[4];// 各类型设备总数数组
		long[] usingCount = new long[4];// 各类型设备正在使用的总数数组
		for (ZFloorEntity zFloorEntity : floors) {
			floorId = zFloorEntity.getId();
			map = zFloorService.getDeviceStatus(floorId, "building");
			for (int i = 0; i < 4; i++) {
				counts[i] = ((long[]) map.get("counts"))[i] + counts[i];
				usingCount[i] = ((long[]) map.get("usingCount"))[i]
						+ usingCount[i];
			}
		}
		
		countMap.put("counts", counts);
		countMap.put("usingCount", usingCount);

		return countMap;
	}
	
}