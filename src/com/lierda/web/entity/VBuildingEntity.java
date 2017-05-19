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
 * @Description: 建筑视图
 * @author zhangdaihao
 * @date 2017-05-08 16:35:58
 * @version V1.0   
 *
 */
@Entity
@Table(name = "v_building", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class VBuildingEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**建筑物名称*/
	private java.lang.String buildingname;
	/**面积*/
	private java.lang.Double area;
	/**位置*/
	private java.lang.Integer position;
	/**所属园区*/
	private java.lang.String parkid;
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
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  面积
	 */
	@Column(name ="AREA",nullable=true,precision=22)
	public java.lang.Double getArea(){
		return this.area;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  面积
	 */
	public void setArea(java.lang.Double area){
		this.area = area;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  位置
	 */
	@Column(name ="POSITION",nullable=true,precision=10,scale=0)
	public java.lang.Integer getPosition(){
		return this.position;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  位置
	 */
	public void setPosition(java.lang.Integer position){
		this.position = position;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属园区
	 */
	@Column(name ="PARKID",nullable=false,length=255)
	public java.lang.String getParkid(){
		return this.parkid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属园区
	 */
	public void setParkid(java.lang.String parkid){
		this.parkid = parkid;
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
