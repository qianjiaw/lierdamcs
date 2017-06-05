package com.lierda.web.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 能耗记录
 * @author zhangdaihao
 * @date 2017-06-05 10:34:39
 * @version V1.0   
 *
 */
@Entity
@Table(name = "z_power_recording", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class PowerRecordingEntity implements java.io.Serializable {
	/**主键id*/
	private java.lang.Integer id;
	/**ddc mac地址*/
	private java.lang.String ddcmac;
	/**设备mac地址*/
	private java.lang.String macid;
	/**实时功率*/
	private java.lang.Double realtimepower;
	/**实时电流*/
	private java.lang.Double realtimecurrent;
	/**总电量*/
	private java.lang.Double allpower;
	/**小时耗电*/
	private java.lang.Double hourpower;
	/**日耗电*/
	private java.lang.Double daypower;
	/**月耗电*/
	private java.lang.Double monthpower;
	/**年耗电*/
	private java.lang.Double yearpower;
	/**日节约电量*/
	private java.lang.Double daysavingpower;
	/**节约总电量*/
	private java.lang.Double allsavingpower;
	/**设备状态*/
	private java.lang.String state;
	/**保存时间*/
	private java.util.Date savingtime;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  主键id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,precision=10,scale=0)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  主键id
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ddc mac地址
	 */
	@Column(name ="DDCMAC",nullable=false,length=20)
	public java.lang.String getDdcmac(){
		return this.ddcmac;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ddc mac地址
	 */
	public void setDdcmac(java.lang.String ddcmac){
		this.ddcmac = ddcmac;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备mac地址
	 */
	@Column(name ="MACID",nullable=false,length=20)
	public java.lang.String getMacid(){
		return this.macid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备mac地址
	 */
	public void setMacid(java.lang.String macid){
		this.macid = macid;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  实时功率
	 */
	@Column(name ="REALTIMEPOWER",nullable=true,precision=15,scale=2)
	public java.lang.Double getRealtimepower(){
		return this.realtimepower;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  实时功率
	 */
	public void setRealtimepower(java.lang.Double realtimepower){
		this.realtimepower = realtimepower;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  实时电流
	 */
	@Column(name ="REALTIMECURRENT",nullable=true,precision=15,scale=2)
	public java.lang.Double getRealtimecurrent(){
		return this.realtimecurrent;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  实时电流
	 */
	public void setRealtimecurrent(java.lang.Double realtimecurrent){
		this.realtimecurrent = realtimecurrent;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  总电量
	 */
	@Column(name ="ALLPOWER",nullable=true,precision=15,scale=2)
	public java.lang.Double getAllpower(){
		return this.allpower;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  总电量
	 */
	public void setAllpower(java.lang.Double allpower){
		this.allpower = allpower;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  小时耗电
	 */
	@Column(name ="HOURPOWER",nullable=true,precision=15,scale=2)
	public java.lang.Double getHourpower(){
		return this.hourpower;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  小时耗电
	 */
	public void setHourpower(java.lang.Double hourpower){
		this.hourpower = hourpower;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  日耗电
	 */
	@Column(name ="DAYPOWER",nullable=true,precision=15,scale=2)
	public java.lang.Double getDaypower(){
		return this.daypower;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  日耗电
	 */
	public void setDaypower(java.lang.Double daypower){
		this.daypower = daypower;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  月耗电
	 */
	@Column(name ="MONTHPOWER",nullable=true,precision=15,scale=2)
	public java.lang.Double getMonthpower(){
		return this.monthpower;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  月耗电
	 */
	public void setMonthpower(java.lang.Double monthpower){
		this.monthpower = monthpower;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  年耗电
	 */
	@Column(name ="YEARPOWER",nullable=true,precision=15,scale=2)
	public java.lang.Double getYearpower(){
		return this.yearpower;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  年耗电
	 */
	public void setYearpower(java.lang.Double yearpower){
		this.yearpower = yearpower;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  日节约电量
	 */
	@Column(name ="DAYSAVINGPOWER",nullable=true,precision=15,scale=2)
	public java.lang.Double getDaysavingpower(){
		return this.daysavingpower;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  日节约电量
	 */
	public void setDaysavingpower(java.lang.Double daysavingpower){
		this.daysavingpower = daysavingpower;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  节约总电量
	 */
	@Column(name ="ALLSAVINGPOWER",nullable=true,precision=15,scale=2)
	public java.lang.Double getAllsavingpower(){
		return this.allsavingpower;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  节约总电量
	 */
	public void setAllsavingpower(java.lang.Double allsavingpower){
		this.allsavingpower = allsavingpower;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备状态
	 */
	@Column(name ="STATE",nullable=true,length=80)
	public java.lang.String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备状态
	 */
	public void setState(java.lang.String state){
		this.state = state;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  保存时间
	 */
	@Column(name ="SAVINGTIME",nullable=false)
	public java.util.Date getSavingtime(){
		return this.savingtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  保存时间
	 */
	public void setSavingtime(java.util.Date savingtime){
		this.savingtime = savingtime;
	}
}
