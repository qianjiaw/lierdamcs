package com.lierda.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lierda.web.service.ZRoomServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("zRoomService")
@Transactional
public class ZRoomServiceImpl extends CommonServiceImpl implements ZRoomServiceI {
	
}