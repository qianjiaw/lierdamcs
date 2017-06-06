package com.lierda.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
		Double zero=(double) 0;Double one=(double) 0;Double two=(double) 0;Double three=(double) 0;Double four=(double) 0;Double five=(double) 0;Double six=(double) 0;Double seven=(double) 0;Double eight=(double) 0;Double nine=(double) 0;Double ten=(double) 0;Double oo=(double) 0;
		Double ot=(double) 0;Double oth=(double) 0;Double of=(double) 0;Double ofi=(double) 0;Double os=(double) 0;Double ose=(double) 0;Double oe=(double) 0;Double on=(double) 0;Double tz=(double) 0;Double to=(double) 0;Double tt=(double) 0;Double tth=(double) 0;
		while(it.hasNext()){
			Map.Entry<String, Object> entry=it.next();
			Double[] value=(Double[]) entry.getValue();
			zero=zero+value[0];one=one+value[1];two=two+value[2];three=three+value[3];four=four+value[4];five=five+value[5];six=six+value[6];seven=seven+value[7];eight=eight+value[8];nine=nine+value[9];ten=ten+value[10];oo=oo+value[11];
			ot=ot+value[12];oth=oth+value[13];of=of+value[14];ofi=ofi+value[15];os=os+value[16];ose=ose+value[17];oe=oe+value[18];on=on+value[19];tz=tz+value[20];to=to+value[21];tt=tt+value[22];tth=tth+value[23];
		}
		currentPowerTotal.put("0", zero);currentPowerTotal.put("1", one);currentPowerTotal.put("2", two);currentPowerTotal.put("3", three);currentPowerTotal.put("4", four);currentPowerTotal.put("5", five);currentPowerTotal.put("6", six);
		currentPowerTotal.put("7", seven);currentPowerTotal.put("8", eight);currentPowerTotal.put("9", nine);currentPowerTotal.put("10", ten);currentPowerTotal.put("11", oo);currentPowerTotal.put("12", ot);currentPowerTotal.put("13", oth);
		currentPowerTotal.put("14", of);currentPowerTotal.put("15", ofi);currentPowerTotal.put("16", os);currentPowerTotal.put("17", ose);currentPowerTotal.put("18", oe);currentPowerTotal.put("19", on);currentPowerTotal.put("20", tz);currentPowerTotal.put("21", to);
		currentPowerTotal.put("22", tt);currentPowerTotal.put("23", tth);
		return currentPowerTotal;
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
		List<Double> powerArray10=new ArrayList<Double>();
		List<Double> powerArray11=new ArrayList<Double>();
		List<Double> powerArray12=new ArrayList<Double>();
		List<Double> powerArray13=new ArrayList<Double>();
		List<Double> powerArray14=new ArrayList<Double>();
		List<Double> powerArray15=new ArrayList<Double>();
		List<Double> powerArray16=new ArrayList<Double>();
		List<Double> powerArray17=new ArrayList<Double>();
		List<Double> powerArray18=new ArrayList<Double>();
		List<Double> powerArray19=new ArrayList<Double>();
		List<Double> powerArray20=new ArrayList<Double>();
		List<Double> powerArray21=new ArrayList<Double>();
		List<Double> powerArray22=new ArrayList<Double>();
		List<Double> powerArray23=new ArrayList<Double>();
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
			}else if (date<timeStart+3600*11) {
				powerArray10.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*12) {
				powerArray11.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*13) {
				powerArray12.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*14) {
				powerArray13.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*15) {
				powerArray14.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*16) {
				powerArray15.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*17) {
				powerArray16.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*18) {
				powerArray17.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*19) {
				powerArray18.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*20) {
				powerArray19.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*21) {
				powerArray20.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*22) {
				powerArray21.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*23) {
				powerArray22.add(powerRecordingEntity.getRealtimepower());
			}else if (date<timeStart+3600*24) {
				powerArray23.add(powerRecordingEntity.getRealtimepower());
			}
		}
		Double[] avgPower={AvgPower(powerArray0),AvgPower(powerArray1),AvgPower(powerArray2),AvgPower(powerArray3),AvgPower(powerArray4),AvgPower(powerArray5),AvgPower(powerArray6),AvgPower(powerArray7),AvgPower(powerArray8),AvgPower(powerArray9),AvgPower(powerArray10),AvgPower(powerArray11),AvgPower(powerArray12),AvgPower(powerArray13),AvgPower(powerArray14),AvgPower(powerArray15),AvgPower(powerArray16),AvgPower(powerArray17),AvgPower(powerArray18),AvgPower(powerArray19),AvgPower(powerArray20),AvgPower(powerArray21),AvgPower(powerArray22),AvgPower(powerArray23)};
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
					String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(powerRecordingEntity.getSavingtime());
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
		List<String> type1=new ArrayList<String>();
		List<String> type15=new ArrayList<String>();
		List<String> type26=new ArrayList<String>();
		Map<String, double[]> powerMap=new HashMap<String, double[]>();
		double[] total1=new double[24];
		double[] total15=new double[24];
		double[] total26=new double[24];
		
		Double count1T0=(double) 0;Double count1T1=(double) 0;Double count1T2=(double) 0;Double count1T3=(double) 0;Double count1T4=(double) 0;Double count1T5=(double) 0;Double count1T6=(double) 0;Double count1T7=(double) 0;Double count1T8=(double) 0;Double count1T9=(double) 0;Double count1T10=(double) 0;
		Double count1T11=(double) 0;Double count1T12=(double) 0;Double count1T13=(double) 0;Double count1T14=(double) 0;Double count1T15=(double) 0;Double count1T16=(double) 0;Double count1T17=(double) 0;Double count1T18=(double) 0;Double count1T19=(double) 0;Double count1T20=(double) 0;
		Double count1T21=(double) 0;Double count1T22=(double) 0;Double count1T23=(double) 0;
		
		Double count15T0=(double) 0;Double count15T1=(double) 0;Double count15T2=(double) 0;Double count15T3=(double) 0;Double count15T4=(double) 0;Double count15T5=(double) 0;Double count15T6=(double) 0;Double count15T7=(double) 0;Double count15T8=(double) 0;Double count15T9=(double) 0;Double count15T10=(double) 0;
		Double count15T11=(double) 0;Double count15T12=(double) 0;Double count15T13=(double) 0;Double count15T14=(double) 0;Double count15T15=(double) 0;Double count15T16=(double) 0;Double count15T17=(double) 0;Double count15T18=(double) 0;Double count15T19=(double) 0;Double count15T20=(double) 0;
		Double count15T21=(double) 0;Double count15T22=(double) 0;Double count15T23=(double) 0;
	
		Double count26T0=(double) 0;Double count26T1=(double) 0;Double count26T2=(double) 0;Double count26T3=(double) 0;Double count26T4=(double) 0;Double count26T5=(double) 0;Double count26T6=(double) 0;Double count26T7=(double) 0;Double count26T8=(double) 0;Double count26T9=(double) 0;Double count26T10=(double) 0;
		Double count26T11=(double) 0;Double count26T12=(double) 0;Double count26T13=(double) 0;Double count26T14=(double) 0;Double count26T15=(double) 0;Double count26T16=(double) 0;Double count26T17=(double) 0;Double count26T18=(double) 0;Double count26T19=(double) 0;Double count26T20=(double) 0;
		Double count26T21=(double) 0;Double count26T22=(double) 0;Double count26T23=(double) 0;
		
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
				System.out.println(avgPower.length+"==========");
//				count1T0=count1T0+avgPower[0];count1T1=count1T1+avgPower[1];count1T2=count1T2+avgPower[2];count1T3=count1T3+avgPower[3];count1T4=count1T4+avgPower[4];count1T5=count1T5+avgPower[5];count1T6=count1T6+avgPower[6];count1T7=count1T7+avgPower[7];count1T8=count1T8+avgPower[8];count1T9=count1T9+avgPower[9];
//				count1T10=count1T10+avgPower[10];count1T11=count1T11+avgPower[11];count1T12=count1T12+avgPower[12];count1T13=count1T13+avgPower[13];count1T14=count1T14+avgPower[14];count1T15=count1T15+avgPower[15];count1T16=count1T16+avgPower[16];count1T17=count1T17+avgPower[17];count1T18=count1T18+avgPower[18];count1T19=count1T19+avgPower[19];
//				count1T20=count1T20+avgPower[20];count1T21=count1T21+avgPower[21];count1T22=count1T22+avgPower[22];count1T23=count1T23+avgPower[23];
				total1[0]=total1[0]+avgPower[0];total1[1]=total1[1]+avgPower[1];total1[2]=total1[2]+avgPower[2];total1[3]=total1[3]+avgPower[3];total1[4]=total1[4]+avgPower[4];total1[5]=total1[5]+avgPower[5];total1[6]=total1[6]+avgPower[6];total1[7]=total1[7]+avgPower[7];total1[8]=total1[8]+avgPower[8];total1[9]=total1[9]+avgPower[9];total1[10]=total1[10]+avgPower[10];
				total1[11]=total1[11]+avgPower[11];total1[12]=total1[12]+avgPower[12];total1[13]=total1[13]+avgPower[13];total1[14]=total1[14]+avgPower[14];total1[15]=total1[15]+avgPower[15];total1[16]=total1[16]+avgPower[16];total1[17]=total1[17]+avgPower[17];total1[18]=total1[18]+avgPower[18];total1[19]=total1[19]+avgPower[19];total1[20]=total1[20]+avgPower[20];total1[21]=total1[21]+avgPower[21];
				total1[22]=total1[22]+avgPower[22];total1[23]=total1[23]+avgPower[23];
			}
		}
		if(type15.size()!=0){
			for (String string : type15) {
				Double[] avgPower=(Double[]) currentPower.get(string);
				total15[0]=total15[0]+avgPower[0];total15[1]=total15[1]+avgPower[1];total15[2]=total15[2]+avgPower[2];total15[3]=total15[3]+avgPower[3];total15[4]=total15[4]+avgPower[4];total15[5]=total15[5]+avgPower[5];total15[6]=total15[6]+avgPower[6];total15[7]=total15[7]+avgPower[7];total15[8]=total15[8]+avgPower[8];total15[9]=total15[9]+avgPower[9];total15[10]=total15[10]+avgPower[10];
				total15[11]=total15[11]+avgPower[11];total15[12]=total15[12]+avgPower[12];total15[13]=total15[13]+avgPower[13];total15[14]=total15[14]+avgPower[14];total15[15]=total15[15]+avgPower[15];total15[16]=total15[16]+avgPower[16];total15[17]=total15[17]+avgPower[17];total15[18]=total15[18]+avgPower[18];total15[19]=total15[19]+avgPower[19];total15[20]=total15[20]+avgPower[20];total15[21]=total15[21]+avgPower[21];
				total15[22]=total15[22]+avgPower[22];total15[23]=total15[23]+avgPower[23];
			}
		}
		if(type26.size()!=0){
			for (String string : type26) {
				Double[] avgPower=(Double[]) currentPower.get(string);
				total26[0]=total26[0]+avgPower[0];total26[1]=total26[1]+avgPower[1];total26[2]=total26[2]+avgPower[2];total26[3]=total26[3]+avgPower[3];total26[4]=total26[4]+avgPower[4];total26[5]=total26[5]+avgPower[5];total26[6]=total26[6]+avgPower[6];total26[7]=total26[7]+avgPower[7];total26[8]=total26[8]+avgPower[8];total26[9]=total26[9]+avgPower[9];total26[10]=total26[10]+avgPower[10];
				total26[11]=total26[11]+avgPower[11];total26[12]=total26[12]+avgPower[12];total26[13]=total26[13]+avgPower[13];total26[14]=total26[14]+avgPower[14];total26[15]=total26[15]+avgPower[15];total26[16]=total26[16]+avgPower[16];total26[17]=total26[17]+avgPower[17];total26[18]=total26[18]+avgPower[18];total26[19]=total26[19]+avgPower[19];total26[20]=total26[20]+avgPower[20];total26[21]=total26[21]+avgPower[21];
				total26[22]=total26[22]+avgPower[22];total26[23]=total26[23]+avgPower[23];
			}
		}
		
		powerMap.put("1", total1);
		powerMap.put("15", total15);
		powerMap.put("26", total26);
		
		return powerMap;
	}
}