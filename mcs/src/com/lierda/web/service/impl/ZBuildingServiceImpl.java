package com.lierda.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lierda.web.service.ZBuildingServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("zBuildingService")
@Transactional
public class ZBuildingServiceImpl extends CommonServiceImpl implements ZBuildingServiceI {
	
}