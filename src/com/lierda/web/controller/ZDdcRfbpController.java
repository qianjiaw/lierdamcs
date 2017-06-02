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

import com.lierda.web.entity.VBuildingEntity;
import com.lierda.web.entity.VDdcRfbpEntity;
import com.lierda.web.entity.VFloorEntity;
import com.lierda.web.entity.ZBuildingEntity;
import com.lierda.web.entity.ZDdcRfbpEntity;
import com.lierda.web.entity.ZFloorEntity;
import com.lierda.web.entity.ZParkEntity;
import com.lierda.web.entity.ZRoomEntity;
import com.lierda.web.resultEntity.ZFloorResult;
import com.lierda.web.service.ZBuildingServiceI;
import com.lierda.web.service.ZDdcRfbpServiceI;
import com.lierda.web.service.ZFloorServiceI;
import com.lierda.web.service.ZParkServiceI;
import com.lierda.web.service.ZRoomServiceI;
import com.lierda.web.service.impl.ZFloorServiceImpl;
import com.lierda.web.service.impl.ZParkServiceImpl;

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

import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: ddc关联表
 * @author zhangdaihao
 * @date 2017-05-26 17:47:32
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zDdcRfbpController")
public class ZDdcRfbpController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZDdcRfbpController.class);

	@Autowired
	private ZDdcRfbpServiceI zDdcRfbpService;
	@Autowired
	private ZParkServiceI ZParkService;
	@Autowired
	private ZBuildingServiceI ZBuildingService;
	@Autowired
	private ZFloorServiceI ZFloorService;
	@Autowired
	private ZRoomServiceI ZRoomService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private JeecgMinidaoServiceI jeecgMinidaoService;
	@Autowired
	private Validator validator;
	


	/**
	 * ddc关联表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/lierda/web/zDdcRfbpList");
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
	public void datagrid(ZDdcRfbpEntity zDdcRfbp,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(VDdcRfbpEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zDdcRfbp, request.getParameterMap());
		this.zDdcRfbpService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除ddc关联表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ZDdcRfbpEntity zDdcRfbp, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zDdcRfbp = systemService.getEntity(ZDdcRfbpEntity.class, zDdcRfbp.getId());
		message = "ddc关联表删除成功";
		zDdcRfbpService.delete(zDdcRfbp);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加ddc关联表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ZDdcRfbpEntity zDdcRfbp, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(zDdcRfbp.getId())) {
			message = "ddc关联表更新成功";
			ZDdcRfbpEntity t = zDdcRfbpService.get(ZDdcRfbpEntity.class, zDdcRfbp.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(zDdcRfbp, t);
				zDdcRfbpService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "ddc关联表更新失败";
			}
		} else {
			message = "ddc关联表添加成功";
			zDdcRfbpService.save(zDdcRfbp);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * ddc关联表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ZDdcRfbpEntity zDdcRfbp, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zDdcRfbp.getId())) {
			zDdcRfbp = zDdcRfbpService.getEntity(ZDdcRfbpEntity.class, zDdcRfbp.getId());
			ZParkEntity zp = ZParkService.getEntity(ZParkEntity.class, zDdcRfbp.getParkid());
			ZBuildingEntity zb = ZBuildingService.getEntity(ZBuildingEntity.class, zDdcRfbp.getBuildid());
			ZFloorEntity zf = ZFloorService.getEntity(ZFloorEntity.class, zDdcRfbp.getFloorid());
			ZRoomEntity zr = ZRoomService.getEntity(ZRoomEntity.class, zDdcRfbp.getRoomid());
			req.setAttribute("zDdcRfbpPage", zDdcRfbp);
			req.setAttribute("zp", zp);
			req.setAttribute("zb", zb);
			req.setAttribute("zf", zf);
			req.setAttribute("zr", zr);
		}
		List<ZParkController> zpark = systemService.getList(ZParkEntity.class);
		req.setAttribute("zparkList", zpark);
		return new ModelAndView("com/lierda/web/zDdcRfbp");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZDdcRfbpEntity> list() {
		List<ZDdcRfbpEntity> listZDdcRfbps=zDdcRfbpService.getList(ZDdcRfbpEntity.class);
		return listZDdcRfbps;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZDdcRfbpEntity task = zDdcRfbpService.get(ZDdcRfbpEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZDdcRfbpEntity zDdcRfbp, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZDdcRfbpEntity>> failures = validator.validate(zDdcRfbp);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		zDdcRfbpService.save(zDdcRfbp);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zDdcRfbp.getId();
		URI uri = uriBuilder.path("/rest/zDdcRfbpController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZDdcRfbpEntity zDdcRfbp) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZDdcRfbpEntity>> failures = validator.validate(zDdcRfbp);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		zDdcRfbpService.saveOrUpdate(zDdcRfbp);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		zDdcRfbpService.deleteEntityById(ZDdcRfbpEntity.class, id);
	}
	
	/**
	 * 获取当前园区下所有建筑物id和name
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getAllBuildingsByParkId")
	@ResponseBody
	public AjaxJson getAllBuildingsByParkId(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		Map<String, Object> map=new HashMap<String, Object>();
		String parkid=request.getParameter("parkid");
		List<ZBuildingEntity> ids=jeecgMinidaoService.getAllBuildingsByParkId(parkid);//查询所有建筑物
		System.out.println(ids);
		map.put("buildings", ids);
		j.setAttributes(map);
		return j;
	}	
	
	/**
	 * 获取当前建筑下所有楼层id和name
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getAllFloorsByBuildId")
	@ResponseBody
	public AjaxJson getAllFloorsByBuildId(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		Map<String, Object> map=new HashMap<String, Object>();
		String buildid=request.getParameter("buildid");
		List<ZFloorEntity> floors=jeecgMinidaoService.getAllFloorsByBuildId(buildid);//查询所有建筑物
		map.put("floors", floors);
		j.setAttributes(map);
		return j;
	}
	
	/**
	 * 获取当前楼层下的下所有房间id和name
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getAllRoomsByFloorId")
	@ResponseBody
	public AjaxJson getAllRoomsByFloorId(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		Map<String, Object> map=new HashMap<String, Object>();
		String floorid=request.getParameter("floorid");
		List<ZRoomEntity> rooms=jeecgMinidaoService.selectRoomByFloor(floorid);//查询所有建筑物
		map.put("rooms", rooms);
		j.setAttributes(map);
		return j;
	}

}
