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
import org.jeecgframework.web.demo.service.test.JeecgMinidaoServiceI;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.lierda.web.entity.DdcEntity;
import com.lierda.web.service.DdcServiceI;

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

import java.io.IOException;
import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 建筑物
 * @author zhangdaihao
 * @date 2017-05-23 10:04:21
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/ddcController")
public class DdcController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DdcController.class);

	@Autowired
	private DdcServiceI ddcService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private JeecgMinidaoServiceI jeecgMinidaoService;

		


	/**
	 * 建筑物列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/lierda/web/ddcList");
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
	public void datagrid(DdcEntity ddc,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DdcEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ddc, request.getParameterMap());
		this.ddcService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "getName")
	@ResponseBody
	public AjaxJson getName(DdcEntity ddc,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		AjaxJson j = new AjaxJson();
		String name="";
		String roomid=request.getParameter("roomid");
		String  floorid=request.getParameter("floorid");
		String buildid=request.getParameter("buildid");
		String parkid=request.getParameter("parkid");
		if(roomid!=null){
			name=jeecgMinidaoService.selectRoomName(roomid);
		}else if (floorid!=null) {
			name=jeecgMinidaoService.selectFloorName(floorid);
		}else if (buildid!=null) {
			name=jeecgMinidaoService.selectName(buildid);
		}else if (parkid!=null) {
			name=jeecgMinidaoService.selectParkName(parkid);
		}
		j.setObj(name);
		return j;
	}

	/**
	 * 删除建筑物
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(DdcEntity ddc, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		ddc = systemService.getEntity(DdcEntity.class, ddc.getId());
		message = "建筑物删除成功";
		ddcService.delete(ddc);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加建筑物
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(DdcEntity ddc, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(ddc.getId())) {
			message = "建筑物更新成功";
			DdcEntity t = ddcService.get(DdcEntity.class, ddc.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(ddc, t);
				ddcService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "建筑物更新失败";
			}
		} else {
			message = "建筑物添加成功";
			ddcService.save(ddc);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 建筑物列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(DdcEntity ddc, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ddc.getId())) {
			ddc = ddcService.getEntity(DdcEntity.class, ddc.getId());
			req.setAttribute("ddcPage", ddc);
		}
		return new ModelAndView("com/lierda/web/ddc");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<DdcEntity> list() {
		List<DdcEntity> listDdcs=ddcService.getList(DdcEntity.class);
		return listDdcs;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		DdcEntity task = ddcService.get(DdcEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody DdcEntity ddc, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DdcEntity>> failures = validator.validate(ddc);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		ddcService.save(ddc);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = ddc.getId();
		URI uri = uriBuilder.path("/rest/ddcController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody DdcEntity ddc) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<DdcEntity>> failures = validator.validate(ddc);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		ddcService.saveOrUpdate(ddc);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		ddcService.deleteEntityById(DdcEntity.class, id);
	}
}
