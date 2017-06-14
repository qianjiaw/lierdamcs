package com.lierda.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lierda.web.service.VPowertypeServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("vPowertypeService")
@Transactional
public class VPowertypeServiceImpl extends CommonServiceImpl implements VPowertypeServiceI {
	
}