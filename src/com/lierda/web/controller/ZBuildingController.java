package com.lierda.web.controller;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.demo.dao.test.JeecgMinidaoDao;
import org.jeecgframework.web.demo.service.test.JeecgMinidaoServiceI;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lierda.web.entity.PowerRecordingEntity;
import com.lierda.web.entity.VBuildingEntity;
import com.lierda.web.entity.ZBuildingEntity;
import com.lierda.web.entity.ZFloorEntity;
import com.lierda.web.entity.ZParkEntity;
import com.lierda.web.entity.ZRoomEntity;
import com.lierda.web.service.ZBuildingServiceI;
import com.lierda.web.service.ZFloorServiceI;
import com.lierda.web.service.ZParkServiceI;
import com.lierda.web.service.impl.ZBuildingServiceImpl;
import com.lierda.web.util.GeneralUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import java.util.Set;
import java.util.TreeMap;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 建筑物管理
 * @author zhangdaihao
 * @date 2017-05-05 11:59:01
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zBuildingController")
public class ZBuildingController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZBuildingController.class);

	@Autowired
	@Qualifier("zBuildingService")
	private ZBuildingServiceI zBuildingService;
	@Autowired
	private ZParkServiceI zParkService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private  JeecgMinidaoServiceI jeecgMinidaoService;
	@Autowired
	private Validator validator;
	
	@Autowired
	@Qualifier("zBuildingServiceMy")
	private ZBuildingServiceI zBuildingServiceMy;
	
	public static List<ZBuildingEntity> buildings;


	/**
	 * 建筑物管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/lierda/web/zBuildingList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(ZBuildingEntity zBuilding,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(VBuildingEntity.class, dataGrid);
		
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zBuilding, request.getParameterMap());
		this.zBuildingService.getDataGridReturn(cq, true);
		
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除建筑物管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ZBuildingEntity zBuilding, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zBuilding = systemService.getEntity(ZBuildingEntity.class, zBuilding.getId());
		message = "建筑物管理删除成功";
		zBuildingService.delete(zBuilding);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加建筑物管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ZBuildingEntity zBuilding, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(zBuilding.getId())) {
			message = "建筑物管理更新成功";
			ZBuildingEntity t = zBuildingService.get(ZBuildingEntity.class, zBuilding.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(zBuilding, t);
				zBuildingService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "建筑物管理更新失败";
			}
		} else {
			message = "建筑物管理添加成功";
			zBuildingService.save(zBuilding);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 建筑物管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ZBuildingEntity zBuilding, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zBuilding.getId())) {
			zBuilding = zBuildingService.getEntity(ZBuildingEntity.class, zBuilding.getId());
			ZParkEntity zPark = zParkService.getEntity(ZParkEntity.class, zBuilding.getParkid());
			req.setAttribute("zBuildingPage", zBuilding);
			req.setAttribute("zParkPage", zPark);
		}
		//List<ZParkEntity> zList = systemService.findListbySql("select * from z_park");
		List<ZParkEntity> zpark = systemService.getList(ZParkEntity.class);
		req.setAttribute("zparkList", zpark);
		return new ModelAndView("com/lierda/web/zBuilding");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZBuildingEntity> list() {
		List<ZBuildingEntity> listZBuildings=zBuildingService.getList(ZBuildingEntity.class);
		return listZBuildings;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZBuildingEntity task = zBuildingService.get(ZBuildingEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZBuildingEntity zBuilding, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZBuildingEntity>> failures = validator.validate(zBuilding);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		zBuildingService.save(zBuilding);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zBuilding.getId();
		URI uri = uriBuilder.path("/rest/zBuildingController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZBuildingEntity zBuilding) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZBuildingEntity>> failures = validator.validate(zBuilding);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		zBuildingService.saveOrUpdate(zBuilding);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		zBuildingService.deleteEntityById(ZBuildingEntity.class, id);
	}
	
	/**
	 * 获取所有建筑物的id和名称
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getAllBuildings")
	@ResponseBody
	public AjaxJson getAllBuildings(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		Map<String, Object> map=new HashMap<String, Object>();
		List<ZBuildingEntity> ids = jeecgMinidaoService
				.getAllBuildingIdAndName();// 查询所有建筑物
		map.put("buildings", ids);
		buildings=ids;
		j.setAttributes(map);
		return j;
	}	
	
	/**
	 * 根据建筑物id获取建筑物内所有的计量在各个时段的功率
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getPowerBybid")
	@ResponseBody
	public AjaxJson getPowerBybid(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, String> powerMap=new HashMap<String, String>();
		Map<String, Object> currentPower=new HashMap<String, Object>();
		Map<String, Object> currentPowerTotal=new HashMap<String, Object>();
		Map<String, float[]> typePowerMap=new HashMap<String, float[]>();
		Set<String> macids=new HashSet<String>();
		String buildId=request.getParameter("buildId");
		long timeStart=GeneralUtil.getTimesmorning();
		long timeStop=GeneralUtil.getTimesnight();
		String sql="select zpr.*,zpt.type from z_building b join z_ddc_rfbp rfbp on b.id=rfbp.buildid join z_power_recording zpr on rfbp.ddcmac=zpr.ddcmac join z_power_type zpt on zpt.devicemac=zpr.macid where b.id='"+buildId+"' and zpr.savingtime BETWEEN FROM_UNIXTIME("+timeStart+", '%Y-%m-%d %H:%i:%S') and FROM_UNIXTIME("+timeStop+", '%Y-%m-%d %H:%i:%S')";
		System.out.println(sql);
		List<PowerRecordingEntity> recordingEntities=jeecgMinidaoService.getPowerBybid(sql);
		for (PowerRecordingEntity powerRecordingEntity : recordingEntities) {
			macids.add(powerRecordingEntity.getMacid());
		}
		
//		powerMap=zBuildingService.getPowerMap(macids, recordingEntities);
//		currentPower=zBuildingService.getCurrentPower(timeStart, powerMap);
//		currentPowerTotal=zBuildingService.getTotalPower(currentPower);
//		typePowerMap=zBuildingService.getPowerByType(recordingEntities, currentPower);
		
		powerMap=zBuildingServiceMy.getPowerMap(macids, recordingEntities);
		currentPower=zBuildingServiceMy.getCurrentPower(timeStart, powerMap);
		currentPowerTotal=zBuildingServiceMy.getTotalPower(currentPower);
		typePowerMap=zBuildingServiceMy.getPowerByType(recordingEntities, currentPower);
		
		map.put("currentPower", currentPower);//含有macid和各时间段功率
		map.put("recordingEntities", recordingEntities);//当前建筑物内所有设备功率信息
		map.put("currentPowerTotal", currentPowerTotal);//各个时间段所有计量功率
		map.put("typePowerMap", typePowerMap);//按计量类型计算的各时间段功率
		j.setAttributes(map);
		return j;
	}	
	
	
	public AjaxJson getPowerBybid1(String buildId){
		AjaxJson j = new AjaxJson();
		Map<String, Object> map=new HashMap<String, Object>();
//		String buildId=request.getParameter("buildId");
		List<PowerRecordingEntity> recordingEntities=new ArrayList<PowerRecordingEntity>();
		long timeStart=new Date().getTime()/1000;
		long timeStop=timeStart+3600*10;
		String hql="select r from ZRoomEntity r where r.floorid='8a9290d85c38fd45015c3904457d0005'";
//		String sql="select zpr.* from z_building b join z_ddc_rfbp rfbp on b.id=rfbp.buildid join z_power_recording zpr on rfbp.ddcmac=zpr.macid join z_power_type zpt on zpt.devicemac=zpr.macid where b.id='"+buildId+"' and zpr.savingtime BETWEEN FROM_UNIXTIME("+timeStart+", '%Y-%m-%d %H:%i:%S') and FROM_UNIXTIME("+timeStop+", '%Y-%m-%d %H:%i:%S')";
//		if(jeecgMinidaoService.getPowerBybid(sql)!=null){
//			recordingEntities=jeecgMinidaoService.getPowerBybid(sql);
//		}
		
		j.setAttributes(map);
		return j;
	}	

	/**
	 * 获取指定建筑物内所有设备总数量和正在使用总数量
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getDeviceCount")
	@ResponseBody
	public AjaxJson getDeviceCount(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		long[] counts=new long[4];//各类型设备总数数组
		long[] usingCount=new long[4];//各类型设备正在使用的总数数组
		List<ZBuildingEntity> buildings=ZBuildingController.buildings;
		Map<String, Object> countMap=new HashMap<String, Object>();
		String buildId=request.getParameter("buildId");
		String floorId="";
		if(buildId==null||buildId.equals("")){
			buildId=buildings.get(0).getId();
		}
		List<ZFloorEntity> floors=jeecgMinidaoService.selectFloorByBuild(buildId);
		
		countMap=zBuildingService.getDeviceCount(floors);
		
		j.setAttributes(countMap);
		return j;
	}
	
	/**
	 * 获取建筑物内各个设备今日距离当前最近能耗记录和昨日最后一次能耗记录
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getDayPower")
	@ResponseBody
	public AjaxJson getDayPower(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		Map<String, Object> map=new HashMap<String, Object>();
		String buildId=request.getParameter("buildId");
		long timeStart=GeneralUtil.getTimesmorning();
		long yesterdaytStart=timeStart-3600*24;
		long yesterdaytStop=timeStart-1;
		long currentTime=new Date().getTime()/1000;
		if(buildId==null||buildId.equals("")){
			buildId=buildings.get(0).getId();
		}
		
		String macidsConditionerSql="select zpr.macid from z_building b join z_ddc_rfbp rfbp on b.id=rfbp.buildid join z_power_recording zpr on rfbp.ddcmac=zpr.ddcmac join z_power_type zpt on zpt.devicemac=zpr.macid  where b.id='"+buildId+"' and zpt.type='15' group by zpr.macid";
		String macidsLightSql="select zpr.macid from z_building b join z_ddc_rfbp rfbp on b.id=rfbp.buildid join z_power_recording zpr on rfbp.ddcmac=zpr.ddcmac join z_power_type zpt on zpt.devicemac=zpr.macid  where b.id='"+buildId+"' and zpt.type='1' group by zpr.macid";
//		//插座
//		String stripToday="select zpr.*,zpt.type from z_building b join z_ddc_rfbp rfbp on b.id=rfbp.buildid join z_power_recording zpr on rfbp.ddcmac=zpr.ddcmac join z_power_type zpt on zpt.devicemac=zpr.macid  where b.id='"+buildId+"' and zpr.savingtime BETWEEN FROM_UNIXTIME("+timeStart+", '%Y-%m-%d %H:%i:%S') and FROM_UNIXTIME("+currentTime+", '%Y-%m-%d %H:%i:%S') and zpt.type='26' order  by  zpr.savingtime desc limit 1";
//		String stripYesterday="select zpr.*,zpt.type from z_building b join z_ddc_rfbp rfbp on b.id=rfbp.buildid join z_power_recording zpr on rfbp.ddcmac=zpr.ddcmac join z_power_type zpt on zpt.devicemac=zpr.macid  where b.id='"+buildId+"' and zpr.savingtime BETWEEN FROM_UNIXTIME("+yesterdaytStart+", '%Y-%m-%d %H:%i:%S') and FROM_UNIXTIME("+yesterdaytStop+", '%Y-%m-%d %H:%i:%S') and zpt.type='26' order  by  zpr.savingtime desc limit 1";
//		//空调
//		String conditionerToday="select zpr.*,zpt.type from z_building b join z_ddc_rfbp rfbp on b.id=rfbp.buildid join z_power_recording zpr on rfbp.ddcmac=zpr.ddcmac join z_power_type zpt on zpt.devicemac=zpr.macid  where b.id='"+buildId+"' and zpr.savingtime BETWEEN FROM_UNIXTIME("+timeStart+", '%Y-%m-%d %H:%i:%S') and FROM_UNIXTIME("+currentTime+", '%Y-%m-%d %H:%i:%S') and zpt.type='15' order  by  zpr.savingtime desc limit 1";
//		String conditionerYesterday="select zpr.*,zpt.type from z_building b join z_ddc_rfbp rfbp on b.id=rfbp.buildid join z_power_recording zpr on rfbp.ddcmac=zpr.ddcmac join z_power_type zpt on zpt.devicemac=zpr.macid  where b.id='"+buildId+"' and zpr.savingtime BETWEEN FROM_UNIXTIME("+yesterdaytStart+", '%Y-%m-%d %H:%i:%S') and FROM_UNIXTIME("+yesterdaytStop+", '%Y-%m-%d %H:%i:%S') and zpt.type='15' order  by  zpr.savingtime desc limit 1";
		

		StringBuffer TodayPre=new StringBuffer("select max(zpr.dayPower) as dayPower from z_building b join z_ddc_rfbp rfbp on b.id=rfbp.buildid join z_power_recording zpr on rfbp.ddcmac=zpr.ddcmac join z_power_type zpt on zpt.devicemac=zpr.macid  where b.id='"+buildId+"' and zpr.savingtime BETWEEN FROM_UNIXTIME("+timeStart+", '%Y-%m-%d %H:%i:%S') and FROM_UNIXTIME("+currentTime+", '%Y-%m-%d %H:%i:%S') and (");
		StringBuffer YesterdayPre=new StringBuffer("select max(zpr.dayPower)  as dayPower from z_building b join z_ddc_rfbp rfbp on b.id=rfbp.buildid join z_power_recording zpr on rfbp.ddcmac=zpr.ddcmac join z_power_type zpt on zpt.devicemac=zpr.macid  where b.id='"+buildId+"' and zpr.savingtime BETWEEN FROM_UNIXTIME("+yesterdaytStart+", '%Y-%m-%d %H:%i:%S') and FROM_UNIXTIME("+yesterdaytStop+", '%Y-%m-%d %H:%i:%S') and (");
		//照明
		String lightToday="";
		String lightYesterday="";
		//空调
		String conditionerToday="";
		String conditionerYesterday="";
		
		
//		String conditionerToday="select zpr.*,zpt.type from z_building b join z_ddc_rfbp rfbp on b.id=rfbp.buildid join z_power_recording zpr on rfbp.ddcmac=zpr.ddcmac join z_power_type zpt on zpt.devicemac=zpr.macid  where b.id='"+buildId+"' and zpr.savingtime BETWEEN FROM_UNIXTIME("+timeStart+", '%Y-%m-%d %H:%i:%S') and FROM_UNIXTIME("+currentTime+", '%Y-%m-%d %H:%i:%S') and zpt.type='15' order  by  zpr.savingtime desc limit 1";
//		String conditionerYesterday="select zpr.*,zpt.type from z_building b join z_ddc_rfbp rfbp on b.id=rfbp.buildid join z_power_recording zpr on rfbp.ddcmac=zpr.ddcmac join z_power_type zpt on zpt.devicemac=zpr.macid  where b.id='"+buildId+"' and zpr.savingtime BETWEEN FROM_UNIXTIME("+yesterdaytStart+", '%Y-%m-%d %H:%i:%S') and FROM_UNIXTIME("+yesterdaytStop+", '%Y-%m-%d %H:%i:%S') and zpt.type='15' order  by  zpr.savingtime desc limit 1";
//		
		List<String> macidsConditioner=zBuildingService.findListbySql(macidsConditionerSql);
		List<String> macidsLight=zBuildingService.findListbySql(macidsLightSql);
		System.out.println(macidsConditionerSql);
		System.out.println(macidsLightSql);
		//照明
		for (String string : macidsLight) {
			TodayPre.append(" zpr.macid='"+string+"' or");
			lightToday="select sum(a.dayPower) from ("+TodayPre.toString().substring(0, TodayPre.length()-2)+") group by zpr.macid) a";
			YesterdayPre.append(" zpr.macid='"+string+"' or");
			lightYesterday="select sum(a.dayPower) from ("+YesterdayPre.toString().substring(0, YesterdayPre.length()-2)+") group by zpr.macid) a";
		}
//		for (String string : macidsStrip) {
//			YesterdayPre.append(" zpr.macid='"+string+"' or");
//			stripYesterday="select sum(a.dayPower) from ("+YesterdayPre.toString().substring(0, YesterdayPre.length()-2)+"group by zpr.macid) a";
//		}
		//空调
		for (String string : macidsConditioner) {
			TodayPre.append(" zpr.macid='"+string+"' or");
			conditionerToday="select sum(a.dayPower) from ("+TodayPre.toString().substring(0, TodayPre.length()-2)+") group by zpr.macid) a";
			YesterdayPre.append(" zpr.macid='"+string+"' or");
			conditionerYesterday="select sum(a.dayPower) from ("+YesterdayPre.toString().substring(0, YesterdayPre.length()-2)+") group by zpr.macid) a";
		}
//		for (String string : macidsConditioner) {
//			YesterdayPre.append(" zpr.macid='"+string+"' or");
//			conditionerYesterday="select sum(a.dayPower) from ("+YesterdayPre.toString().substring(0, YesterdayPre.length()-2)+"group by zpr.macid) a";
//		}
		System.out.println("111111111");
		System.out.println(lightToday);
		System.out.println("222222222");
		System.out.println(lightYesterday);
		System.out.println("33333333333");
		System.out.println(conditionerToday);
		System.out.println("44444444444444");
		System.out.println(conditionerYesterday);
//		List<PowerRecordingEntity> stripRecordToday=jeecgMinidaoService.getPowerBybid(stripToday);
//		List<PowerRecordingEntity> stripRecordYesterday=jeecgMinidaoService.getPowerBybid(stripYesterday);
//		List<PowerRecordingEntity> conditionerRecordToday=jeecgMinidaoService.getPowerBybid(conditionerToday);
//		List<PowerRecordingEntity> conditionerRecordYesterday=jeecgMinidaoService.getPowerBybid(conditionerYesterday);
		map.put("lightRecordToday", lightToday.equals("")?0:zBuildingService.findListbySql(lightToday).get(0));
		map.put("lightRecordYesterday", lightYesterday.equals("")?0:zBuildingService.findListbySql(lightYesterday).get(0));
		map.put("conditionerRecordToday", conditionerToday.equals("")?0:zBuildingService.findListbySql(conditionerToday).get(0));
		map.put("conditionerRecordYesterday", conditionerYesterday.equals("")?0:zBuildingService.findListbySql(conditionerYesterday).get(0));
		
		j.setAttributes(map);
		return j;
	}
	
	public static void main(String[] args) {
//		System.out.println(new Date().getTime()/1000);
//		System.out.println(1496647496-3600);
//		System.out.println(new Date().getTime()/1000<(new Date().getTime()/1000-1000));
//		System.out.println(1496678400);
		System.out.println(new Date().getTime()/1000);
//		System.out.println((new Date().getTime()/1000-1496678400)/3600);
//		ArrayList<String> l=new ArrayList<String>();
////		l.add(0, "klkkk");
//		System.out.println(l.get(0));
		
		String a="123";
		a.substring(0, a.length()-2);
		System.out.println(a);
	}
}
