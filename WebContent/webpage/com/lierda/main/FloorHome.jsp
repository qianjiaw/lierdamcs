<%@page import="java.io.Console"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/mcs/plug-in/jquery/jquery-1.8.3.min.js"></script>
<script src="/mcs/plug-in/Highcharts-5.0.11/code/highcharts.js"></script>
<link rel="stylesheet" href="/mcs/plug-in/lierda/main/floormain.css" />
<link rel="stylesheet" href="/mcs/plug-in/lierda/main/hplushome.css" />

<title>楼层首页</title>
</head>
<body style="height: 100%; width: 100%; position: absolute;">

	<div id="home_main" class="home_main">
		<div id="left" class="left">
			<div id="building-title" class="main-left-title">
				<span class="main-message"></span>
				<p class="main-message-text">基本信息</p>
			</div>
			<div id="building-main" class="building-main">
				<div id="building-text" class="building-text"></div>
				<div id="building-container" class="building-container">
					<div id="building-building" class="building-building">
						<div id="building-header" class="building-header">
							<img alt="" id="buildingheader-pic" class="buildingheader-pic" src="/mcs/images/lierda/main-icon/building-header.png" usemap="#buildingheadermap" ></img>
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
						<span id="this-floor-top" class="floor-text-font"></span>
					</div>
					<div id="floor-main" class="floor-main"></div>
				</div>
				
				<div id="this-floor-controller" class="this-floor-room">
					<div id="floor-controller-text" class="floor-main-text">
						<span id="this-floor-cen" class="floor-text-font"></span>
					</div>
					<div id="floor-controller" class="floor-controller">
						<div id="floor-controller-step" class="floor-controller-step">
							<div id="controller-step-first" class="controller-step-first">
								<span class="controller-step-font">1、勾选房号</span>
								<div id="triangle-right" class="triangle-first-right"></div>
							</div>
							<div id="controller-step-second" class="controller-step-second">
								<span class="controller-step-font">2、勾选设备</span>
								<div id="triangle-left" class="triangle-second-left"></div>
								<div id="triangle-right" class="triangle-second-right"></div>
							</div>
							<div id="controller-step-third" class="controller-step-third">
								<span class="controller-step-font">3、控制操作</span>
								<div id="triangle-left" class="triangle-third-left"></div>
								<div id="triangle-right" class="triangle-third-right"></div>
							</div>
						</div>
						<div id="floor-controller-main" class="floor-controller-main">
							<div id="controller-main-first" class="controller-main-first">
								
							</div>
							<div id="controller-main-second" class="controller-main-second">
								<div id="controller-light" class="controller-second-main">
									<input id="controller-light-check" type="checkbox" class="controller-check"><span id="controller-light-text" class="controller-font">照明</span></input>
								</div>
								<div id="controller-air-condition" class="controller-second-main">
									<input id="controller-air-check" type="checkbox" class="controller-check"><span id="controller-air-text" class="controller-font">空调</span></input>
								</div>
							</div>
							<div id="controller-main-third" class="controller-main-third">
								<div id="controller-third-main" class="controller-third-main">
									<div id="controller-open-all" class="controller-open-all">
										<p id="open-controller">全开</p>
									</div>
									<div id="controller-close-all" class="controller-close-all">
										<p id="close-controller">全关</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div id="room-device-state" class="room-device-state">
					<div id="device-state-text" class="floor-main-text">
						<span id="this-floor-bot" class="floor-text-font">设备状态监控</span>
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



</body>

<script src="/mcs/webpage/com/lierda/main/js/addpage.js"></script>

<script>
	var room = [{"name":88101},{"name":88102},{"name":88103},{"name":88104},{"name":88105},{"name":88106},{"name":88107},{"name":88108},{"name":88109},{"name":88110},{"name":88111},{"name":88112},{"name":88113},{"name":88114},{"name":88115},{"name":88116},{"name":88117},{"name":88118}]
	var floornum = 18;

	$(function() {
		var req = getRequest();
		$("#this-floor-top").text(req.floor + "F楼层房态图");
		$("#this-floor-cen").text(req.floor + "F楼层控制模式");
		
		///////////////////////////////set Height and Width
		setHAndW();
		window.onresize = function () {
			setHAndW();
		}
		
		addBuilding();
		drawroomstate(room);
		drawroomcheck(room);
		addtable();

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
		setWidthByPercent("floor-controller-main", "controller-main-third",27, 2);
		
		///////////////////////////////setTop
		var controllerlightheight = getHeight("controller-light")/2-6;
		var controllerairconditionheight = getHeight("controller-air-condition")/2-6;
		$("#controller-light-check").css("top",""+controllerlightheight+"px");
		$("#controller-light-text").css("top",""+(controllerlightheight-2)+"px");
		$("#controller-air-check").css("top",""+controllerairconditionheight+"px");
		$("#controller-air-text").css("top",""+(controllerairconditionheight-2)+"px");
		
		var controllermainthird = getHeight("controller-main-third")*0.3;
		$("#controller-third-main").css("margin-top",""+(controllermainthird)+"px");
		
		///////////////////////////////setLineHeight
		var controllerthirdmain = getHeight("controller-third-main");
		$("#open-controller").css("line-height",""+controllerthirdmain+"px");
		$("#close-controller").css("line-height",""+controllerthirdmain+"px");
	}
	
	//////////////////////////addbuilding
	function addBuilding (){
		$("#building-building").height((floornum*28+20+70)+"px");
		$("#building-eachfloor").height((floornum*28+20)+"px");
		for(var i=floornum;i>0;i--){
			$("#building-eachfloor").append('<div id="floor-'+i+'" onclick="changetopshow(this)" class="eachfloor"><span class="floor-font">'+i+'F</span></div>')
		}
		
	}
	
	function drawroomstate (room) {
		var width = getWidth("floor-main") / 6 - 5;
		var height = width/2;
		for (var i = 0; i < room.length; i++) {
			$("#floor-main").append('<div id="room-state-'+room[i].name+'" onclick="selectRoom(this)" style="height:'+height+'px;width:'+width+'px;background-color:skyblue;float:left;margin-left:2px;margin-top:2px;">'+room[i].name+'</div>');
		}
	}

	function drawroomcheck (room) {
		var count = 0;
		var width = getWidth("controller-main-first") / 5 - 3;
		var height = width/2;
		$("#controller-main-first").append('<form id="roomcheckform"></form>');
		for (var i = 0; i < room.length; i++) {
			$("#roomcheckform").append('<div id="room-check-'+i+'" style="height:'+height+'px;width:'+width+'px;float:left;margin-left:2px;margin-top:2px;"></div>');
			$("#room-check-"+i+"").append('<input type="checkbox" style="top: 50%;margin-top: -6px;position: relative;float:left;"  value="'+room[i].name+'"></input>');
			$("#room-check-"+i+"").append('<p style="float: left;position:relative;font-size: 10px;line-height: '+height+'px;">'+room[i].name+'</p>');
		}
	}
	
	function addtable() {
		$("#device-state-body").append('<tr><td>1</td><td>88101</td><td>有无</td><td>门锁</td><td>照明</td><td>窗帘</td><td>插座</td><td>地暖</td><td>音乐</td> <td>11</td><td>22</td><td>33</td><td>44</td></tr>');
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
		var id = thisfloor.id.split("-")[1];
		$("#this-floor-top").text(id + "F楼层房态图");
		$("#this-floor-cen").text(id + "F楼层控制模式");
	}
	
	function selectRoom (thisroom) {
		var id = thisroom.id.split("-")[2];
		window.location.href="/mcs/webpage/com/lierda/main/RoomHome.jsp?room="+id+"";
	}
</script>

</html>