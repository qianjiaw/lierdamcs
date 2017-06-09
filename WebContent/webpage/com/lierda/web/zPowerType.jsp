<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>计量类型</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="zPowerTypeController.do?save">
		<input id="id" name="id" type="hidden" value="${zPowerTypePage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">计量统计类型:</label>
			  <select id="type" name="type">
			  		<option selected="selected" disabled="disabled" style="display: none" value="${zPowerTypePage.type}">${typename}</option>
					<option class="inputxt" value="1">调光灯</option>
					<option class="inputxt" value="15">六路开关</option>
					<option class="inputxt" value="26">计量插座</option>
			  </select>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">计量设备id:</label>
		      <input class="inputxt" id="devicemac" name="devicemac" 
					   value="${zPowerTypePage.devicemac}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>