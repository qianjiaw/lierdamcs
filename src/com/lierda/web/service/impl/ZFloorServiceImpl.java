package com.lierda.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lierda.web.entity.AirConditioner;
import com.lierda.web.entity.Bgm;
import com.lierda.web.entity.Blind;
import com.lierda.web.entity.FloorHeating;
import com.lierda.web.entity.Light;
import com.lierda.web.entity.Lock;
import com.lierda.web.entity.PowerStrip;
import com.lierda.web.entity.SenseHuman;
import com.lierda.web.entity.ZRoomEntity;
import com.lierda.web.resultEntity.DeviceStatus;
import com.lierda.web.resultEntity.JsonResult;
import com.lierda.web.service.ZFloorServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.demo.service.test.JeecgMinidaoServiceI;

@Service("zFloorService")
@Transactional
public class ZFloorServiceImpl extends CommonServiceImpl implements ZFloorServiceI {
	@Autowired
	private JeecgMinidaoServiceI jeecgMinidaoService;
	/**
	 * 获取指定楼层所有房间的各个设备状态
	 * @return
	 */
	public Map<String, Object> getDeviceStatus(String floorid) {
		Map<String, Object> map=new HashMap<String, Object>();
		String roomid = "";
		List<ZRoomEntity> rooms = jeecgMinidaoService
				.selectRoomByFloor(floorid);//所有房间id，名称
		
		for (ZRoomEntity zRoomEntity : rooms) {
			roomid = zRoomEntity.getId();
			DeviceStatus deviceStatus = new DeviceStatus();
			//获取当前房间各设备状态
			
			// 人感
			List<String> senseAttr = findListbySql("select device.attributes as attributes from z_room r join z_ddc_rfbp rfbp on r.id=rfbp.roomid join z_ddc ddc on ddc.ddcmac=rfbp.ddcmac join z_device device on device.ddcId=ddc.id join z_devicetype devicetype on devicetype.id=device.type where r.id='"+ roomid + "' and (device.type='21' or device.type='20')");
			// 门磁
			List<String> lockAttr = findListbySql("select device.attributes as attributes from z_room r join z_ddc_rfbp rfbp on r.id=rfbp.roomid join z_ddc ddc on ddc.ddcmac=rfbp.ddcmac join z_device device on device.ddcId=ddc.id join z_devicetype devicetype on devicetype.id=device.type where r.id='"+ roomid + "' and (device.type='10' or device.type='18')");
			// 照明
			List<String> lightAttr = findListbySql("select device.attributes as attributes from z_room r join z_ddc_rfbp rfbp on r.id=rfbp.roomid join z_ddc ddc on ddc.ddcmac=rfbp.ddcmac join z_device device on device.ddcId=ddc.id join z_devicetype devicetype on devicetype.id=device.type where r.id='"+ roomid + "' and device.type='1'");
			// 窗帘
			List<String> blindAttr = findListbySql("select device.attributes as attributes from z_room r join z_ddc_rfbp rfbp on r.id=rfbp.roomid join z_ddc ddc on ddc.ddcmac=rfbp.ddcmac join z_device device on device.ddcId=ddc.id join z_devicetype devicetype on devicetype.id=device.type where r.id='"+ roomid + "' and (device.type='5' or device.type='17')");
			// 插座
			List<String> stripAttr = findListbySql("select device.attributes as attributes from z_room r join z_ddc_rfbp rfbp on r.id=rfbp.roomid join z_ddc ddc on ddc.ddcmac=rfbp.ddcmac join z_device device on device.ddcId=ddc.id join z_devicetype devicetype on devicetype.id=device.type where r.id='"+ roomid + "' and device.type='26'");
			//地暖
			List<String> heatingAttr=findListbySql("select device.attributes as attributes from z_room r join z_ddc_rfbp rfbp on r.id=rfbp.roomid join z_ddc ddc on ddc.ddcmac=rfbp.ddcmac join z_device device on device.ddcId=ddc.id join z_devicetype devicetype on devicetype.id=device.type where r.id='"+ roomid + "' and device.type='13'");
			//音乐
			List<String> bgmAttr=findListbySql("select device.attributes as attributes from z_room r join z_ddc_rfbp rfbp on r.id=rfbp.roomid join z_ddc ddc on ddc.ddcmac=rfbp.ddcmac join z_device device on device.ddcId=ddc.id join z_devicetype devicetype on devicetype.id=device.type where r.id='"+ roomid + "' and device.type='27'");
			//空调
			List<String> conditionerAttr=findListbySql("select device.attributes as attributes from z_room r join z_ddc_rfbp rfbp on r.id=rfbp.roomid join z_ddc ddc on ddc.ddcmac=rfbp.ddcmac join z_device device on device.ddcId=ddc.id join z_devicetype devicetype on devicetype.id=device.type where r.id='"+ roomid + "' and device.type='15'");
			
//			// 人感
//			List<String> senseAttr = findHql("select device.attributes as attributes from ZRoomEntity r join ZDdcRfbpEntity rfbp on r.id=rfbp.roomid join DdcEntity ddc on ddc.ddcmac=rfbp.ddcmac join DeviceEntity device on device.ddcId=ddc.id join ZDeviceTypeEntity devicetype on devicetype.id=device.type where r.id=? and (device.type='21' or device.type='20')",roomid);
//			// 门磁
//			List<String> lockAttr = findHql("select device.attributes as attributes from ZRoomEntity r join ZDdcRfbpEntity rfbp on r.id=rfbp.roomid join DdcEntity ddc on ddc.ddcmac=rfbp.ddcmac join DeviceEntity device on device.ddcId=ddc.id join ZDeviceTypeEntity devicetype on devicetype.id=device.type where r.id=? and (device.type='10' or device.type='18')",roomid);
//			// 照明
//			List<String> lightAttr = findHql("select device.attributes as attributes from ZRoomEntity r join ZDdcRfbpEntity rfbp on r.id=rfbp.roomid join DdcEntity ddc on ddc.ddcmac=rfbp.ddcmac join DeviceEntity device on device.ddcId=ddc.id join ZDeviceTypeEntity devicetype on devicetype.id=device.type where r.id=? and device.type='1'",roomid);
//			// 窗帘
//			List<String> blindAttr = findHql("select device.attributes as attributes from ZRoomEntity r join ZDdcRfbpEntity rfbp on r.id=rfbp.roomid join DdcEntity ddc on ddc.ddcmac=rfbp.ddcmac join DeviceEntity device on device.ddcId=ddc.id join ZDeviceTypeEntity devicetype on devicetype.id=device.type where r.id=? and (device.type='5' or device.type='17')",roomid);
//			// 插座
//			List<String> stripAttr = findHql("select device.attributes as attributes from ZRoomEntity r join ZDdcRfbpEntity rfbp on r.id=rfbp.roomid join DdcEntity ddc on ddc.ddcmac=rfbp.ddcmac join DeviceEntity device on device.ddcId=ddc.id join ZDeviceTypeEntity devicetype on devicetype.id=device.type where r.id=? and device.type='26'",roomid);
//			//地暖
//			List<String> heatingAttr=findHql("select device.attributes as attributes from ZRoomEntity r join ZDdcRfbpEntity rfbp on r.id=rfbp.roomid join DdcEntity ddc on ddc.ddcmac=rfbp.ddcmac join DeviceEntity device on device.ddcId=ddc.id join ZDeviceTypeEntity devicetype on devicetype.id=device.type where r.id=? and device.type='13'",roomid);
//			//音乐
//			List<String> bgmAttr=findHql("select device.attributes as attributes from ZRoomEntity r join ZDdcRfbpEntity rfbp on r.id=rfbp.roomid join DdcEntity ddc on ddc.ddcmac=rfbp.ddcmac join DeviceEntity device on device.ddcId=ddc.id join ZDeviceTypeEntity devicetype on devicetype.id=device.type where r.id=? and device.type='27'",roomid);
//			//空调
//			List<String> conditionerAttr=findHql("select device.attributes as attributes from ZRoomEntity r join ZDdcRfbpEntity rfbp on r.id=rfbp.roomid join DdcEntity ddc on ddc.ddcmac=rfbp.ddcmac join DeviceEntity device on device.ddcId=ddc.id join ZDeviceTypeEntity devicetype on devicetype.id=device.type where r.id=? and device.type='15'",roomid);
			//根据上面获取的数据组装当前房间各设备对象
			
			// 人感
			SenseHuman senseHuman=new SenseHuman();
			if (senseAttr.size()!=0) {
				for (String attribute : senseAttr) {
					JSONObject jsonObject = JSON.parseObject(attribute);
					String status = (String) jsonObject.get("PIR");
					if(status==null){
						senseHuman.setStatus("none");
					}
					if (status!=null&&status.equals("YES")) {
						senseHuman.setStatus("ON");
					}
				}
			} else {
				senseHuman.setStatus("none");
			}
			deviceStatus.setSenseHuman(senseHuman);
			
			// 门磁
			Lock lock = new Lock();
			if (lockAttr.size()!=0) {
				int lockCount = 0;
				for (String string : lockAttr) {
					JSONObject jsonObject = JSON.parseObject(string);
					String status = (String) jsonObject.get("DOR");
					if(status==null){
						lock.setStatus("none");
					}
					if (status!=null&&status.equals("OPEN")) {
						lockCount++;
						lock.setStatus("ON");
					}
				}
				lock.setCount(lockCount);
			} else {
				lock.setStatus("none");
			}
			deviceStatus.setLock(lock);

			// 照明
			Light light = new Light();
			if (lightAttr.size()!=0) {
				int lightCount = 0;
				for (String string : lightAttr) {
					JSONObject jsonObject = JSON.parseObject(string);
					String status = (String) jsonObject.get("SWI");
					if(status==null){
						light.setStatus("none");
					}
					if (status!=null&&status.equals("ON")) {
						lightCount++;
						light.setStatus("ON");
					}
				}
				light.setCount(lightCount);
			} else {
				light.setStatus("none");
			}
			deviceStatus.setLight(light);
			
			// 窗帘
			Blind blind = new Blind();
			if (blindAttr.size()!=0) {
				for (String string : blindAttr) {
					JSONObject jsonObject = JSON.parseObject(string);
					String status = (String) jsonObject.get("WIN");
					if(status==null){
						blind.setStatus("none");
					}
					if (status!=null&&status.equals("OPEN")) {
						blind.setStatus("ON");
						String action = status;
						if (action.equals("CLOSE")) {
							action = "关";
						} else if (action.equals("STOP")) {
							action = "停";
						} else if (action.equals("OPEN")) {
							action = "开";
						}
						blind.setAction(action);
					}
				}
			} else {
				blind.setStatus("none");
			}
			deviceStatus.setBlind(blind);
			
			// 插座
			PowerStrip powerStrip=new PowerStrip();
			if (stripAttr.size()!=0) {
				int stripCount = 0;
				for (String string : stripAttr) {
					JSONObject jsonObject = JSON.parseObject(string);
					String status = (String) jsonObject.get("SWI");
					if(status==null){
						powerStrip.setStatus("none");
					}
					if (status!=null&&status.equals("ON")) {
						stripCount++;
						powerStrip.setStatus("ON");
					}
				}
				powerStrip.setCount(stripCount);
			} else {
				powerStrip.setStatus("none");
			}
			deviceStatus.setPowerStrip(powerStrip);
			
			//地暖
			FloorHeating floorHeating=new FloorHeating();
			if (heatingAttr.size()!=0) {
				int heatingCount = 0;
				for (String string : heatingAttr) {
					JSONObject jsonObject = JSON.parseObject(string);
					String status = (String) jsonObject.get("SWI");
					if(status==null){
						floorHeating.setStatus("none");
					}
					if (status!=null&&status.equals("ON")) {
						heatingCount++;
						floorHeating.setStatus("ON");
					}
				}
				floorHeating.setCount(heatingCount);
			} else {
				floorHeating.setStatus("none");
			}
			deviceStatus.setFloorHeating(floorHeating);
			
			//音乐
			Bgm bgm=new Bgm();
			if (bgmAttr.size()!=0) {
				int bgmCount = 0;
				for (String string : bgmAttr) {
					JSONObject jsonObject = JSON.parseObject(string);
					String status = (String) jsonObject.get("SWI");
					if(status==null){
						bgm.setStatus("none");
					}
					if (status!=null&&status.equals("ON")) {
						bgmCount++;
						bgm.setStatus("ON");
					}
				}
				bgm.setCount(bgmCount);
			} else {
				bgm.setStatus("none");
			}
			deviceStatus.setBgm(bgm);
			
			//空调
			AirConditioner airConditioner=new AirConditioner();
			if (conditionerAttr.size()!=0) {
				for (String string : conditionerAttr) {
					JSONObject jsonObject = JSON.parseObject(string);
					String status = (String) jsonObject.get("TUN");
					if(status==null){
						airConditioner.setStatus("none");
					}
					if (status!=null&&status.equals("OPEN")) {
						airConditioner.setStatus("ON");
						String mode = (String) jsonObject.get("SMD");// 模式
						String temperature = (String) jsonObject.get("STP");// 温度
						String windSpeed = (String) jsonObject.get("SSP");// 风速
					
						if(mode.equals("COOL")){
							mode="制冷";
						}else if (mode.equals("HOT")) {
							mode="制热";
						}else if (mode.equals("AIR")){
							mode="送风";
						}
						
						if(windSpeed.equals("LOW")){
							windSpeed="低风";
						}else if (windSpeed.equals("MID")) {
							windSpeed="中风";
						}else if (windSpeed.equals("HIGH")) {
							windSpeed="大风";
						}else if (windSpeed.equals("AUTO")) {
							windSpeed="自动";
						}
						
						airConditioner.setMode(mode);
						airConditioner.setTemperature(temperature);
						airConditioner.setWindSpeed(windSpeed);
					}
				}
			} else {
				airConditioner.setStatus("none");
			}
			deviceStatus.setAirConditioner(airConditioner);

			map.put(roomid, deviceStatus);
		}
		
		return map;
	}
	
	/**
	 * 根据传入参数拼接sql语句
	 * @param roomid
	 * @param deviceType
	 * @return
	 */
	public String addSql(String roomid,String deviceType){
		String sql="device.macid='0000000000000000' and r.id='"+roomid+"' and devicetype.typ='"+deviceType+"'";
		return sql;
	}
	
	/**
	 * 根据传入参数获取sql语句
	 * @param list
	 * @param sql
	 * @return
	 */
	public String getSql(List<JsonResult> list,String sql){
		StringBuffer sqlpart=new StringBuffer();
		List<String> types=null;
		JsonResult result=null;
		for (int i=0;i<list.size();i++) {
			if(!(i<1)){
				sqlpart=sqlpart.append(" or ");
			}
			result=list.get(i);
			String roomid=result.getRoomid();
			types=result.getType();
			String deviceType="";
			for (int j = 0; j < types.size(); j++) {
				deviceType=types.get(j);
				sqlpart=sqlpart.append(addSql(roomid,deviceType));
				if(!(j==types.size()-1)){
					sqlpart=sqlpart.append(" or ");
				}
			}
		}
		sql=sql+sqlpart.toString();
		return sql;
	}
	
}