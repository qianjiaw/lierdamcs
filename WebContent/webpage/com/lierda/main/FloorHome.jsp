<%@page import="java.io.Console"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/plug-in/jquery/jquery-1.8.3.min.js"></script>
<script src="/plug-in/Highcharts-5.0.11/code/highcharts.js"></script>
<link rel="stylesheet" href="/plug-in/lierda/main/floormain.css" />

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
						<p id="buildingname"></p>
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
			<div id="floormain" class="floormain">

				<div id="this-floor-room" class="this-floor-room">
					<div id="floor-room-text" class="floor-main-text">
						<span id="room-state-icon" class="room-state-icon"></span>
						<p id="this-floor-top" class="floor-text-font"></p>
					</div>
					<div id="floor-main" class="floor-main"></div>
				</div>
				
				<div id="this-floor-controller" class="this-floor-room">
					<div id="floor-controller-text" class="floor-main-text">
						<span id="floor-roomcheck-icon" class="floor-roomcheck-icon"></span>
						<p id="this-floor-cen" class="floor-text-font"></p>
					</div>
					<div id="floor-controller" class="floor-controller">
						<div id="floor-controller-step" class="floor-controller-step">
							<div id="controller-step-first" class="controller-step-first">
								<span class="controller-step-font">1、勾选房号</span>
							</div>
							<div id="controller-step-second" class="controller-step-second">
								<span class="controller-step-font">2、勾选设备</span>
							</div>
							<div id="controller-step-third" class="controller-step-third">
								<span class="controller-step-font">3、控制操作</span>
							</div>
						</div>
						<div id="floor-controller-main" class="floor-controller-main">
							<div id="controller-main-first" class="controller-main-first">
								
							</div>
							<div id="controller-main-second" class="controller-main-second">
								<div id="controller-light" class="controller-second-main">
									<input id="controller-light-check" type="checkbox" class="controller-check" value="LT-CTM"><span id="controller-light-text" class="controller-font">照明</span></input>
								</div>
								<div id="controller-air-condition" class="controller-second-main">
									<input id="controller-air-check" type="checkbox" class="controller-check" value="CL-RL6"><span id="controller-air-text" class="controller-font">空调</span></input>
								</div>
							</div>
							<div id="controller-main-third" class="controller-main-third">
								<div id="controller-third-main" class="controller-third-main">
									<div id="controller-open-all" class="controller-open-all">
										<p id="open-controller" onclick="allcontroller(this)">全开</p>
									</div>
									<div id="controller-close-all" class="controller-close-all">
										<p id="close-controller" onclick="allcontroller(this)">全关</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div id="room-device-state" class="room-device-state">
					<div id="device-state-text" class="floor-main-text">
						<span id="floor-bot-icon" class="floor-bot-icon"></span>
						<p id="floor-text-font" class="floor-text-font">设备状态监控</p>
					</div>
					<div id="device-state" class="floor-main">
						<table id="device-state-table" class="device-state-table">
							<thead id="device-state-head" class="device-state-head">
								<tr>
									<th>序号
										<button class="unclickth" onclick="changethstate(this)"
											id="1-0"></button>
									</th>
									<th>房号
										<button class="unclickth" onclick="changethstate(this)"
											id="2-0"></button>
									</th>
									<th>有无人
										<button class="unclickth" onclick="changethstate(this)"
											id="3-0"></button>
									</th>
									<th>门锁状态
										<button class="unclickth" onclick="changethstate(this)"
											id="4-0"></button>
									</th>
									<th>照明状态
										<button class="unclickth" onclick="changethstate(this)"
											id="5-0"></button>
									</th>
									<th>窗帘状态
										<button class="unclickth" onclick="changethstate(this)"
											id="6-0"></button>
									</th>
									<th>插座状态
										<button class="unclickth" onclick="changethstate(this)"
											id="7-0"></button>
									</th>
									<th>地暖状态
										<button class="unclickth" onclick="changethstate(this)"
											id="8-0"></button>
									</th>
									<th>音乐状态
										<button class="unclickth" onclick="changethstate(this)"
											id="9-0"></button>
									</th>
									<th class="last-th" colspan="4">空调状态
										<button class="unclickth" onclick="changethstate(this)"
											id="10-0"></button>
									</th>
								</tr>
							</thead>
							<tbody id="device-state-body" class="device-state-body">

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

<script src="/webpage/com/lierda/main/js/addpage.js"></script>
<script src="/webpage/com/lierda/main/js/tablesort.js"></script>

<script>
	var buildId="";
	var buildName = "";
	var showBuilding = {};
	
	var buildings=[];

	var floor=[];
	var floors=[];
	var floornum = 0;
	var floorid=getRequest().floorid;
	
	var rooms=[];
	var roomsState = {};
	

	
	////////////////////////////////////////////getbuildingdata
	function getDetailByFloorid(){
		$.ajax({
			type:"post",
			async: false,
			url:"/zFloorController.do?getDetailByFloorid",
			data: {'floorid':floorid},
			dataType: "json",
			success: function(data){
				attributes=	data.attributes;
				console.log(attributes);
				buildings=attributes['allBuildings'];
				var building=attributes['building'];
				showBuilding = building[0];
				floor=attributes['floor'];
				floors=attributes['floors'];
				rooms=attributes['rooms'];
				
				floornum=floors.length;
				buildId = showBuilding.id;
				buildName = showBuilding.buildingname;
				
				///////////////////////after getdata
				addBuilding(buildName);
				drawroomcheck();
			}
		});
	}
	
	////////////////////////////////////////////获取当前楼层所有房间设备状态
	function getDeviceStatus () {
		$.ajax({
			type:"post",
			async: false,
			url:"/zFloorController.do?getDeviceStatus",
			data: {'floorid':floorid},
			dataType: "json",
			success: function(data){
				attributes=	data.attributes;
				roomsState = attributes;
				
				//////////////////////after getdata
				drawroomstate();
				addtable();
			}
		});
	}
	
	/////////////////////////////////////////更改建筑
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
				drawroomcheck();
			}
		});
	}
	

	$(function() {
		
		///////////////////////////////set Height and Width
		setHAndW();
		window.onresize = function () {
			setHAndW();
		}
		
		/////////////////////////////////////////getdata
		getDetailByFloorid();
		getDeviceStatus();
		
		////////////////////////////////////////add show
		$("#this-floor-top").text(floor[0].floorname + "F楼层房态图");
		$("#this-floor-cen").text(floor[0].floorname + "F楼层控制模式");
		
		//////////////////////////////////////////////addTableSort
		tableSort("device-state-table");

	});
	
	function setHAndW () {
		///////////////////////////////setHeight
		setHeight("home_main", "left", 20);
		setHeight("home_main", "right", 20);
		setHeight("left", "building-main", 56);
		setHeight("building-main", "building-container", 30);
		
		setHeight("this-floor-room", "floor-main", 50);
		setHeight("this-floor-controller", "floor-controller", 50);
		setHeight("room-device-state", "device-state", 50);
		setHeight("floor-controller", "floor-controller-main", 50);
		
		///////////////////////////////setWidth
		setWidthByPercent("floor-controller-main", "controller-main-first",50, 1);
		setWidthByPercent("floor-controller-main", "controller-main-second",22, 1);
		setWidthByPercent("floor-controller-main", "controller-main-third",28, 2);
		cmainsechwidth = getWidth("controller-main-first");
		$("#controller-main-second").css("left",""+cmainsechwidth+"px");
		
		$("#main-message").width(getHeight("main-message")*6/7+"px");
		////////////icon setWidth
		$("#room-state-icon").width(getHeight("room-state-icon")*6/7+"px");
		$("#floor-roomcheck-icon").width(getHeight("floor-roomcheck-icon")+"px");
		$("#floor-bot-icon").width(getHeight("floor-bot-icon")*1.2+"px");
		
		///////////////////////////////setTop
		var controllerlightheight = getHeight("controller-light")/2-6;
		var controllerairconditionheight = getHeight("controller-air-condition")/2-6;
		$("#controller-light-check").css("top",""+controllerlightheight+"px");
		$("#controller-light-text").css("top",""+(controllerlightheight-2)+"px");
		$("#controller-air-check").css("top",""+controllerairconditionheight+"px");
		$("#controller-air-text").css("top",""+(controllerairconditionheight-2)+"px");
		
		var controllermainthird = getHeight("controller-main-third")*0.1;
		$("#controller-third-main").css("margin-top",""+(controllermainthird)+"px");
		
		///////////////////////////////setLineHeight
		var controlleropenline = getHeight("controller-open-all");
		var controllercloseline = getHeight("controller-close-all");
		$("#open-controller").css("line-height",""+controlleropenline+"px");
		$("#close-controller").css("line-height",""+controllercloseline+"px");
		
		///////////////////////////////////set FontSize
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
	
	function drawroomstate () {
		$("#floor-main").empty();
		var width = getWidth("floor-main") / 6 - 5;
		var height = (width/118)*74;
		var stateheight = height*3/5-5;
		var lineheight = height*2/5;
		for (var i = 0; i < rooms.length; i++) {
			var roomState = roomsState[""+rooms[i].id+""];
			$("#floor-main").append('<div id="room-state-'+rooms[i].id+'" onclick="selectRoom(this)" class="room-state" style="height:'+height+'px;width:'+width+'px;line-height:'+lineheight+'px;font-size:'+lineheight+'px;">'+
			'<div id="room-state-pic" class="room-state-pic" style="height:'+stateheight+'px">'+
			'<div id="room-light-state" class="room-light-'+roomState.light.status+'"></div>'+
			'<div id="room-air-state" class="room-air-'+roomState.airConditioner.status+'"></div>'+
			'<div id="room-person-state" class="room-person-'+roomState.senseHuman.status+'"></div>'+
			'</div>'+
			'<p class="room-state-font">'+rooms[i].roomname+'</p>'+
			'</div>');
		}
	}

	function drawroomcheck () {
		$("#controller-main-first").empty();
		var count = 0;
		var width = getWidth("controller-main-first") / 5 - 3;
		var height = width/2;
		$("#controller-main-first").append('<form id="roomcheckform"></form>');
		for (var i = 0; i < rooms.length; i++) {
			$("#roomcheckform").append('<div id="room-check-'+i+'" style="height:'+height+'px;width:'+width+'px;min-width:50px;float:left;margin-left:2px;margin-top:2px;"></div>');
			$("#room-check-"+i+"").append('<input type="checkbox" class="room-check-input"  value="'+rooms[i].id+'"></input>');
			$("#room-check-"+i+"").append('<a style="float:left;position:relative;font-size:12px;font-family:PingFangSC-Regular;line-height:'+height+'px;">'+rooms[i].roomname+'</a>');
			//href="/webpage/com/lierda/main/RoomHome.jsp?roomid='+rooms[i].id+'"
		}
	}
	
	function allcontroller (obj) {
		var controllertype = obj.id.split("-")[0];
		console.log(controllertype);
		var senddata = {};
		var data = [];
		var checkroom = 0;
		for (var i=0;i<$("#roomcheckform input:checkbox").length;i++) {
			if ($("#roomcheckform input:checkbox")[i].checked) {
				checkroom++;
				var roomtype = {"roomid":"","type":[]};
				var name = $("#roomcheckform input:checkbox")[i].value;
				roomtype["roomid"] = name;
				if ($("#controller-light input:checkbox")[0].checked) {
					roomtype["type"][0] = $("#controller-light input:checkbox")[0].value;
					if ($("#controller-air-condition input:checkbox")[0].checked) {
						roomtype["type"][1] = $("#controller-air-condition input:checkbox")[0].value;
					}
				}
				else if ($("#controller-air-condition input:checkbox")[0].checked) {
					roomtype["type"][0] = $("#controller-air-condition input:checkbox")[0].value;
				}
				else {
					alert("请选择设备类型");
					return false;
				}
				data.push(roomtype);
			}
		}
		if (checkroom == 0) {
			alert("请选择房间");
			return false;
		}
		senddata = {data};
		var devicetype = roomtype["type"];
		upsenddata(JSON.stringify(senddata),devicetype,controllertype);
	}
	
	function upsenddata (data,devicetype,controllertype) {
		
		$.ajax({
			type:"post",
			async: false,
			url:"/zFloorController.do?getAllDeviceByRAT",
			data: {'roomtypedata':data},
			dataType: "json",
			success: function(data){
				attributes=	data.attributes;
				var r = attributes['result'];
				if (r.length>0) {
					///////////////////////////getbackdata
					var ddcmac = r[0].ddcmac;
					var serverip = r[0].serverip;
					var type = devicetype;
					
					/////////////////////////sendcmd
					if (type.length == 1) {
						sendcmd(serverip,ddcmac,type[0],controllertype);
					}
					else if (type.length == 2) {

						console.log(type[0]);
						sendcmd(serverip,ddcmac,type[0],controllertype);
						sendcmd(serverip,ddcmac,type[1],controllertype);
					}
				}
				else {
					console.log("no device");
					return false;	
				}
			}
		});

	}
	
	function sendcmd (serverip,ddcmac,type,controllertype) {
		var cmd = {
				"sourceId":"123456",
				"serialNum":(new Date().getTime())%10000,
				"requestType":"cmd",
				"id":"0000000000000000",
				"ddcId":"",
				"attributes":{"GRP":"ff"}
		};
		cmd["ddcId"] = ddcmac;
		cmd["attributes"]["TYP"] = type;
		if ("open" == controllertype) {
			cmd["attributes"]["SWI"] = "ON";
		}
		else {
			cmd["attributes"]["SWI"] = "OFF";
		}
		var jsoncmd = JSON.stringify(cmd);
		console.log(jsoncmd);
		console.log("http://" +serverip+ ":8006/action");
		
		$.ajax({
			type:"post",
			async: false,
			url:"http://202.107.200.162:8006/action",
			data: {'pn':'cmd','cmdStr':jsoncmd},
			dataType: "json",
			success: function(data){
				console.log(data);
				
				///////////////////////////getbackdata
				
				/////////////////////////sendcmd
				
			},
			false: function (data) {
				console.log("false");
			}
		});

	}
	
	function addtable() {
		for (var i = 0; i < rooms.length; i++) {
			var roomState = roomsState[""+rooms[i].id+""];
			console.log(rooms[i].roomname);
			console.log(roomState);
			$("#device-state-body").append(
				'<tr style="height:50px;font-size:14px;">'+
					'<td>'+(i+1)+'</td>'+
					'<td>'+rooms[i].roomname+'</td>'+
					'<td><div class="table-user-'+roomState.senseHuman.status+'"></div></td>'+
					'<td><div class="table-switch-'+roomState.lock.status+'"></div></td>'+
					'<td><div class="table-switch-'+roomState.light.status+'"></div></td>'+
					'<td><div class="table-switch-'+roomState.blind.status+'"></div></td>'+	
					'<td><div class="table-switch-'+roomState.powerStrip.status+'"></div></td>'+
					'<td><div class="table-switch-'+roomState.floorHeating.status+'"></div></td>'+
					'<td><div class="table-switch-'+roomState.bgm.status+'"></div></td>'+
					'<td style="height:20px;width:20px;"><div class="table-switch-'+roomState.airConditioner.status+'"></div></td>'+
					'<td id="air-mode" style="height:20px;width:20px;"></td>'+
					'<td id="air-temperature" style="height:20px;width:20px;"></td>'+
					'<td id="air-windSpeed" style="height:20px;width:20px;"></td>'+
				'</tr>'
			);
			if (roomState.airConditioner.status == "ON") {
				$("#air-mode").append('<img src="/images/lierda/roomstate/'+roomState.airConditioner.mode+'.png" style="height:100%;width:100%;"></img>');
				$("#air-temperature").append('<span style="font-family:PingFangSC-Regular;font-size:12px;color:#728ce3;">'+roomState.airConditioner.temperature+'℃</span>');
				switch(roomState.airConditioner.windSpeed)
				{
				case 1 :$("#air-mode").append('<img src="/images/lierda/roomstate/airwind-1.png" style="height:100%;width:100%;"></img>');
				case 2 :$("#air-mode").append('<img src="/images/lierda/roomstate/airwind-2.png" style="height:100%;width:100%;"></img>');
				case 3 :$("#air-mode").append('<img src="/images/lierda/roomstate/airwind-3.png" style="height:100%;width:100%;"></img>');
				default:$("#air-mode").append('');
				};
				/*
				if (roomState.airConditioner.windSpeed==1) {
					$("#air-mode").append('<img src="/images/lierda/roomstate/airwind-1.png" style="height:100%;width:100%;"></img>');
				}
				if (roomState.airConditioner.windSpeed==2) {
					$("#air-mode").append('<img src="/images/lierda/roomstate/airwind-2.png" style="height:100%;width:100%;"></img>');
				}
				if (roomState.airConditioner.windSpeed==3) {
					$("#air-mode").append('<img src="/images/lierda/roomstate/airwind-3.png" style="height:100%;width:100%;"></img>');
				}
				*/
				
			}
		}
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

	function changetopshow (thisfloor) {
		var name = thisfloor.id.split("-")[1];
		$("#this-floor-top").text(name + "F楼层房态图");
		$("#this-floor-cen").text(name + "F楼层控制模式");
	}
	
	function selectFloor(obj){
		var id = obj.id.split("-")[1];
		window.location.href="/webpage/com/lierda/main/FloorHome.jsp?floorid="+id+"";
	}
	
	function selectRoom (thisroom) {
		var id = thisroom.id.split("-")[2];
		window.location.href="/webpage/com/lierda/main/RoomHome.jsp?roomid="+id+"";
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