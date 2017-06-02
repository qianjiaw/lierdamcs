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
 * @Description: 设备类型
 * @author zhangdaihao
 * @date 2017-06-02 15:54:09
 * @version V1.0   
 *
 */
@Entity
@Table(name = "z_devicetype", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ZDeviceTypeEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.Integer id;
	/**设备类型名称*/
	private java.lang.String typename;
	/**ddc设备类型*/
	private java.lang.String typ;
	/**触发设备(0: 触发和执行都可;1:只能触发;2:只能执行)*/
	private java.lang.Integer trigger;
	/**图标*/
	private java.lang.String icon;
	/**链接*/
	private java.lang.String link;
	/**在线颜色*/
	private java.lang.String bgc;
	/**语音支持语句*/
	private java.lang.String words;
	
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ddc设备类型
	 */
	@Column(name ="TYP",nullable=true,length=10)
	public java.lang.String getTyp(){
		return this.typ;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ddc设备类型
	 */
	public void setTyp(java.lang.String typ){
		this.typ = typ;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  触发设备(0: 触发和执行都可;1:只能触发;2:只能执行)
	 */
	@Column(name ="TRIGGER",nullable=true,precision=10,scale=0)
	public java.lang.Integer getTrigger(){
		return this.trigger;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  触发设备(0: 触发和执行都可;1:只能触发;2:只能执行)
	 */
	public void setTrigger(java.lang.Integer trigger){
		this.trigger = trigger;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图标
	 */
	@Column(name ="ICON",nullable=true,length=30)
	public java.lang.String getIcon(){
		return this.icon;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图标
	 */
	public void setIcon(java.lang.String icon){
		this.icon = icon;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  链接
	 */
	@Column(name ="LINK",nullable=true,length=30)
	public java.lang.String getLink(){
		return this.link;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  链接
	 */
	public void setLink(java.lang.String link){
		this.link = link;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  在线颜色
	 */
	@Column(name ="BGC",nullable=true,length=30)
	public java.lang.String getBgc(){
		return this.bgc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  在线颜色
	 */
	public void setBgc(java.lang.String bgc){
		this.bgc = bgc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  语音支持语句
	 */
	@Column(name ="WORDS",nullable=true,length=2000)
	public java.lang.String getWords(){
		return this.words;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  语音支持语句
	 */
	public void setWords(java.lang.String words){
		this.words = words;
	}
}
