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
 * @Description: ddc视图展示
 * @author zhangdaihao
 * @date 2017-05-25 10:14:14
 * @version V1.0   
 *
 */
@Entity
@Table(name = "v_ddcv", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class DdcvEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**ddcmac地址*/
	private java.lang.String ddcmac;
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
