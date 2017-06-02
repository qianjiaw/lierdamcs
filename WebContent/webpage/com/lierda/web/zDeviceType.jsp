<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>设备类型</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zDeviceTypeController.do?save">
			<input id="id" name="id" type="hidden" value="${zDeviceTypePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							设备类型名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="typename" name="typename" ignore="ignore"
							   value="${zDeviceTypePage.typename}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							ddc设备类型:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="typ" name="typ" ignore="ignore"
							   value="${zDeviceTypePage.typ}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							触发设备(0: 触发和执行都可;1:只能触发;2:只能执行):
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="trigger" name="trigger" ignore="ignore"
							   value="${zDeviceTypePage.trigger}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							图标:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="icon" name="icon" ignore="ignore"
							   value="${zDeviceTypePage.icon}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							链接:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="link" name="link" ignore="ignore"
							   value="${zDeviceTypePage.link}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							在线颜色:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="bgc" name="bgc" ignore="ignore"
							   value="${zDeviceTypePage.bgc}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							语音支持语句:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="words" name="words" ignore="ignore"
							   value="${zDeviceTypePage.words}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>