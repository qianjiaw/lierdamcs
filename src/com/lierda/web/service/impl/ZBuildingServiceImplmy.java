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

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lierda.web.entity.PowerRecordingEntity;
import com.lierda.web.entity.ZFloorEntity;
import com.lierda.web.service.ZBuildingServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("zBuildingServiceMy")
@Transactional
public class ZBuildingServiceImplmy extends CommonServiceImpl implements ZBuildingServiceI{
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
			double[] value=(double[]) entry.getValue();
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
//	public void getPower(List<PowerRecordingEntity> entities,long timeStart){
//		for (PowerRecordingEntity powerRecordingEntity : entities) {
//			Double allPower=powerRecordingEntity.getAllpower();
//			long time=powerRecordingEntity.getSavingtime().getTime();
//			Math.abs((time-timeStart));
//			double min=3600*1000;
//			if(min>Math.abs((time-timeStart))){
//				min=Math.abs((time-timeStart));
//			}
//		}
//	}
//	public Double[] getPower(List<PowerRecordingEntity> entities,long timeStart){
//		Double[] avgPower=new Double[24];
//		Map<String, List<Double>> hourPower=new HashMap<String, List<Double>>();
//		String hour="";
//		for (int i = 0; i <24; i++) {
//			List<Double> powerArray=new ArrayList<Double>();
//			hour=i+"";
//			hourPower.put(hour, powerArray);
//		}
//		
//		timeStart=timeStart*1000;
//		for (PowerRecordingEntity powerRecordingEntity : entities) {
//			long date=powerRecordingEntity.getSavingtime().getTime();
//			for (int i = 0; i < 24; i++) {
//				if(date<timeStart+3600*(i+1)*1000){
//					hourPower.get(i+"").add(powerRecordingEntity.getAllpower());
//					if(powerRecordingEntity.getMacid().equals("00124B000A993D58")){
//						System.out.println(powerRecordingEntity.getRealtimepower()+"power");
//						System.out.println(i);
//					}
//					break;
//				}
//			}
//		}
//		
//		for (int i = 0; i < 24; i++) {
//			avgPower[i]=AvgPower(hourPower.get(i+""));
//		}
//		return avgPower;
//	}
	
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
					value.append("{\"time\":\""+time+"\",\"power\":\""+powerRecordingEntity.getAllpower()+"\"};");
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
				double[] allPower=(double[]) currentPower.get(string);
				for(int i=0;i<24;i++){
					total1[i]=total1[i]+allPower[i];
				}
			}
		}
		
		if(type15.size()!=0){
			for (String string : type15) {
				double[] allPower=(double[]) currentPower.get(string);
				for(int i=0;i<24;i++){
					total15[i]=total15[i]+allPower[i];
				}
			}
		}
		
		if(type26.size()!=0){
			for (String string : type26) {
				double[] allPower=(double[]) currentPower.get(string);
				for(int i=0;i<24;i++){
					total26[i]=total26[i]+allPower[i];
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
				entity.setAllpower(Double.valueOf((String) object
						.get("power")));
				entities.add(entity);
				
			}

//			Double[] avgPower = getPower(entities, timeStart);
//			currentPower.put(entry.getKey(), avgPower);
			currentPower.put(entry.getKey(), calculatePower(entities, timeStart));
		}

		return currentPower;
	}
	
	
	
	//计算功率
	
	public  double[] calculatePower(List<PowerRecordingEntity> entities,long timeStart){
			ArrayList<String> notWork=new ArrayList<String>();
			long hour=0;
			long before=0;//时间戳与前一个整点的绝对值
			long after=0;//时间戳与后一个整点的绝对值
			long[] min=new long[25];//距离整点最近的时间戳，最后一个比较的是当天的24点与下一天的1点的多余数据
			double[] powerHour=new double[24];//各个整点间的功耗
			double[] currentPower=new double[25];//计量在当前时刻的总功耗
			for (int i = 0; i < min.length; i++) {
				min[i]=3600*1000;
			}
			for (PowerRecordingEntity powerRecordingEntity : entities) {
					long time=powerRecordingEntity.getSavingtime().getTime();
					for(int i=0;i<24;i++){
						hour=timeStart*1000+3600*1000*(i+1);
						if(time<hour){
							before=Math.abs(time-hour+3600*1000);
							after=Math.abs(time-hour);
							if(min[i]>before){
								min[i]=before;
								currentPower[i]=powerRecordingEntity.getAllpower();
							}
							if(min[i+1]>after){
								min[i+1]=after;
								currentPower[i+1]=powerRecordingEntity.getAllpower();
							}
							break;
						}
						notWork.add(i+"");
					}
			}
			
			for (int i = 0; i < currentPower.length-1; i++) {
				powerHour[i]=(currentPower[i+1]-currentPower[i]<0?0:currentPower[i+1]-currentPower[i]);
			}
		return powerHour;
	}

	public Map<String, Object> getDeviceCount(List<ZFloorEntity> floors) {
		// TODO Auto-generated method stub
		return null;
	}

	public Double[] getPower(List<PowerRecordingEntity> entities, long timeStart) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}