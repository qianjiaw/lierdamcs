<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>房间管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="zRoomController.do?save">
		<input id="id" name="id" type="hidden" value="${zRoomPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">房间名称:</label>
		      <input class="inputxt" id="roomname" name="roomname" ignore="ignore"
					   value="${zRoomPage.roomname}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">所属楼层:</label>
			  <select id="floorid" name="floorid">
				<c:forEach items="${zfloorList}" var="item">
					<option id="${item.id}" class="inputxt">${item.parkname}-${item.buildingname}-${item.floorname}</option>
				</c:forEach>
			  </select>
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
  
  <script>
  	$("#${zFloorPage.id}").attr("selected",true);
  </script>
 </body>