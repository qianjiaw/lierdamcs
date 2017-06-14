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
 * @Description: 计量类型视图
 * @author zhangdaihao
 * @date 2017-06-07 15:01:40
 * @version V1.0   
 *
 */
@Entity
@Table(name = "v_powertype", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class VPowertypeEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**计量统计类型*/
	private java.lang.Integer type;
	/**计量设备id*/
	private java.lang.String devicemac;
	/**设备类型名称*/
	private java.lang.String typename;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  id
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
	 *@param: java.lang.Integer  id
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  计量统计类型
	 */
	@Column(name ="TYPE",nullable=false,precision=10,scale=0)
	public java.lang.Integer getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  计量统计类型
	 */
	public void setType(java.lang.Integer type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计量设备id
	 */
	@Column(name ="DEVICEMAC",nullable=false,length=20)
	public java.lang.String getDevicemac(){
		return this.devicemac;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计量设备id
	 */
	public void setDevicemac(java.lang.String devicemac){
		this.devicemac = devicemac;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备类型名称
	 */
	@Column(name ="TYPENAME",nullable=true,length=30)
	public java.lang.String getTypename(){
		return this.typename;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备类型名称
	 */
	public void setTypename(java.lang.String typename){
		this.typename = typename;
	}
}
