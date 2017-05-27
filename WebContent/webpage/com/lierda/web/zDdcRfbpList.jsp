<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zDdcRfbpList" title="ddc关联表" actionUrl="zDdcRfbpController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="ddcmac" field="ddcmac"   width="120"></t:dgCol>
   <t:dgCol title="房间名" field="roomname"   width="120"></t:dgCol>
   <t:dgCol title="楼层名" field="floorname"   width="120"></t:dgCol>
   <t:dgCol title="建筑物名" field="buildingname"   width="120"></t:dgCol>
   <t:dgCol title="园区名" field="parkname"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zDdcRfbpController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="zDdcRfbpController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zDdcRfbpController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zDdcRfbpController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>