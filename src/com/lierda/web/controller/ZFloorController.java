package com.lierda.web.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.jeecgframework.web.demo.service.test.JeecgMinidaoServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.lierda.web.entity.VBuildingEntity;
import com.lierda.web.entity.VFloorEntity;
import com.lierda.web.entity.ZBuildingEntity;
import com.lierda.web.entity.ZFloorEntity;
import com.lierda.web.entity.ZRoomEntity;
import com.lierda.web.resultEntity.JsonResult;
import com.lierda.web.resultEntity.SqlResult;
import com.lierda.web.resultEntity.ZFloorResult;
import com.lierda.web.service.ZBuildingServiceI;
import com.lierda.web.service.ZFloorServiceI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**   
 * @Title: Controller
 * @Description: 楼层管理
 * @author zhangdaihao
 * @date 2017-05-05 11:59:18
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zFloorController")
public class ZFloorController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZFloorController.class);

	@Autowired
	private ZFloorServiceI zFloorService;
	@Autowired
	private ZBuildingServiceI zBuildingService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private JeecgMinidaoServiceI jeecgMinidaoService;
	@Autowired
	@Qualifier("zFloorService")
	private ZFloorServiceI floorServiceImpl;
	@Autowired
	private Validator validator;
	
	/**
	 * 楼层管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/lierda/web/zFloorList");
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
	public void datagrid(ZFloorEntity zFloor,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(VFloorEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zFloor, request.getParameterMap());
		this.zFloorService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除楼层管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ZFloorEntity zFloor, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zFloor = systemService.getEntity(ZFloorEntity.class, zFloor.getId());
		message = "楼层管理删除成功";
		zFloorService.delete(zFloor);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加楼层管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ZFloorEntity zFloor, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(zFloor.getId())) {
			message = "楼层管理更新成功";
			ZFloorEntity t = zFloorService.get(ZFloorEntity.class, zFloor.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(zFloor, t);
				zFloorService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "楼层管理更新失败";
			}
		} else {
			message = "楼层管理添加成功";
			zFloorService.save(zFloor);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 楼层管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ZFloorEntity zFloor, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zFloor.getId())) {
			zFloor = zFloorService.getEntity(ZFloorEntity.class, zFloor.getId());
			ZBuildingEntity zBuilding = zBuildingService.getEntity(ZBuildingEntity.class, zFloor.getBuildingid());
			req.setAttribute("zFloorPage", zFloor);
			req.setAttribute("zBuildingPage", zBuilding);
		}
		List<VBuildingEntity> zbuilding = systemService.getList(VBuildingEntity.class);
		req.setAttribute("zbuildingList", zbuilding);
		return new ModelAndView("com/lierda/web/zFloor");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZFloorEntity> list() {
		List<ZFloorEntity> listZFloors=zFloorService.getList(ZFloorEntity.class);
		return listZFloors;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZFloorEntity task = zFloorService.get(ZFloorEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZFloorEntity zFloor, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZFloorEntity>> failures = validator.validate(zFloor);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		zFloorService.save(zFloor);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zFloor.getId();
		URI uri = uriBuilder.path("/rest/zFloorController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZFloorEntity zFloor) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZFloorEntity>> failures = validator.validate(zFloor);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		zFloorService.saveOrUpdate(zFloor);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		zFloorService.deleteEntityById(ZFloorEntity.class, id);
	}
	
	
	/**
	 * 通过建筑物id获取楼层id和名称
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getFloorNum")
	@ResponseBody
	public AjaxJson getFloorNum(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		List<ZBuildingEntity> ids = null;
		Map<String, Object> map = new HashMap<String, Object>();
		String buildId = request.getParameter("buildId");
		if (buildId == null || buildId.equals("")) {
			// buildId="8a9290d85be74999015be74bca0b0000";
			ids = jeecgMinidaoService.getAllBuildingIdAndName();// 查询所有建筑物
			buildId = ids.get(0).getId();
		}
		List<ZFloorEntity> floors=jeecgMinidaoService.selectFloorByBuild(buildId);
//		select new Link(id,name) from Link
		List<ZFloorResult> floors1=
				null;
		floors1=zFloorService.findHql("select new com.lierda.web.resultEntity.ZFloorResult(id,floorname) from ZFloorEntity where buildingid=?", new String[]{""+buildId+""});
//		System.out.println(buildId+"BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
//		List<ZFloorEntity> floors=zFloorService.findHql("select f  from ZFloorEntity f where buildingid=?",new String[]{buildId});
//		for (ZFloorResult zFloorEntity : floors1) {
//			System.out.println(zFloorEntity.getFloorname()+"LOOK");
//			System.out.println(zFloorEntity.getId()+"KKKKKKKKKKKKKKKKKK");
////			zFloorService.f
//			
//		}
		
		map.put("buildings", jeecgMinidaoService.getAllBuildingIdAndName());
//		System.out.println(buildId);
		map.put("floors", jeecgMinidaoService.selectFloorByBuild(buildId));
		j.setAttributes(map);
		return j;
	}
		
	/**
	 * 根据楼层id获取(所有建筑物，该楼层房间，该楼层)的id和名称
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getFloorDetail")
	@ResponseBody
	public AjaxJson getFloorDetail(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String floorid=request.getParameter("floorid");
		String buildId=request.getParameter("buildId");
		Map<String, Object> map=new HashMap<String, Object>();
		List<ZBuildingEntity> ids=null;
		if(buildId==null||buildId.equals("")){
//			buildId="8a9290d85be74999015be74bca0b0000";
			ids=jeecgMinidaoService.getAllBuildingIdAndName();//查询所有建筑物
			buildId=ids.get(0).getId();
		}
		List<ZRoomEntity> rooms=jeecgMinidaoService.selectRoomByFloor(floorid);
		List<ZFloorEntity> floor=jeecgMinidaoService.selectFloorById(floorid);
		map.put("buildings", ids);
		map.put("rooms", rooms);
		map.put("floor", floor);//当前楼层
		map.put("floors", jeecgMinidaoService.selectFloorByBuild(buildId));//建筑物对应所有楼层
		j.setAttributes(map);
		return j;
	}
	
	/**
	 * 根据楼层id获取(该楼层)的id和名称
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getCurrentFloor")
	@ResponseBody
	public AjaxJson getCurrentFloor(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String floorid=request.getParameter("floorid");
		Map<String, Object> map=new HashMap<String, Object>();
		List<ZFloorEntity> floor=jeecgMinidaoService.selectFloorById(floorid);
		map.put("floor", floor);//当前楼层
		j.setAttributes(map);
		return j;
	}
	
	/**
	 * 根据楼层id获取该楼层房间的id和名称
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getRoomsByFloorId")
	@ResponseBody
	public AjaxJson getRoomsByFloorId(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String floorid=request.getParameter("floorid");
		Map<String, Object> map=new HashMap<String, Object>();
		List<ZRoomEntity> rooms=jeecgMinidaoService.selectRoomByFloor(floorid);
		map.put("rooms", rooms);
		j.setAttributes(map);
		return j;
	}
	
	/**
	 * 根据楼层buildId获取建筑物所有楼层的id和名称
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getFloorsInBuidings")
	@ResponseBody
	public AjaxJson getFloorsInBuidings(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String buildId=request.getParameter("buildId");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("floors", jeecgMinidaoService.selectFloorByBuild(buildId));//建筑物对应所有楼层
		j.setAttributes(map);
		return j;
	}
	
	/**
	 * 根据楼层buildId获取(所有建筑物,当前建筑物,当前建筑物的所有楼层)的id和名称
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getDetailByBuildingid")
	@ResponseBody
	public AjaxJson getDetailByBuildingid(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		Map<String, Object> map=new HashMap<String, Object>();
		List<ZBuildingEntity> currentBuild=null;
		String buildId=request.getParameter("buildId");
		List<ZBuildingEntity> allBuildings=ZBuildingController.buildings;
		if(buildId==null||buildId.equals("")){
			buildId=allBuildings.get(0).getId();
		}
		map.put("currentBuild", jeecgMinidaoService.getBuildingBybuildingid(buildId));
		map.put("allBuildings", allBuildings);
		map.put("floors", jeecgMinidaoService.selectFloorByBuild(buildId));//建筑物对应所有楼层
		j.setAttributes(map);
		return j;
	}
	
	/**
	 * 根据楼层floorid获取(所有建筑物,当前建筑物,当前建筑物的所有楼层,当前楼层，当前楼层的所有房间)的id和名称
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getDetailByFloorid")
	@ResponseBody
	public AjaxJson getDetailByFloorid(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		Map<String, Object> map=new HashMap<String, Object>();
		List<ZFloorEntity> currentBuilding=null;
		String currentBuildingId="";
		String floorid=request.getParameter("floorid");
		List<ZBuildingEntity> allBuildings=ZBuildingController.buildings;
		if(floorid==null||floorid.equals("")){
			floorid=ZBuildingController.floors.get(0).getId();
		}
		currentBuilding=jeecgMinidaoService.getBuildingByFloorId(floorid);
		currentBuildingId=currentBuilding.get(0).getBuildingid();
		map.put("allBuildings", allBuildings);
		map.put("building", jeecgMinidaoService.getBuildingBybuildingid(currentBuildingId));
		map.put("rooms",jeecgMinidaoService.selectRoomByFloor(floorid));
		map.put("floor", jeecgMinidaoService.selectFloorById(floorid));
		map.put("floors", jeecgMinidaoService.selectFloorByBuild(currentBuildingId));
		j.setAttributes(map);
		return j;
	}
	
	
	
	/**
	 * 根据楼层floorId获取建筑物的id和名称
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getBuidingByFloorId")
	@ResponseBody
	public AjaxJson getBuidingByFloorId(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String floorid=request.getParameter("floorid");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("building", jeecgMinidaoService.getBuildingByFloorId(floorid));//建筑物对应所有楼层
		j.setAttributes(map);
		return j;
	}
	
	/**
	 * 根据房间id和设备类型获取mac地址全0的设备
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getAllDeviceByRAT")
	@ResponseBody
	public AjaxJson getAllDeviceByRAT(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String  s=null;
		List<SqlResult> r=null;
//		List<String> types=null;
//		List<String> allTypes=new ArrayList<String>();
		String sql="select device.macid as macid, ddc.ddcmac as ddcmac,ddc.serverip as serverip,ddc.id as ddcid, device.id as deviceid from z_room r join z_ddc_rfbp rfbp on r.id=rfbp.roomid join z_ddc ddc on ddc.ddcmac=rfbp.ddcmac join z_device device on device.ddcId=ddc.id join z_devicetype devicetype on devicetype.id=device.type where ";
		String roomtypedata=request.getParameter("roomtypedata");
		Map<String, Object> map=new HashMap<String, Object>();
//		List<JsonResult> list1=new ArrayList<JsonResult>();
//		List<String> list=new ArrayList<String>();

		JSONObject object=JSON.parseObject(roomtypedata);
		Object  data= object.get("data");
		List<JsonResult> rs= (List<JsonResult>) JSON.parseArray(data+"", JsonResult.class);
//		for (JsonResult jsonResult : rs) {
//			types=jsonResult.getType();
//			for (String type : types) {
//				allTypes.add(type);
//			}
//		}
		sql=floorServiceImpl.getSql(rs, sql);
		r=jeecgMinidaoService.getAllDeviceByRAT(sql);
		map.put("result", r);
//		map.put("allTypes", allTypes);
		j.setAttributes(map);
		return j;
	}
	
	
	/**
	 * 获取指定楼层所有房间的设备状态
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getDeviceStatus")
	@ResponseBody
	public AjaxJson getDeviceStatus(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		List<ZBuildingEntity> buildings = ZBuildingController.buildings;
		String buildId = buildings.get(0).getId();
		Map<String, Object> map = new HashMap<String, Object>();
		String floorid = request.getParameter("floorid");
		if(floorid==null||floorid.equals("")){
			floorid=jeecgMinidaoService.selectFloorByBuild(buildId).get(0).getId();
		}
		map = floorServiceImpl.getDeviceStatus(floorid, "");
		j.setAttributes(map);
		return j;
	}
	
}
