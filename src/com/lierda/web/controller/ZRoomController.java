package com.lierda.web.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.lierda.web.entity.VFloorEntity;
import com.lierda.web.entity.VRoomEntity;
import com.lierda.web.entity.ZBuildingEntity;
import com.lierda.web.entity.ZFloorEntity;
import com.lierda.web.entity.ZRoomEntity;
import com.lierda.web.service.ZFloorServiceI;
import com.lierda.web.service.ZRoomServiceI;

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

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.io.Console;
import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 房间管理
 * @author zhangdaihao
 * @date 2017-05-05 11:59:35
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zRoomController")
public class ZRoomController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZRoomController.class);

	@Autowired
	private ZRoomServiceI zRoomService;
	@Autowired
	private ZFloorServiceI zFloorService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private JeecgMinidaoServiceI jeecgMinidaoService;
	@Autowired
	private Validator validator;
	


	/**
	 * 房间管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/lierda/web/zRoomList");
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
	public void datagrid(ZRoomEntity zRoom,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(VRoomEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zRoom, request.getParameterMap());
		this.zRoomService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除房间管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ZRoomEntity zRoom, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zRoom = systemService.getEntity(ZRoomEntity.class, zRoom.getId());
		message = "房间管理删除成功";
		zRoomService.delete(zRoom);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加房间管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ZRoomEntity zRoom, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(zRoom.getId())) {
			message = "房间管理更新成功";
			ZRoomEntity t = zRoomService.get(ZRoomEntity.class, zRoom.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(zRoom, t);
				zRoomService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "房间管理更新失败";
			}
		} else {
			message = "房间管理添加成功";
			zRoomService.save(zRoom);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 房间管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ZRoomEntity zRoom, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zRoom.getId())) {
			zRoom = zRoomService.getEntity(ZRoomEntity.class, zRoom.getId());
			ZFloorEntity zFloor = zFloorService.getEntity(ZFloorEntity.class, zRoom.getFloorid());
			req.setAttribute("zRoomPage", zRoom);
			req.setAttribute("zFloorPage", zFloor);
		}
		List<VFloorEntity> zfloor = systemService.getList(VFloorEntity.class);
		req.setAttribute("zfloorList", zfloor);
		return new ModelAndView("com/lierda/web/zRoom");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZRoomEntity> list() {
		List<ZRoomEntity> listZRooms=zRoomService.getList(ZRoomEntity.class);
		return listZRooms;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZRoomEntity task = zRoomService.get(ZRoomEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZRoomEntity zRoom, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZRoomEntity>> failures = validator.validate(zRoom);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		zRoomService.save(zRoom);
		
		

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zRoom.getId();
		URI uri = uriBuilder.path("/rest/zRoomController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZRoomEntity zRoom) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZRoomEntity>> failures = validator.validate(zRoom);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		zRoomService.saveOrUpdate(zRoom);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		zRoomService.deleteEntityById(ZRoomEntity.class, id);
	}
	
	/**
	 * 根据楼层roomid获取(所有建筑物,当前建筑物,当前建筑物的所有楼层,当前楼层,当前房间,房间内所有ddc,ddc对应device)的id和名称
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getDetailByRoomid")
	@ResponseBody
	public AjaxJson getDetailByRoomid(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String roomid = request.getParameter("roomid");
		System.out.println(roomid+"..............................");
		List<ZFloorEntity> currentBuildingId = null;
		List<ZBuildingEntity> currentBuilding = null;
		String floorid = "";
		String buildid = "";
		Map<String, Object> map = new HashMap<String, Object>();
		List<ZBuildingEntity> allBuildings = ZBuildingController.buildings;
		if(roomid==null||roomid.equals("")){
			buildid=allBuildings.get(0).getId();
			floorid=(String) zFloorService.findListbySql("select id from z_floor where buildingid='"+buildid+"'").get(0);
			roomid=(String) zRoomService.findListbySql("select id from z_room where floorid='"+floorid+"'").get(0);
		}
		List<ZRoomEntity> currentFloorId = jeecgMinidaoService.getFloorByRoomId(roomid);
		floorid = currentFloorId.get(0).getFloorid();// 当前楼层id
		List<ZFloorEntity> currentFloor=jeecgMinidaoService.selectFloorById(floorid);
		currentBuildingId= jeecgMinidaoService.getBuildingByFloorId(floorid);
		currentBuilding=jeecgMinidaoService.getBuildingBybuildingid(currentBuildingId.get(0).getBuildingid());
		buildid = currentBuildingId.get(0).getBuildingid();// 当前建筑物id
		map.put("devices", jeecgMinidaoService.getDeviceByRoomid(roomid));
		map.put("ddcs", jeecgMinidaoService.getDdcByRoomId(roomid));
		map.put("currentRoom", jeecgMinidaoService.getRoomByRoomId(roomid));
		map.put("floors", jeecgMinidaoService.selectFloorByBuild(buildid));
		map.put("currentBuilding", currentBuilding);
		map.put("allBuildings", allBuildings);
		map.put("currentFloor", currentFloor);
		j.setAttributes(map);
		return j;
	}
}
