<%@page import="java.io.Console"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/mcs/plug-in/jquery/jquery-1.8.3.min.js"></script>
<script src="/mcs/plug-in/Highcharts-5.0.11/code/highcharts.js"></script>
<link rel="stylesheet" href="/mcs/plug-in/lierda/main/roommain.css" />
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
  								<area id="buildingheader" shape="poly" coords="5,70,78,0,151,70" href="#" />
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
					<div id="room-sence-power-title" class="main-right-title">
						<p id="roomid" class="main-message-text"></p>
					</div>
					<div id="room-sence" class="room-sence">
						<div id="sence-title" class="sence-title">
							<span id="sence-font" class="sence-font">情景模式选择</span>
						</div>
						<div id="choose-sence" class="choose-sence">
							<img alt="" id="sence-pic" class="sence-pic" src="/mcs/images/lierda/main-icon/sence-main.png" usemap="#sencemap" />
							<map name="sencemap" id="sencemap">
  								<area id="sence-one" shape="polygon" coords="15,0,44,0,57,25,44,50,15,50,0,25" href="#" />
							</map>
						</div>
					</div>
					<div id="room-power" class="room-power">
						
					</div>
				</div>
				<div id="room-device" class="room-device">
					<div id="room-device-title" class="main-right-title">
						
					</div>
					<div id="room-device-main" class="room-device-main">
						<table id="room-device-table" class="room-device-table">
							<thead>
								<tr>
									<th id="room-device-th1" class="room-device-th th1" >序号
										<button class="unclickth" onclick="changethstate(this)"
										id="1-0"></button>
									</th>
									<th id="room-device-th1" class="room-device-th th2">设备编号
										<button class="unclickth" onclick="changethstate(this)"
										id="2-0"></button>
									</th>
									<th id="room-device-th1" class="room-device-th th3">调光属性
										<button class="unclickth" onclick="changethstate(this)"
										id="3-0"></button>
									</th>
									<th id="room-device-th1" class="room-device-th th4">隶属于
										<button class="unclickth" onclick="changethstate(this)"
										id="4-0"></button>
									</th>
									<th id="room-device-th1" class="room-device-th th5" colspan="3">设备状态
										<button class="unclickth" onclick="changethstate(this)"
										id="5-0"></button>
									</th>
									<th id="room-device-th1" class="room-device-th th6">控制操作
										<button class="unclickth" onclick="changethstate(this)"
										id="6-0"></button>
									</th>
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



</body>

<script src="/mcs/plug-in/Echarts/echarts.min.js"></script>
<script src="/mcs/webpage/com/lierda/main/js/addpage.js"></script>
<script src="/mcs/webpage/com/lierda/main/js/roompowerchart.js"></script>

<script>
	var room = [{"name":88101},{"name":88102},{"name":88103},{"name":88104},{"name":88105},{"name":88106},{"name":88107},{"name":88108},{"name":88109},{"name":88110},{"name":88111},{"name":88112},{"name":88113},{"name":88114},{"name":88115},{"name":88116},{"name":88117},{"name":88118}]
	var floornum = 18;

	$(function() {
		var req = getRequest();
		$("#roomid").text(req.room + "房间");
		$("#").text(req.room + "房间");
		
		///////////////////////////////set Height and Width
		setHAndW();
		window.onresize = function () {
			setHAndW();
			resizePowerChart();
		}
		
		addBuilding();
		drawPowerChart();
		addtable();
		
		$("#sence-one").mouseover(function(e){
			senceone();
		});
		$("#sence-one").mouseout(function(e){
			sencemain();
		});

	});
	
	function setHAndW () {
		///////////////////////////////setHeight
		setHeight("home_main", "left", 20);
		setHeight("home_main", "right", 20);
		setHeight("left", "building-main", 56);
		setHeight("building-main", "building-container", 30);
		
		setHeight("room-sence-power", "room-sence", 40);
		setHeight("room-sence-power", "room-power", 40);

		
		
		////////////////////////////////setLineHeight
		var sencefontheight = getHeight("sence-title");
		setHeight("room-sence", "choose-sence", sencefontheight+50);//////////setSenceChooseHeight
		$("#sence-font").css("line-height",""+sencefontheight+"px");
		
		///////////////////////////////setCss
		var choosesencemargin = getWidth("choose-sence")/2;
		$("#choose-sence").css("margin-left","-"+choosesencemargin+"px");
	}
	
	//////////////////////////addbuilding
	function addBuilding (){
		$("#building-building").height((floornum*28+20+70)+"px");
		$("#building-eachfloor").height((floornum*28+20)+"px");
		for(var i=floornum;i>0;i--){
			$("#building-eachfloor").append('<div id="floor-'+i+'" onclick="selectFloor(this)" class="eachfloor"><span class="floor-font">'+i+'F</span></div>')
		}
		
	}
	
	/////////////////////////addTable
	function addtable() {
		$("#room-device-body").append(
			'<tr>'+
			'<td>1</td>'+
			'<td>0000000000000000</td>'+
			'<td>不可调</td>'+
			'<td>000000000000</td>'+
			'+<td>12</td>	<td>34</td>    <td>56</td>'+
			'<td>音乐</td>'+
			'</tr>'
		);
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
		window.location.href="/mcs/webpage/com/lierda/main/FloorHome.jsp?floor="+id+"";
	}
	
	function senceone () {
		$("#sence-pic").attr('src',"/mcs/images/lierda/main-icon/sence-one.png"); 
	}
	function sencemain () {
		$("#sence-pic").attr('src',"/mcs/images/lierda/main-icon/sence-main.png"); 
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
	
</script>

</html>