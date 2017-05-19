package com.lierda.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lierda.web.service.ZParkServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("zParkService")
@Transactional
public class ZParkServiceImpl extends CommonServiceImpl implements ZParkServiceI {
	
}