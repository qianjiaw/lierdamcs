package org.jeecgframework.web.demo.dao.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.ResultType;
import org.jeecgframework.minidao.annotation.Sql;
import org.jeecgframework.web.demo.entity.test.JeecgMinidaoEntity;

import com.lierda.web.entity.ZBuildingEntity;
import com.lierda.web.entity.ZFloorEntity;
import com.lierda.web.entity.ZRoomEntity;

/**
 * Minidao例子
 * @author fancq
 * 
 */
//@Repository("jeecgMinidaoDao")
@MiniDao
public interface JeecgMinidaoDao {
	@Arguments({"jeecgMinidao", "page", "rows"})
	public List<Map> getAllEntities(JeecgMinidaoEntity jeecgMinidao, int page, int rows);

	@Arguments({"jeecgMinidao", "page", "rows"})
	@ResultType(JeecgMinidaoEntity.class)
	public List<JeecgMinidaoEntity> getAllEntities2(JeecgMinidaoEntity jeecgMinidao, int page, int rows);

	//@Arguments("id")
	//JeecgMinidaoEntity getJeecgMinidao(String id);

	@Sql("SELECT count(*) FROM jeecg_minidao")
	Integer getCount();

	@Sql("SELECT SUM(salary) FROM jeecg_minidao")
	Integer getSumSalary();
	
	@Arguments("id")
	String selectName(String id);
	
	@Arguments("id")
	String selectFloorName(String id);
	
	@Arguments("id")
	String selectRoomName(String id);
	
	@Arguments("id")
	String selectParkName(String parkid);
	
	@Arguments("buildId")
	List<ZFloorEntity> selectFloorByBuild(String buildId);
	
	@Sql("select id,buildingname from z_building ")
	List<ZBuildingEntity> getAllBuildingIdAndName();
	
	@Arguments("floorid")
	List<ZRoomEntity> selectRoomByFloor(String floorid);
	
	@Arguments("floorid")
	List<ZFloorEntity> selectFloorById(String floorid);
	
	@Arguments("floorid")
	List<ZFloorEntity> getBuidingByFloorId(String floorid);
	
	/*@Arguments("jeecgMinidao")
	int update(JeecgMinidaoEntity jeecgMinidao);

	@Arguments("jeecgMinidao")
	void insert(JeecgMinidaoEntity jeecgMinidao);

	@Arguments("jeecgMinidao")
	void delete(JeecgMinidaoEntity jeecgMinidao);*/
}
