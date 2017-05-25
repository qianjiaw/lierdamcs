<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="ddcList" title="建筑物"  autoLoadData="true" actionUrl="ddcController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="ddcmac地址" field="ddcmac"   width="120"></t:dgCol>
   <t:dgCol title="房间id" field="roomid"  formatterjs="getRoomName" width="120"></t:dgCol>
   <t:dgCol title="房间id" field="floorid"   formatterjs="getFoolrName" width="120"></t:dgCol>
   <t:dgCol title="建筑物id" field="buildid"   formatterjs="getBuildName"  width="120"></t:dgCol>
   <t:dgCol title="园区id" field="parkid"   formatterjs="getParkName" width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="ddcController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="ddcController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="ddcController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="ddcController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 
 
 <script type="text/javascript">
	function getRoomName(value,row,index){
		var room="";
		$.ajax({
			type:"post",
			async: false,
			url:"ddcController.do?datagrid",
			data: {'roomid':row.roomid,'floorid':row.floorid,'buildid':row.buildid,'parkid':row.parkid},
			dataType: "json",
			success: function(data){

				console.log('dddddddddddddddddddd'');
			}
		});
		return room;
	}
	
	function getFoolrName(value,row,index){
		var floor="";
		$.ajax({
			type:"post",
			async: false,
			url:"ddcController.do?getName",
			data: {'floorid':value},
			dataType: "json",
			success: function(data){
				floor = data.obj;
				console.log(row.id);
			}
		});
		return floor;
	}
	
	function getBuildName(value,row,index){
		var build="";
		$.ajax({
			type:"post",
			async: false,
			url:"ddcController.do?getName",
			data: {'buildid':value},
			dataType: "json",
			success: function(data){
				build = data.obj;
				console.log(row.id);
			}
		});
		return build;
	}
	
	function getParkName(value,row,index){
		var park="";
		$.ajax({
			type:"post",
			async: false,
			url:"ddcController.do?getName",
			data: {'parkid':value},
			dataType: "json",
			success: function(data){
				park = data.obj;
				console.log(row.id);
			}
		});
		return park;
	}
 </script>
 
 