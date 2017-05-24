<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>建筑物</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ddcController.do?save">
			<input id="id" name="id" type="hidden" value="${ddcPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							ddcmac地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="ddcmac" name="ddcmac" ignore="ignore"
							   value="${ddcPage.ddcmac}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							房间id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="roomid" name="roomid" ignore="ignore"
							   value="${ddcPage.roomid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							房间id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="floorid" name="floorid" ignore="ignore"
							   value="${ddcPage.floorid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							建筑物id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="buildid" name="buildid" ignore="ignore"
							   value="${ddcPage.buildid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							园区id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="parkid" name="parkid" ignore="ignore"
							   value="${ddcPage.parkid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>