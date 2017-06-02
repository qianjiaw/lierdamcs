package com.lierda.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lierda.web.service.ZDeviceTypeServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("zDeviceTypeService")
@Transactional
public class ZDeviceTypeServiceImpl extends CommonServiceImpl implements ZDeviceTypeServiceI {
	
}