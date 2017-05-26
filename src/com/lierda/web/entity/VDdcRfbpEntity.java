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
 * @Description: ddc关联视图
 * @author zhangdaihao
 * @date 2017-05-26 09:40:07
 * @version V1.0   
 *
 */
@Entity
@Table(name = "v_ddc_rfbp", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class VDdcRfbpEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**ddcid*/
	private java.lang.String ddcid;
	/**房间id*/
	private java.lang.String roomid;
	/**房间id*/
	private java.lang.String floorid;
	/**建筑物id*/
	private java.lang.String buildid;
	/**园区id*/
	private java.lang.String parkid;
	/**房间名称*/
	private java.lang.String roomname;
	/**楼层名*/
	private java.lang.String floorname;
	/**建筑物名称*/
	private java.lang.String buildingname;
	/**园区名称*/
	private java.lang.String parkname;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ddcid
	 */
	@Column(name ="DDCID",nullable=true,length=20)
	public java.lang.String getDdcid(){
		return this.ddcid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ddcid
	 */
	public void setDdcid(java.lang.String ddcid){
		this.ddcid = ddcid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  房间id
	 */
	@Column(name ="ROOMID",nullable=true,length=36)
	public java.lang.String getRoomid(){
		return this.roomid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  房间id
	 */
	public void setRoomid(java.lang.String roomid){
		this.roomid = roomid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  房间id
	 */
	@Column(name ="FLOORID",nullable=true,length=36)
	public java.lang.String getFloorid(){
		return this.floorid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  房间id
	 */
	public void setFloorid(java.lang.String floorid){
		this.floorid = floorid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  建筑物id
	 */
	@Column(name ="BUILDID",nullable=true,length=36)
	public java.lang.String getBuildid(){
		return this.buildid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  建筑物id
	 */
	public void setBuildid(java.lang.String buildid){
		this.buildid = buildid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  园区id
	 */
	@Column(name ="PARKID",nullable=true,length=36)
	public java.lang.String getParkid(){
		return this.parkid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  园区id
	 */
	public void setParkid(java.lang.String parkid){
		this.parkid = parkid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  房间名称
	 */
	@Column(name ="ROOMNAME",nullable=true,length=20)
	public java.lang.String getRoomname(){
		return this.roomname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  房间名称
	 */
	public void setRoomname(java.lang.String roomname){
		this.roomname = roomname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  楼层名
	 */
	@Column(name ="FLOORNAME",nullable=true,length=50)
	public java.lang.String getFloorname(){
		return this.floorname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  楼层名
	 */
	public void setFloorname(java.lang.String floorname){
		this.floorname = floorname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  建筑物名称
	 */
	@Column(name ="BUILDINGNAME",nullable=true,length=20)
	public java.lang.String getBuildingname(){
		return this.buildingname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  建筑物名称
	 */
	public void setBuildingname(java.lang.String buildingname){
		this.buildingname = buildingname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  园区名称
	 */
	@Column(name ="PARKNAME",nullable=true,length=50)
	public java.lang.String getParkname(){
		return this.parkname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  园区名称
	 */
	public void setParkname(java.lang.String parkname){
		this.parkname = parkname;
	}
}
