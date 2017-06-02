<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zDeviceTypeList" title="设备类型" actionUrl="zDeviceTypeController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="设备类型名称" field="typename"   width="120"></t:dgCol>
   <t:dgCol title="ddc设备类型" field="typ"   width="120"></t:dgCol>
   <t:dgCol title="触发设备(0: 触发和执行都可;1:只能触发;2:只能执行)" field="trigger"   width="120"></t:dgCol>
   <t:dgCol title="图标" field="icon"   width="120"></t:dgCol>
   <t:dgCol title="链接" field="link"   width="120"></t:dgCol>
   <t:dgCol title="在线颜色" field="bgc"   width="120"></t:dgCol>
   <t:dgCol title="语音支持语句" field="words"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zDeviceTypeController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="zDeviceTypeController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zDeviceTypeController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zDeviceTypeController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>