<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="deviceList" title="设备信息" actionUrl="deviceController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="设备类型" field="type"   width="120"></t:dgCol>
   <t:dgCol title="设备名称" field="name"   width="120"></t:dgCol>
   <t:dgCol title="sourceid" field="sourceid"   width="120"></t:dgCol>
   <t:dgCol title="datetime" field="datetime" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>
   <t:dgCol title="serialnum" field="serialnum"   width="120"></t:dgCol>
   <t:dgCol title="requesttype" field="requesttype"   width="120"></t:dgCol>
   <t:dgCol title="macid" field="macid"   width="120"></t:dgCol>
   <t:dgCol title="attributes" field="attributes"   width="120"></t:dgCol>
   <t:dgCol title="ddcid" field="ddcid"   width="120"></t:dgCol>
   <t:dgCol title="企业主键" field="companyid"   width="120"></t:dgCol>
   <t:dgCol title="是否在线" field="online"   width="120"></t:dgCol>
   <t:dgCol title="常用1不常用0" field="commonuse"   width="120"></t:dgCol>
   <t:dgCol title="别名" field="alias"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="deviceController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="deviceController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="deviceController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="deviceController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>