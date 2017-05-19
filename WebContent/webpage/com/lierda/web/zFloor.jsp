<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>楼层管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="zFloorController.do?save">
		<input id="id" name="id" type="hidden" value="${zFloorPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">楼层布局图:</label>
		      <input class="inputxt" id="image" name="image" ignore="ignore"
					   value="${zFloorPage.image}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">房间数量:</label>
		      <input class="inputxt" id="rooms" name="rooms" ignore="ignore"
					   value="${zFloorPage.rooms}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">楼层名:</label>
		      <input class="inputxt" id="floorname" name="floorname" ignore="ignore"
					   value="${zFloorPage.floorname}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">所属建筑:</label>
		      <select id="buildingid" name="buildingid">
				<c:forEach items="${zbuildingList}" var="item">
					<option class="inputxt" value="${item.id}">${item.buildingname}</option>
				</c:forEach>
			  </select>
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>