<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>建筑物管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="zBuildingController.do?save">
		<input id="id" name="id" type="hidden" value="${zBuildingPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">建筑物名称:</label>
		      <input class="inputxt" id="buildingname" name="buildingname" ignore="ignore"
					   value="${zBuildingPage.buildingname}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">面积:</label>
		      <input class="inputxt" id="area" name="area" ignore="ignore"
					   value="${zBuildingPage.area}" datatype="d">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">位置:</label>
		      <input class="inputxt" id="position" name="position" ignore="ignore"
					   value="${zBuildingPage.position}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">所属园区:</label>
		      <select id="parkid" name="parkid">
				<c:forEach items="${zparkList}" var="item">
					<option class="inputxt" value="${item.id}">${item.parkname}</option>
				</c:forEach>
			  </select>
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>