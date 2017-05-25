package com.lierda.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lierda.web.service.DdcvServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("ddcvService")
@Transactional
public class DdcvServiceImpl extends CommonServiceImpl implements DdcvServiceI {
	
}