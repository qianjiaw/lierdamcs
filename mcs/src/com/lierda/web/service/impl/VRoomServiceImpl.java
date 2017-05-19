package com.lierda.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lierda.web.service.VRoomServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("vRoomService")
@Transactional
public class VRoomServiceImpl extends CommonServiceImpl implements VRoomServiceI {
	
}