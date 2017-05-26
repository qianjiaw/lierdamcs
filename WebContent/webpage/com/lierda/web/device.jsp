<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>设备信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="deviceController.do?save">
			<input id="id" name="id" type="hidden" value="${devicePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							设备类型:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="type" name="type" ignore="ignore"
							   value="${devicePage.type}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							设备名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" ignore="ignore"
							   value="${devicePage.name}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							sourceid:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sourceid" name="sourceid" ignore="ignore"
							   value="${devicePage.sourceid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							datetime:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="datetime" name="datetime" ignore="ignore"
							     value="<fmt:formatDate value='${devicePage.datetime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							serialnum:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="serialnum" name="serialnum" ignore="ignore"
							   value="${devicePage.serialnum}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							requesttype:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="requesttype" name="requesttype" ignore="ignore"
							   value="${devicePage.requesttype}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							macid:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="macid" name="macid" ignore="ignore"
							   value="${devicePage.macid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							attributes:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="attributes" name="attributes" ignore="ignore"
							   value="${devicePage.attributes}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							ddcid:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="ddcid" name="ddcid" ignore="ignore"
							   value="${devicePage.ddcid}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							企业主键:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="companyid" name="companyid" ignore="ignore"
							   value="${devicePage.companyid}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否在线:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="online" name="online" ignore="ignore"
							   value="${devicePage.online}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							常用1不常用0:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="commonuse" name="commonuse" ignore="ignore"
							   value="${devicePage.commonuse}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							别名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="alias" name="alias" ignore="ignore"
							   value="${devicePage.alias}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>