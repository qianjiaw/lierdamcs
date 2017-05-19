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
 * @Description: 楼层视图
 * @author zhangdaihao
 * @date 2017-05-08 17:35:08
 * @version V1.0   
 *
 */
@Entity
@Table(name = "v_floor", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class VFloorEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**楼层布局图*/
	private java.lang.String image;
	/**房间数量*/
	private java.lang.Integer rooms;
	/**楼层名*/
	private java.lang.String floorname;
	/**所属建筑*/
	private java.lang.String buildingid;
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
	 *@return: java.lang.String  楼层布局图
	 */
	@Column(name ="IMAGE",nullable=true,length=50)
	public java.lang.String getImage(){
		return this.image;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  楼层布局图
	 */
	public void setImage(java.lang.String image){
		this.image = image;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  房间数量
	 */
	@Column(name ="ROOMS",nullable=true,precision=10,scale=0)
	public java.lang.Integer getRooms(){
		return this.rooms;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  房间数量
	 */
	public void setRooms(java.lang.Integer rooms){
		this.rooms = rooms;
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
	 *@return: java.lang.String  所属建筑
	 */
	@Column(name ="BUILDINGID",nullable=false,length=36)
	public java.lang.String getBuildingid(){
		return this.buildingid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属建筑
	 */
	public void setBuildingid(java.lang.String buildingid){
		this.buildingid = buildingid;
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
