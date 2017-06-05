package org.jeecgframework.web.demo.service.impl.test;

import java.util.ArrayList;
import java.util.List;

import org.jeecgframework.web.demo.dao.test.JeecgMinidaoDao;
import org.jeecgframework.web.demo.entity.test.JeecgMinidaoEntity;
import org.jeecgframework.web.demo.service.test.JeecgMinidaoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lierda.web.entity.DdcEntity;
import com.lierda.web.entity.DeviceEntity;
import com.lierda.web.entity.PowerRecordingEntity;
import com.lierda.web.entity.ZBuildingEntity;
import com.lierda.web.entity.ZFloorEntity;
import com.lierda.web.entity.ZRoomEntity;
import com.lierda.web.resultEntity.SqlResult;

/**
 * Minidao例子
 * @author fancq
 *
 */
@Service("jeecgMinidaoService")
@Transactional
public class JeecgMinidaoServiceImpl implements JeecgMinidaoServiceI {
	@Autowired
	private JeecgMinidaoDao jeecgMinidaoDao;
	
	public List<JeecgMinidaoEntity> listAll(JeecgMinidaoEntity jeecgMinidao, int page, int rows) {
		List<JeecgMinidaoEntity> entities = jeecgMinidaoDao.getAllEntities2(jeecgMinidao, page, rows);
		return entities;
	}
	
	/**
	public JeecgMinidaoEntity getEntity(Class clazz, String id) {
		JeecgMinidaoEntity jeecgMinidao = null; //(JeecgMinidaoEntity)jeecgMinidaoDao.getByIdHiber(clazz, id);
		return jeecgMinidao;
	}
	
	public void insert(JeecgMinidaoEntity jeecgMinidao) {
		//jeecgMinidaoDao.saveByHiber(jeecgMinidao);
	}
	
	public void update(JeecgMinidaoEntity jeecgMinidao) {
		//jeecgMinidaoDao.updateByHiber(jeecgMinidao);
	}
	
	public void delete(JeecgMinidaoEntity jeecgMinidao) {
		//jeecgMinidaoDao.deleteByHiber(jeecgMinidao);
	}
	
	public void deleteAllEntitie(List<JeecgMinidaoEntity> entities) {
		for (JeecgMinidaoEntity entity : entities) {
			//jeecgMinidaoDao.deleteByHiber(entity);
		}
	}
	*/
	public Integer getCount() {
		return jeecgMinidaoDao.getCount();
	}
	
	public Integer getSumSalary() {
		return jeecgMinidaoDao.getSumSalary();
	}

	public String selectName(String id) {
		// TODO Auto-generated method stub
		return jeecgMinidaoDao.selectName(id);
	}

	public String selectFloorName(String id) {
		// TODO Auto-generated method stub
		return jeecgMinidaoDao.selectFloorName(id);
	}

	public String selectRoomName(String id) {
		// TODO Auto-generated method stub
		return jeecgMinidaoDao.selectRoomName(id);
	}

	public String selectParkName(String id) {
		// TODO Auto-generated method stub
		return jeecgMinidaoDao.selectParkName(id);
	}

	@Override
	public List<ZFloorEntity> selectFloorByBuild(String buildId) {
		// TODO Auto-generated method stub
		return jeecgMinidaoDao.selectFloorByBuild(buildId);
	}

	@Override
	public List<ZBuildingEntity> getAllBuildingIdAndName() {
		// TODO Auto-generated method stub
		return jeecgMinidaoDao.getAllBuildingIdAndName();
	}

	@Override
	public List<ZRoomEntity> selectRoomByFloor(String floorid) {
		// TODO Auto-generated method stub
		return jeecgMinidaoDao.selectRoomByFloor(floorid);
	}

	@Override
	public List<ZFloorEntity> selectFloorById(String floorid) {
		// TODO Auto-generated method stub
		return jeecgMinidaoDao.selectFloorById(floorid);
	}

	@Override
	public List<ZFloorEntity> getBuildingByFloorId(String floorid) {
		// TODO Auto-generated method stub
		return jeecgMinidaoDao.getBuildingByFloorId(floorid);
	}

	@Override
	public List<ZBuildingEntity> getBuildingBybuildingid(String buildingid) {
		// TODO Auto-generated method stub
		return jeecgMinidaoDao.getBuildingBybuildingid(buildingid);
	}

	@Override
	public List<ZRoomEntity> getFloorByRoomId(String roomid) {
		// TODO Auto-generated method stub
		return jeecgMinidaoDao.getFloorByRoomId(roomid);
	}

	public List<ZBuildingEntity> getAllBuildingsByParkId(String parkid) {
		// TODO Auto-generated method stub
		return jeecgMinidaoDao.getAllBuildingsByParkId(parkid);
	}

	@Override
	public List<ZFloorEntity> getAllFloorsByBuildId(String buildid) {
		// TODO Auto-generated method stub
		return jeecgMinidaoDao.getAllFloorsByBuildId(buildid);
	}

	@Override
	public List<ZRoomEntity> getRoomByRoomId(String roomid) {
		// TODO Auto-generated method stub
		return jeecgMinidaoDao.getRoomByRoomId(roomid);
	}

	@Override
	public List<DdcEntity> getDdcByRoomId(String roomid) {
		// TODO Auto-generated method stub
		return jeecgMinidaoDao.getDdcByRoomId(roomid);
	}

	@Override
	public List<DeviceEntity> getDeviceByRoomid(String roomid) {
		// TODO Auto-generated method stub
		return jeecgMinidaoDao.getDeviceByRoomid(roomid);
	}

	@Override
	public List<SqlResult> getAllDeviceByRAT(String sql) {
		// TODO Auto-generated method stub
		return jeecgMinidaoDao.getAllDeviceByRAT(sql);
	}

	@Override
	public List<PowerRecordingEntity> getPowerBybid(String sql) {
		// TODO Auto-generated method stub
		return jeecgMinidaoDao.getPowerBybid(sql);
	}
}