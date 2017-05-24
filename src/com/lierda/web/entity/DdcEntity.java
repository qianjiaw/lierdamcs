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
 * @Description: 建筑物
 * @author zhangdaihao
 * @date 2017-05-23 10:04:21
 * @version V1.0   
 *
 */
@Entity
@Table(name = "z_ddc", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class DdcEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**ddcmac地址*/
	private java.lang.String ddcmac;
	/**房间id*/
	private java.lang.String roomid;
	/**房间id*/
	private java.lang.String floorid;
	/**建筑物id*/
	private java.lang.String buildid;
	/**园区id*/
	private java.lang.String parkid;
	
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
	 *@return: java.lang.String  ddcmac地址
	 */
	@Column(name ="DDCMAC",nullable=true,length=20)
	public java.lang.String getDdcmac(){
		return this.ddcmac;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ddcmac地址
	 */
	public void setDdcmac(java.lang.String ddcmac){
		this.ddcmac = ddcmac;
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
}
