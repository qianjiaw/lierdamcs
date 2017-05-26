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
 * @Description: 设备信息
 * @author zhangdaihao
 * @date 2017-05-26 10:02:00
 * @version V1.0   
 *
 */
@Entity
@Table(name = "z_device", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class DeviceEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.Integer id;
	/**设备类型*/
	private java.lang.Integer type;
	/**设备名称*/
	private java.lang.String name;
	/**sourceid*/
	private java.lang.String sourceid;
	/**datetime*/
	private java.util.Date datetime;
	/**serialnum*/
	private java.lang.Integer serialnum;
	/**requesttype*/
	private java.lang.String requesttype;
	/**macid*/
	private java.lang.String macid;
	/**attributes*/
	private java.lang.String attributes;
	/**ddcid*/
	private java.lang.Integer ddcid;
	/**企业主键*/
	private java.lang.Integer companyid;
	/**是否在线*/
	private java.lang.Integer online;
	/**常用1不常用0*/
	private java.lang.Integer commonuse;
	/**别名*/
	private java.lang.String alias;
	
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  设备类型
	 */
	@Column(name ="TYPE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  设备类型
	 */
	public void setType(java.lang.Integer type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备名称
	 */
	@Column(name ="NAME",nullable=true,length=30)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  sourceid
	 */
	@Column(name ="SOURCEID",nullable=true,length=20)
	public java.lang.String getSourceid(){
		return this.sourceid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  sourceid
	 */
	public void setSourceid(java.lang.String sourceid){
		this.sourceid = sourceid;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  datetime
	 */
	@Column(name ="DATETIME",nullable=true)
	public java.util.Date getDatetime(){
		return this.datetime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  datetime
	 */
	public void setDatetime(java.util.Date datetime){
		this.datetime = datetime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  serialnum
	 */
	@Column(name ="SERIALNUM",nullable=true,precision=10,scale=0)
	public java.lang.Integer getSerialnum(){
		return this.serialnum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  serialnum
	 */
	public void setSerialnum(java.lang.Integer serialnum){
		this.serialnum = serialnum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  requesttype
	 */
	@Column(name ="REQUESTTYPE",nullable=true,length=10)
	public java.lang.String getRequesttype(){
		return this.requesttype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  requesttype
	 */
	public void setRequesttype(java.lang.String requesttype){
		this.requesttype = requesttype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  macid
	 */
	@Column(name ="MACID",nullable=true,length=20)
	public java.lang.String getMacid(){
		return this.macid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  macid
	 */
	public void setMacid(java.lang.String macid){
		this.macid = macid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  attributes
	 */
	@Column(name ="ATTRIBUTES",nullable=true,length=2000)
	public java.lang.String getAttributes(){
		return this.attributes;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  attributes
	 */
	public void setAttributes(java.lang.String attributes){
		this.attributes = attributes;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  ddcid
	 */
	@Column(name ="DDCID",nullable=true,precision=10,scale=0)
	public java.lang.Integer getDdcid(){
		return this.ddcid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  ddcid
	 */
	public void setDdcid(java.lang.Integer ddcid){
		this.ddcid = ddcid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  企业主键
	 */
	@Column(name ="COMPANYID",nullable=true,precision=10,scale=0)
	public java.lang.Integer getCompanyid(){
		return this.companyid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  企业主键
	 */
	public void setCompanyid(java.lang.Integer companyid){
		this.companyid = companyid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否在线
	 */
	@Column(name ="ONLINE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getOnline(){
		return this.online;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否在线
	 */
	public void setOnline(java.lang.Integer online){
		this.online = online;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  常用1不常用0
	 */
	@Column(name ="COMMONUSE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getCommonuse(){
		return this.commonuse;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  常用1不常用0
	 */
	public void setCommonuse(java.lang.Integer commonuse){
		this.commonuse = commonuse;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  别名
	 */
	@Column(name ="ALIAS",nullable=true,length=20)
	public java.lang.String getAlias(){
		return this.alias;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  别名
	 */
	public void setAlias(java.lang.String alias){
		this.alias = alias;
	}
}
