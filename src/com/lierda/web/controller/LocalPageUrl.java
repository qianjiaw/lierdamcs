package com.lierda.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Title: Controller
 * @Description: 页面跳转
 * @author xxd
 * @date 2017-05-17
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/localPageUrl")
public class LocalPageUrl {
	/**
	 * 房态管理界面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "roomstate")
	public ModelAndView roomstate(String floor,HttpServletRequest request) {
		request.setAttribute("floor", floor);
		return new ModelAndView("com/lierda/main/FloorHome");
	}
}
