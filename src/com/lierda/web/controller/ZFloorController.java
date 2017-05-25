package com.lierda.web.controller;
import java.util.List;

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
import com.lierda.web.entity.VFloorEntity;
import com.lierda.web.entity.ZBuildingEntity;
import com.lierda.web.entity.ZFloorEntity;
import com.lierda.web.entity.ZParkEntity;
import com.lierda.web.service.ZBuildingServiceI;
import com.lierda.web.service.ZFloorServiceI;

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
}
