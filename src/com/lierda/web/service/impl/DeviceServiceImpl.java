package com.lierda.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lierda.web.service.DeviceServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("deviceService")
@Transactional
public class DeviceServiceImpl extends CommonServiceImpl implements DeviceServiceI {
	
}