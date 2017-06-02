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

import com.lierda.web.entity.ZDeviceTypeEntity;
import com.lierda.web.service.ZDeviceTypeServiceI;

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
 * @Description: 设备类型
 * @author zhangdaihao
 * @date 2017-06-02 15:54:09
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/zDeviceTypeController")
public class ZDeviceTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ZDeviceTypeController.class);

	@Autowired
	private ZDeviceTypeServiceI zDeviceTypeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 设备类型列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/lierda/web/zDeviceTypeList");
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
	public void datagrid(ZDeviceTypeEntity zDeviceType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ZDeviceTypeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, zDeviceType, request.getParameterMap());
		this.zDeviceTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除设备类型
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ZDeviceTypeEntity zDeviceType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		zDeviceType = systemService.getEntity(ZDeviceTypeEntity.class, zDeviceType.getId());
		message = "设备类型删除成功";
		zDeviceTypeService.delete(zDeviceType);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加设备类型
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ZDeviceTypeEntity zDeviceType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(zDeviceType.getId())) {
			message = "设备类型更新成功";
			ZDeviceTypeEntity t = zDeviceTypeService.get(ZDeviceTypeEntity.class, zDeviceType.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(zDeviceType, t);
				zDeviceTypeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "设备类型更新失败";
			}
		} else {
			message = "设备类型添加成功";
			zDeviceTypeService.save(zDeviceType);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 设备类型列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ZDeviceTypeEntity zDeviceType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(zDeviceType.getId())) {
			zDeviceType = zDeviceTypeService.getEntity(ZDeviceTypeEntity.class, zDeviceType.getId());
			req.setAttribute("zDeviceTypePage", zDeviceType);
		}
		return new ModelAndView("com/lierda/web/zDeviceType");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ZDeviceTypeEntity> list() {
		List<ZDeviceTypeEntity> listZDeviceTypes=zDeviceTypeService.getList(ZDeviceTypeEntity.class);
		return listZDeviceTypes;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ZDeviceTypeEntity task = zDeviceTypeService.get(ZDeviceTypeEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ZDeviceTypeEntity zDeviceType, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZDeviceTypeEntity>> failures = validator.validate(zDeviceType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		zDeviceTypeService.save(zDeviceType);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = zDeviceType.getId().toString();
		URI uri = uriBuilder.path("/rest/zDeviceTypeController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ZDeviceTypeEntity zDeviceType) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ZDeviceTypeEntity>> failures = validator.validate(zDeviceType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		zDeviceTypeService.saveOrUpdate(zDeviceType);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		zDeviceTypeService.deleteEntityById(ZDeviceTypeEntity.class, id);
	}
}
