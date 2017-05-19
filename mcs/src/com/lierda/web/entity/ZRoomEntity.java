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
 * @Description: 房间管理
 * @author zhangdaihao
 * @date 2017-05-05 11:59:35
 * @version V1.0   
 *
 */
@Entity
@Table(name = "z_room", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ZRoomEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**房间名称*/
	private java.lang.String roomname;
	/**所属楼层*/
	private java.lang.String floorid;
	
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
	 *@return: java.lang.String  所属楼层
	 */
	@Column(name ="FLOORID",nullable=false,length=36)
	public java.lang.String getFloorid(){
		return this.floorid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属楼层
	 */
	public void setFloorid(java.lang.String floorid){
		this.floorid = floorid;
	}
}
