package com.lierda.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lierda.web.service.DdcServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("ddcService")
@Transactional
public class DdcServiceImpl extends CommonServiceImpl implements DdcServiceI {
	
}