package com.lierda.web.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 园区管理
 * @author zhangdaihao
 * @date 2017-05-05 11:58:43
 * @version V1.0   
 *
 */
@Entity
@Table(name = "z_park", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ZParkEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**园区名称*/
	private java.lang.String parkname;
	/**经度*/
	private java.lang.String lon;
	/**纬度*/
	private java.lang.String lat;
	/**园区图*/
	private java.lang.String image;
	/**地图位置*/
	private java.lang.String map;
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=255)
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  经度
	 */
	@Column(name ="LON",nullable=true,length=20)
	public java.lang.String getLon(){
		return this.lon;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  经度
	 */
	public void setLon(java.lang.String lon){
		this.lon = lon;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  纬度
	 */
	@Column(name ="LAT",nullable=true,length=20)
	public java.lang.String getLat(){
		return this.lat;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  纬度
	 */
	public void setLat(java.lang.String lat){
		this.lat = lat;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  园区图
	 */
	@Column(name ="IMAGE",nullable=true,length=255)
	public java.lang.String getImage(){
		return this.image;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  园区图
	 */
	public void setImage(java.lang.String image){
		this.image = image;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地图位置
	 */
	@Column(name ="MAP",nullable=true,length=50)
	public java.lang.String getMap(){
		return this.map;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地图位置
	 */
	public void setMap(java.lang.String map){
		this.map = map;
	}

	
}
