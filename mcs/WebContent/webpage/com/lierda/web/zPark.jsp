<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>园区管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="zParkController.do?save">
		<input id="id" name="id" type="hidden" value="${zParkPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">园区名称:</label>
		      <input class="inputxt" id="parkname" name="parkname" ignore="ignore"
					   value="${zParkPage.parkname}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">经度:</label>
		      <input class="inputxt" id="lon" name="lon" ignore="ignore"
					   value="${zParkPage.lon}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">纬度:</label>
		      <input class="inputxt" id="lat" name="lat" ignore="ignore"
					   value="${zParkPage.lat}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">园区图:</label>
		      <input class="inputxt" id="image" name="image" ignore="ignore"
					   value="${zParkPage.image}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">地图位置:</label>
		      <input class="inputxt" id="map" name="map" ignore="ignore"
					   value="${zParkPage.map}">
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>