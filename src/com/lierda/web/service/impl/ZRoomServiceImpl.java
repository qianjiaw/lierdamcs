package com.lierda.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lierda.web.resultEntity.RoomDeviceSta;
import com.lierda.web.service.ZRoomServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.demo.service.test.JeecgMinidaoServiceI;

@Service("zRoomService")
@Transactional
public class ZRoomServiceImpl extends CommonServiceImpl implements ZRoomServiceI {
@Resource
private ZRoomServiceI zRoomService;
@Autowired
private JeecgMinidaoServiceI jeecgMinidaoService;

	/**
	 * 根据房间id以及设备类型获取设备状态详细信息
	 * @param roomid
	 * @param type
	 * @return
	 */
	public List<RoomDeviceSta> getDeviceDetail(String roomid,String type) {
		// TODO Auto-generated method stub
		String suffix="";
		if(type.equals("19")){
			suffix="(device.type='19' or device.type='2' or device.type='3' or device.type='4')";
		}else if(type.equals("7")){
			suffix="(device.type='7' or device.type='8' or device.type='20' or device.type='21')";
		}else{
			suffix="device.type='"+type+"'";
		}
		String sql="select ddc.ddcmac as ddcmac,device.attributes as attributes,device.id as deviceId,device.type as type from z_device device join z_ddc ddc on ddc.id=device.ddcId join z_ddc_rfbp rfbp on rfbp.ddcmac=ddc.ddcmac join z_room r on r.id=rfbp.roomid where r.id='"+roomid+"' and "+suffix;
		System.out.println(sql);
		List<RoomDeviceSta> deviceDetail=jeecgMinidaoService.getRoomDevice(sql);
		deviceDetail=getAttributes(deviceDetail);
//		for (RoomDeviceSta roomDeviceSta : deviceDetail) {
//			JSONObject attributes=JSONObject.parseObject(roomDeviceSta.getAttributes());
//			if(attributes.get("LEV")!=null){
//				roomDeviceSta.setCanAdjust(true);
//			}
//		}
		return deviceDetail;
	}
	
	/**
	 * 获取设备状态详细信息
	 * @param deviceDetail
	 * @return
	 */
	public List<RoomDeviceSta>  getAttributes(List<RoomDeviceSta> deviceDetail){
		String json="";
		String type="";
		for (RoomDeviceSta roomDeviceSta : deviceDetail) {
			type=roomDeviceSta.getType();
			JSONObject attributes=JSONObject.parseObject(roomDeviceSta.getAttributes());
			if(type.equals("1")){//照明
				String swi =(String) attributes.get("SWI");//开关
				String lev = (String) attributes.get("LEV");//调光
				String ctm = (String) attributes.get("CTM");//调色
				json="{\"SWI\":\""+swi+"\",\"LEV\":\""+lev+"\",\"CTM\":\""+ctm+"\"}";
			}else if (type.equals("26")) {//插座
				String swi = (String) attributes.get("SWI");//开关
				json="{\"SWI\":\""+swi+"\"}";
			}else if (type.equals("15")) {//空调
				String tun=(String) attributes.get("TUN");//开关
				String smd = (String) attributes.get("SMD");// 模式
				json="{\"TUN\":\""+tun+"\",\"SMD\":\""+smd+"\"}";
			}else if (type.equals("5")) {//窗帘
				String win=(String) attributes.get("WIN");//开，关，停
				json="{\"WIN\":\""+win+"\"}";
			}else if (type.equals("13")) {//地暖
				String swi =(String) attributes.get("SWI");//开关
				json="{\"SWI\":\""+swi+"\"}";
			}else if (type.equals("18")) {//门磁
				String dor=(String) attributes.get("DOR");//开关
				json="{\"DOR\":\""+dor+"\"}";
			}
			
			if (!json.equals("")) {
				roomDeviceSta.setDeviceInfo(json);
			}
			
				//触摸开关面板
			switch (type) {
			case "2":// 2键触摸开关
				roomDeviceSta.setDeviceInfo("{\"SW1\":\""
						+ (String) attributes.get("SW1") + "\",\"SW2\":\""
						+ (String) attributes.get("SW2") + "\"}");
				break;
			case "3":// 3键触摸开关
				roomDeviceSta.setDeviceInfo("{\"SW1\":\""
						+ (String) attributes.get("SW1") + "\",\"SW2\":\""
						+ (String) attributes.get("SW2") + "\",\"SW3\":\""
						+ (String) attributes.get("SW3") + "\"}");
				break;
			case "4":// 4键触摸开关
				roomDeviceSta.setDeviceInfo("{\"SW1\":\""
						+ (String) attributes.get("SW1") + "\",\"SW2\":\""
						+ (String) attributes.get("SW2") + "\",\"SW3\":\""
						+ (String) attributes.get("SW3") + "\",\"SW4\":\""
						+ (String) attributes.get("SW4") + "\"}");
				break;
			case "19":// 1键触摸开关
				roomDeviceSta.setDeviceInfo("{\"SW1\":\""
						+ (String) attributes.get("SW1") + "\"}");
				break;
			}

			// 人感
			switch (type) {
			case "7":// 微波人感
				roomDeviceSta.setDeviceInfo("{\"PIR\":\""+attributes.get("PIR")+"\"}");
				break;
			case "8":// 红外人感
				roomDeviceSta.setDeviceInfo("{\"PIR\":\""+attributes.get("PIR")+"\"}");
				break;
			case "20":// 微波人感
				roomDeviceSta.setDeviceInfo("{\"PIR\":\""+attributes.get("PIR")+"\"}");
				break;
			case "21":// 红外人感
				roomDeviceSta.setDeviceInfo("{\"PIR\":\""+attributes.get("PIR")+"\"}");
				break;
			}
		}
		
		return deviceDetail;
	}
	
	public static void main(String[] args) {
		StringBuffer sb=new StringBuffer("");
		StringBuffer[] jsonArray={new StringBuffer(""),new StringBuffer(""),new StringBuffer(""),new StringBuffer("")};
		jsonArray[0].append("1");
		System.out.println(jsonArray[0]);
		System.out.println(jsonArray[1]);
		System.out.println(jsonArray[2]);
		System.out.println(jsonArray[3]);
		System.out.println("=====");
	}
}