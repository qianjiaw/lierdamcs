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
 * @Description: ddc管理
 * @author zhangdaihao
 * @date 2017-05-27 15:35:49
 * @version V1.0   
 *
 */
@Entity
@Table(name = "z_ddc", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class DdcEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.Integer id;
	/**ddcmac*/
	private java.lang.String ddcmac;
	/**isssl*/
	private java.lang.Integer isssl;
	/**serverip*/
	private java.lang.String serverip;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  主键
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
	 *@param: java.lang.Integer  主键
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ddcmac
	 */
	@Column(name ="DDCMAC",nullable=false,length=20)
	public java.lang.String getDdcmac(){
		return this.ddcmac;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ddcmac
	 */
	public void setDdcmac(java.lang.String ddcmac){
		this.ddcmac = ddcmac;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  isssl
	 */
	@Column(name ="ISSSL",nullable=false,precision=10,scale=0)
	public java.lang.Integer getIsssl(){
		return this.isssl;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  isssl
	 */
	public void setIsssl(java.lang.Integer isssl){
		this.isssl = isssl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  serverip
	 */
	@Column(name ="SERVERIP",nullable=true,length=50)
	public java.lang.String getServerip(){
		return this.serverip;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  serverip
	 */
	public void setServerip(java.lang.String serverip){
		this.serverip = serverip;
	}
}
