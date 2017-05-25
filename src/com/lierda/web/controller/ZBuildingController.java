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
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.lierda.web.entity.VBuildingEntity;
import com.lierda.web.entity.ZBuildingEntity;
import com.lierda.web.entity.ZParkEntity;
import com.lierda.web.service.ZBuildingServiceI;
import com.lierda.web.service.ZParkServiceI;

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
	private ZBuildingServiceI zBuildingService;
	@Autowired
	private ZParkServiceI zParkService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


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
}
