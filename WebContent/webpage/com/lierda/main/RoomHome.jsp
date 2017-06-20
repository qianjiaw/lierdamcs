<%@page import="java.io.Console"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/plug-in/jquery/jquery-1.8.3.min.js"></script>
<script src="/plug-in/Highcharts-5.0.11/code/highcharts.js"></script>
<link rel="stylesheet" href="/plug-in/lierda/main/roommain.css" />
<link rel="stylesheet" href="/plug-in/lierda/main/hplushome.css" />

<title>楼层首页</title>
</head>
<body style="height: 100%; width: 100%; position: absolute;">

	<div id="home_main" class="home_main">
		<div id="left" class="left">
			<div id="building-title" class="main-left-title">
				<span id="main-message" class="main-message"></span>
				<p class="main-main-text">基本信息</p>
			</div>
			<div id="building-main" class="building-main">
				<div id="building-text" class="building-text">
					<div id="building-select" class="building-select" onclick="choosebuilding()">
						<p id="buildingname" ></p>
					</div>
				</div>
				<div id="building-container" class="building-container">
					<div id="building-building" class="building-building">
						<div id="building-header" class="building-header">
							<img alt="" id="buildingheader-pic" class="buildingheader-pic" src="/images/lierda/main-icon/building-header.png" usemap="#buildingheadermap" ></img>
							<map name="buildingheadermap" id="buildingheadermap">
  								<area id="buildingheader" shape="poly" coords="5,70,78,0,151,70" href="hplushome.jsp" />
							</map>
						</div>
						<div id="building-eachfloor" class="building-eachfloor">
							<!-- <div id="floor-1" class="eachfloor"><span class="floor-font">9F</span></div> -->
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="right" class="right">
			<div id="roommain" class="roommain">
				<div id="room-sence-power" class="room-sence-power">
					<div id="room-sence-power-title" class="room-sence-title">
						<p id="roomname" class="main-message-text"></p>
					</div>
					<div id="room-sence" class="room-sence">
						<div id="sence-title" class="sence-title">
							<span id="sence-font" class="sence-font">情景模式选择</span>
						</div>
						<div id="choose-sence" class="choose-sence">
							<img alt="" id="sence-pic" class="sence-pic" src="/images/lierda/main-icon/sence-main.png" usemap="#sencemap" />
							<map name="sencemap" id="sencemap">
  								<area id="sence-lighton" shape="polygon" coords="15,0,44,0,57,25,44,50,15,50,0,25" onclick="AllLightOn()" />
  								<area id="sence-lightoff" shape="polygon" coords="62,27,91,27,104,52,91,77,62,77,47,52" onclick="AllLightOff()" />
  								<area id="sence-airon" shape="polygon" coords="111,0,138,0,153,25,138,50,111,50,96,25" onclick="AllAirOn()" />
  								<area id="sence-airoff" shape="polygon" coords="158,27,193,27,200,52,193,77,158,77,143,52" onclick="AllAirOff()" />
							</map>
						</div>
					</div>
					<div id="room-power" class="room-power">
						
					</div>
				</div>
				<div id="room-device" class="room-device">
					<div id="room-device-title" class="main-right-title">
						<span id="room-device-icon" class="room-device-icon"></span>
						<select id="device-type-select" class="device-type-select" onchange="changeDeviceType(this)">
							<option id="type-1" class="main-right-text" selected="selected">智能灯</option>
							<option id="type-26" class="main-right-text">计量插座</option>
							<option id="type-19" class="main-right-text">面板开关</option>
							<option id="type-15" class="main-right-text">空调</option>
							<option id="type-5" class="main-right-text">窗帘</option>
							<option id="type-7" class="main-right-text">人感</option>
							<option id="type-13" class="main-right-text">单路开关</option>
							<option id="type-18" class="main-right-text">门磁</option>
						</select>
					</div>
					<div id="room-device-main" class="room-device-main">
						<table id="room-device-table" class="room-device-table">
							<thead>
								<tr id="room-table-tr" style="height:30px;">
									
								</tr>
								<tr id="room-table-trdetail" style="height:20px;">
									
								</tr>
							</thead>
							<tbody id="room-device-body" class="room-device-body">
							
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

	</div>

	<div id="building-choose-main" class="building-choose-main" onclick="hidechoose()">
		
	</div>

</body>

<script src="/plug-in/Echarts/echarts.min.js"></script>
<script src="/webpage/com/lierda/main/js/addpage.js"></script>
<script src="/webpage/com/lierda/main/js/roompowerchart.js"></script>
<script src="/webpage/com/lierda/main/js/roomsencecontroller.js"></script>
<script src="/webpage/com/lierda/main/js/addRoomTable.js"></script>
<script src="/webpage/com/lierda/main/js/tablesort.js"></script>

<script>
	var roomid = getRequest().roomid;
	var showRoom = {};
	var buildId="";
	var buildName = "";
	var showBuilding = {};
	var buildings=[];
	var floors=[];
	var showFloor = {};
	var floornum = 0;
	var ddcs = [];
	var devices = [];
	var showType = "";
	
	function getDetailByRoomid(){

		$.ajax({
			type:"post",
			async: false,
			url:"/zRoomController.do?getDetailByRoomid",
			data: {'roomid':roomid},
			dataType: "json",
			success: function(data){
				attributes=	data.attributes;
				buildings=attributes['allBuildings'];
				var currentBuilding = attributes['currentBuilding'];
				showBuilding = currentBuilding[0];
				var buildId=showBuilding.id;
				var buildName = showBuilding.buildingname;
				floors = attributes['floors'];
				floornum = floors.length;
				var currentFloor = attributes['currentFloor'];
				showFloor = currentFloor[0];
				var currentRoom = attributes['currentRoom'];
				showRoom = currentRoom[0];
				ddcs = attributes['ddcs'];
					
				//////////////////////after get data
				addBuilding(buildName);
				settitle();
			}
		});
	}

	function getBuildFloorMessage(){
		$.ajax({
			type:"post",
			async: false,
			url:"/zFloorController.do?getDetailByBuildingid",
			data: {'buildId':buildId},
			dataType: "json",
			success: function(data){
				attributes=	data.attributes;
				var currentBuild=attributes['currentBuild'];
				showBuilding = currentBuild[0];
				floors=attributes['floors'];
				floornum = floors.length;
				
				///////////////////////after getdata
				addBuilding(buildName);
			}
		});
	}

	function getDeviceByType (type) {////////////////////////////////////////////////////////////////////
		$.ajax({
			type:"post",
			async: false,
			url:"/zRoomController.do?getDeviceDetail",
			data: {'roomid':roomid,'type':type},
			dataType: "json",
			success: function(data){
				attributes=	data.attributes;
				console.log(attributes);
				devices = attributes['deviceDetail'];
				
				//////////////////////////after get data
				//////////////////////////addRoomTable.js
				addtable(type,devices);
			}
		});
	}
	
	
	$(function() {
		
		showType="1";
		
		///////////////////////////////set Height and Width
		setHAndW();
		window.onresize = function () {
			setHAndW();
			resizePowerChart();
		}
		
		/////////////////////////////getdata
		getDetailByRoomid();
		getDeviceByType(showType);
		
		drawPowerChart();
		
		listenSenceChange();
		
		/////////////////////////////////////////////addTableSort
		tableSort("room-device-table");

	});
	
	function setHAndW () {
		///////////////////////////////setHeight
		setHeight("home_main", "left", 20);
		setHeight("home_main", "right", 20);
		setHeight("left", "building-main", 56);
		setHeight("building-main", "building-container", 30);
		
		setHeight("room-sence-power", "room-sence", 40);
		setHeight("room-sence-power", "room-power", 40);

		setHeight("room-device", "room-device-main", 40);
		
		/////////////////////////////////////setWidth
		$("#main-message").width(getHeight("main-message")*6/7+"px");
		
		$("#room-device-icon").width(getHeight("room-device-icon")*6/7+"px");
		
		////////////////////////////////setLineHeight
		var sencefontheight = getHeight("sence-title");
		setHeight("room-sence", "choose-sence", sencefontheight+50);//////////setSenceChooseHeight
		$("#sence-font").css("line-height",""+sencefontheight+"px");
		
		///////////////////////////////setCss
		var choosesencemargin = getWidth("choose-sence")/2;
		$("#choose-sence").css("margin-left","-"+choosesencemargin+"px");
	}
	
	//////////////////////////addbuilding
	function addBuilding (buildName){
		$("#building-building").height((floornum*28+20+70)+"px");
		$("#building-eachfloor").height((floornum*28+20)+"px");
		$("#building-eachfloor").empty();
		for(i in floors){
			$("#building-eachfloor").append('<div id="floor-'+floors[(floors.length-1)-i].id+'" onclick="selectFloor(this)" class="eachfloor"><span class="floor-font">'+floors[(floors.length-1)-i].floorname+'F</span></div>')
		}

		$("#buildingname").text(""+buildName+"");
	}
	
	////////////////////////////////setTitle
	function settitle () {
		$("#roomname").text(""+showRoom.roomname+"房间");
	}
	
	function changeDeviceType (obj) {
		showType = $("#device-type-select option:selected")[0].id.split("-")[1];
		getDeviceByType(showType);
	}
	
	function getRequest() {
		var url = location.search;
		var theRequest = new Object();

		if (url.indexOf("?") != -1) {
			var str = url.substr(1);
			strs = str.split("&");
			for (var i = 0; i < strs.length; i++) {
				theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
			}

		}
		return theRequest;
	}

	function getHeight(divid) {
		var h = $("#" + divid + "").get(0).offsetHeight;
		return h;
	}

	function getWidth(divid) {
		var w = $("#" + divid + "").get(0).offsetWidth;
		return w;
	}

	function setHeight(getdivid, setdivid, differpx) {
		$("#" + setdivid + "").height(($("#" + getdivid + "").get(0).offsetHeight - differpx) + "px");
	}
	function setWidth(getdivid, setdivid, differpx) {
		$("#" + setdivid + "").width(($("#" + getdivid + "").get(0).offsetWidth - differpx) + "px");
	}
	function setWidthByPercent(getdivid, setdivid, percent,differpx) {
		$("#" + setdivid + "").width(($("#" + getdivid + "").get(0).offsetWidth*percent/100 - differpx) + "px");
	}


	function selectFloor(obj){
		var id = obj.id.split("-")[1];
		window.location.href="/webpage/com/lierda/main/FloorHome.jsp?floorid="+id+"";
	}
	
	
	function changethstate(e) {
		//////////////////////////////0:未点击
		//////////////////////////////1:上排序（正序）
		//////////////////////////////2：下排序（倒序）
		var id = e.id.split("-")[0];
		var state = e.id.split("-")[1];
		if (state == 0) {
			$("#" + e.id + "").addClass("clickupth");
			$("#" + e.id + "").removeClass("unclickth");
			$("#" + e.id + "").attr('id', '' + id + '-1');
		}
		if (state == 1) {
			$("#" + e.id + "").addClass("clickdownth");
			$("#" + e.id + "").removeClass("clickupth");
			$("#" + e.id + "").attr('id', '' + id + '-2');
		}
		if (state == 2) {
			$("#" + e.id + "").addClass("clickupth");
			$("#" + e.id + "").removeClass("clickdownth");
			$("#" + e.id + "").attr('id', '' + id + '-1');
		}
	}
	function choosebuilding () {
		$("#building-choose-main").css("display","block");
		$("#building-choose-main").empty();
		for(key in buildings){
			$("#building-choose-main").append(
				'<div id="building-'+buildings[key].id+'-'+buildings[key].buildingname+'" onclick="doChooseBuilding(this)" class="each-building">'+
	    			'<img style="width: 100%;height:156px;" alt="" src="/images/lierda/main-icon/building-choose.png"></img>'+
	    			'<p class="building-choose-font">'+buildings[key].buildingname+'</p>'+
	    		'</div>'
	    	);
	    }
	}

	function hidechoose () {
		$("#building-choose-main").css("display","none");
	}
	function doChooseBuilding (obj) {
		buildId = obj.id.split("-")[1];
		buildName = obj.id.split("-")[2];
		getBuildFloorMessage();
		addBuilding(buildName);
	}
</script>

</html>