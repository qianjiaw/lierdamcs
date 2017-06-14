package com.lierda.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lierda.web.service.ZPowerTypeServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("zPowerTypeService")
@Transactional
public class ZPowerTypeServiceImpl extends CommonServiceImpl implements ZPowerTypeServiceI {
	
}