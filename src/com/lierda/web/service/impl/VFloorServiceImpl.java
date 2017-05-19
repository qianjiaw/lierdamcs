package com.lierda.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lierda.web.service.VFloorServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("vFloorService")
@Transactional
public class VFloorServiceImpl extends CommonServiceImpl implements VFloorServiceI {
	
}