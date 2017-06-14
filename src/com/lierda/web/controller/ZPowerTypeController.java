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

import com.lierda.web.entity.VPowertypeEntity;
import com.lierda.web.entity.ZDeviceTypeEntity;
import com.lierda.web.entity.ZPowerTypeEntity;
import com.lierda.web.service.ZDeviceTypeServiceI;
import com.lierda.web.service.ZPowerTypeServiceI;

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
 * @Description: 计量类型
 * @author zhangdaihao
 * @date 2017-06-07 14:39:28
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zPowerTypeController")
public class ZPowerTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZPowerTypeController.class);

	@Autowired
	private ZPowerTypeServiceI zPowerTypeService;
	@Autowired
	private ZDeviceTypeServiceI ZDeviceTypeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 计量类型列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/lierda/web/zPowerTypeList");
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
	public void datagrid(ZPowerTypeEntity zPowerType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(VPowertypeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zPowerType, request.getParameterMap());
		this.zPowerTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除计量类型
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ZPowerTypeEntity zPowerType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zPowerType = systemService.getEntity(ZPowerTypeEntity.class, zPowerType.getId());
		message = "计量类型删除成功";
		zPowerTypeService.delete(zPowerType);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加计量类型
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ZPowerTypeEntity zPowerType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(zPowerType.getId())) {
			message = "计量类型更新成功";
			ZPowerTypeEntity t = zPowerTypeService.get(ZPowerTypeEntity.class, zPowerType.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(zPowerType, t);
				zPowerTypeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "计量类型更新失败";
			}
		} else {
			message = "计量类型添加成功";
			zPowerTypeService.save(zPowerType);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 计量类型列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ZPowerTypeEntity zPowerType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zPowerType.getId())) {
			zPowerType = zPowerTypeService.getEntity(ZPowerTypeEntity.class, zPowerType.getId());
			ZDeviceTypeEntity type = ZDeviceTypeService.getEntity(ZDeviceTypeEntity.class, zPowerType.getType());
			String typename = type.getTypename();
			req.setAttribute("zPowerTypePage", zPowerType);
			req.setAttribute("typename", typename);
		}
		return new ModelAndView("com/lierda/web/zPowerType");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZPowerTypeEntity> list() {
		List<ZPowerTypeEntity> listZPowerTypes=zPowerTypeService.getList(ZPowerTypeEntity.class);
		return listZPowerTypes;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZPowerTypeEntity task = zPowerTypeService.get(ZPowerTypeEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZPowerTypeEntity zPowerType, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZPowerTypeEntity>> failures = validator.validate(zPowerType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		zPowerTypeService.save(zPowerType);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		Integer id = zPowerType.getId();
		URI uri = uriBuilder.path("/rest/zPowerTypeController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZPowerTypeEntity zPowerType) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZPowerTypeEntity>> failures = validator.validate(zPowerType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		zPowerTypeService.saveOrUpdate(zPowerType);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		zPowerTypeService.deleteEntityById(ZPowerTypeEntity.class, id);
	}
}
