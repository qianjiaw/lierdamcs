package com.lierda.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lierda.web.service.ZFloorServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("zFloorService")
@Transactional
public class ZFloorServiceImpl extends CommonServiceImpl implements ZFloorServiceI {
	
}