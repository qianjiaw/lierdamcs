package com.lierda.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lierda.web.service.VBuildingServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("vBuildingService")
@Transactional
public class VBuildingServiceImpl extends CommonServiceImpl implements VBuildingServiceI {
	
}