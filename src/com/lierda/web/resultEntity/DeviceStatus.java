package com.lierda.web.resultEntity;
/**
 * 房间设备状态
 * @author  qianjiawei
 */
import com.lierda.web.entity.AirConditioner;
import com.lierda.web.entity.Bgm;
import com.lierda.web.entity.Blind;
import com.lierda.web.entity.FloorHeating;
import com.lierda.web.entity.Light;
import com.lierda.web.entity.Lock;
import com.lierda.web.entity.PowerStrip;
import com.lierda.web.entity.SenseHuman;

public class DeviceStatus {
	/**人感*/
	private SenseHuman senseHuman;
	/**门锁*/
	private Lock lock;
	/**照明*/
	private Light light;
	/**窗帘*/
	private Blind blind;
	/**插座*/
	private PowerStrip powerStrip;
	/**地暖*/
	private FloorHeating floorHeating;
	/**音乐*/
	private Bgm bgm;
	/**空调*/
	private AirConditioner airConditioner;
	public SenseHuman getSenseHuman() {
		return senseHuman;
	}
	public void setSenseHuman(SenseHuman senseHuman) {
		this.senseHuman = senseHuman;
	}
	public Lock getLock() {
		return lock;
	}
	public void setLock(Lock lock) {
		this.lock = lock;
	}
	public Light getLight() {
		return light;
	}
	public void setLight(Light light) {
		this.light = light;
	}
	public Blind getBlind() {
		return blind;
	}
	public void setBlind(Blind blind) {
		this.blind = blind;
	}
	public PowerStrip getPowerStrip() {
		return powerStrip;
	}
	public void setPowerStrip(PowerStrip powerStrip) {
		this.powerStrip = powerStrip;
	}
	public FloorHeating getFloorHeating() {
		return floorHeating;
	}
	public void setFloorHeating(FloorHeating floorHeating) {
		this.floorHeating = floorHeating;
	}
	public Bgm getBgm() {
		return bgm;
	}
	public void setBgm(Bgm bgm) {
		this.bgm = bgm;
	}
	public AirConditioner getAirConditioner() {
		return airConditioner;
	}
	public void setAirConditioner(AirConditioner airConditioner) {
		this.airConditioner = airConditioner;
	}
	public DeviceStatus(SenseHuman senseHuman, Lock lock, Light light,
			Blind blind, PowerStrip powerStrip, FloorHeating floorHeating,
			Bgm bgm, AirConditioner airConditioner) {
		super();
		this.senseHuman = senseHuman;
		this.lock = lock;
		this.light = light;
		this.blind = blind;
		this.powerStrip = powerStrip;
		this.floorHeating = floorHeating;
		this.bgm = bgm;
		this.airConditioner = airConditioner;
	}
	public DeviceStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
