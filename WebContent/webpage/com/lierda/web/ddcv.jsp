<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>ddc视图展示</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ddcvController.do?save">
			<input id="id" name="id" type="hidden" value="${ddcvPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							ddcmac地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="ddcmac" name="ddcmac" ignore="ignore"
							   value="${ddcvPage.ddcmac}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							房间名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="roomname" name="roomname" ignore="ignore"
							   value="${ddcvPage.roomname}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							楼层名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="floorname" name="floorname" ignore="ignore"
							   value="${ddcvPage.floorname}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							建筑物名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="buildingname" name="buildingname" ignore="ignore"
							   value="${ddcvPage.buildingname}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							园区名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="parkname" name="parkname" ignore="ignore"
							   value="${ddcvPage.parkname}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>