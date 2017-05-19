<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>用户信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="kUserController.do?save">
			<input id="id" name="id" type="hidden" value="${kUserPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							账户:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="account" name="account" ignore="ignore"
							   value="${kUserPage.account}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="username" name="username" ignore="ignore"
							   value="${kUserPage.username}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							密码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="password" name="password" ignore="ignore"
							   value="${kUserPage.password}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							手机号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="phone" name="phone" ignore="ignore"
							   value="${kUserPage.phone}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							idcard:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="idcard" name="idcard" ignore="ignore"
							   value="${kUserPage.idcard}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否通过认证0未通过1通过:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="ispass" name="ispass" ignore="ignore"
							   value="${kUserPage.ispass}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							创建修改时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="creatdate" name="creatdate" ignore="ignore"
							     value="<fmt:formatDate value='${kUserPage.creatdate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							首次店铺id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="shopid" name="shopid" ignore="ignore"
							   value="${kUserPage.shopid}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>