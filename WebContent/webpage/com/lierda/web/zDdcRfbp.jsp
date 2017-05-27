<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>ddc关联表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="zDdcRfbpController.do?save">
		<input id="id" name="id" type="hidden" value="${zDdcRfbpPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">ddcmac:</label>
		      <input class="inputxt" id="ddcmac" name="ddcmac" ignore="ignore"
					   value="${zDdcRfbpPage.ddcmac}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">房间id:</label>
			  <select id="roomid" name="roomid">
				
			  </select>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">楼层id:</label>
		      <select id="floorid" name="floorid">
				
			  </select>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">建筑物id:</label>
		      <select id="buildid" name="buildid">
				
			  </select>
		      <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">园区id:</label>
			  <select id="parkid" name="parkid">
			  	<option selected="selected" disabled="disabled" style="display: none" value=""></option>
				<c:forEach items="${zparkList}" var="item">
					<option id="${item.id}" class="inputxt" value="${item.id}">${item.parkname}</option>
				</c:forEach>
			  </select>
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
  
  <script>
 	var buildings = [];
 	var floors = [];
 	var rooms = [];
 	
  	$("#parkid").change(function () {
  		var parkid = $("#parkid option:selected").attr("id");
  		getAllBuildingsByParkId(parkid);
  	});
  	$("#buildid").change(function () {
  		var buildid = $("#buildid option:selected").attr("id");
  		getAllFloorsByBuildId(buildid);
  	});
  	$("#floorid").change(function () {
  		var buildid = $("#floorid option:selected").attr("id");
  		getAllRoomsByFloorId(buildid);
  	});
  	
  	function getAllBuildingsByParkId(parkid){
		$.ajax({
			type:"post",
			async: false,
			url:"/mcs/zDdcRfbpController.do?getAllBuildingsByParkId",
			data: {'parkid':parkid},
			dataType: "json",
			success: function(data){
		  		console.log("done");
				attributes=	data.attributes;
				buildings = attributes['buildings'];
				console.log(buildings);
				addbuildings();
			}
		});
	}
  	
  	function getAllFloorsByBuildId (buildid){
		$.ajax({
			type:"post",
			async: false,
			url:"/mcs/zDdcRfbpController.do?getAllFloorsByBuildId",
			data: {'buildid':buildid},
			dataType: "json",
			success: function(data){
		  		console.log("done");
				attributes=	data.attributes;
				floors = attributes['floors'];
				addfloors();
			}
		});
	}
  	
  	function getAllRoomsByFloorId (floorid){
		$.ajax({
			type:"post",
			async: false,
			url:"/mcs/zDdcRfbpController.do?getAllRoomsByFloorId",
			data: {'floorid':floorid},
			dataType: "json",
			success: function(data){
		  		console.log("done");
				attributes=	data.attributes;
				rooms = attributes['rooms'];
				addrooms();
			}
		});
	}
  	
  	function addbuildings () {
		$("#buildid").empty();
		$("#buildid").append('<option selected="selected" disabled="disabled" style="display: none" value=""></option>');
		for (i in buildings) {
			$("#buildid").append('<option id="'+buildings[i].id+'" class="inputxt" value="'+buildings[i].id+'">'+buildings[i].buildingname+'</option>');
		}
  	}
  	function addfloors () {
		$("#floorid").empty();
		$("#floorid").append('<option selected="selected" disabled="disabled" style="display: none" value=""></option>');
		for (i in floors) {
			$("#floorid").append('<option id="'+floors[i].id+'" class="inputxt" value="'+floors[i].id+'">'+floors[i].floorname+'</option>');
		}
  	}
  	function addrooms () {
		$("#roomid").empty();
		$("#roomid").append('<option selected="selected" disabled="disabled" style="display: none" value=""></option>');
		for (i in rooms) {
			$("#roomid").append('<option id="'+rooms[i].id+'" class="inputxt" value="'+rooms[i].id+'">'+rooms[i].roomname+'</option>');
		}
  	}
  </script>
  
 </body>