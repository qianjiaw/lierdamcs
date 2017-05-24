<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="/mcs/plug-in/Highcharts-5.0.11/code/highcharts.js"></script>

<link rel="stylesheet" href="/mcs/plug-in/lierda/main/hplushome.css" />


<title>首页</title>
</head>
<body style="position:absolute;width:100%;height: 100%;">
	<div id="home_main" class="home_main">
		<div id="left" class="left">
			<div id="building-title" class="main-left-title">
				<span class="main-message"></span>
				<p class="main-main-text">基本信息</p>
			</div>
			<div id="building-main" class="building-main">
				<div id="building-text" class="building-text">
					
				</div>
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
			<div id="right_top" class="right_top">
				<div id="building-title" class="main-right-title">
					<span class="main-device-state"></span>
					<p class="main-message-text">设备运行状态</p>
				</div>
				<div id="device-state-bar" class="device-state-bar">
					<div id="bar-1" class="each-bar">
						
					</div>
					<div id="bar-2" class="each-bar">
						
					</div>
					<div id="bar-3" class="each-bar">
						
					</div>
					<div id="bar-4" class="each-bar">
						
					</div>
				</div>
			</div>
			<div id="right_cen" class="right_cen">
				<div id="building-title" class="main-right-title">
					<span class="main-pic-power"></span>
					<p class="main-message-text">设备实时消耗</p>
				</div>
				<div id="device-power-spline" class="device-power-spline">
					
				</div>
			</div>
			<div id="right_bot" class="right_bot">
				<div id="building-title" class="main-right-title">
					<span class="main-sort-power"></span>
					<p class="main-message-text">分类分项能耗</p>
				</div>
				<div id="device-sort-power" class="device-sort-power">
					<div id="air-condition-power" class="each-sort-power">
						<div id="sort-power-pic" class="air-condition-pic"></div>
						<div id="sort-power-text" class="sort-power-text"></div>
					</div>
					<div id="light-power" class="each-sort-power">
						<div id="sort-power-pic" class="light-pic"></div>
						<div id="sort-power-text" class="sort-power-text"></div>
					</div>
					<div id="energy-power" class="each-sort-power">
						<div id="sort-power-pic" class="energy-pic"></div>
						<div id="sort-power-text" class="sort-power-text"></div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
</body>
<script type="text/javascript" src="/mcs/plug-in/jquery/jquery-1.8.3.min.js"></script>
<script src="/mcs/webpage/com/lierda/main/js/bar.js"></script>
<script src="/mcs/webpage/com/lierda/main/js/spline.js"></script>
<script>

var floornum = 13;
var buildId="";
var buildings="";
var floors="";

function getFloorNum(){
	$.ajax({
		type:"post",
		async: false,
		url:"loginController.do?getFloorNum",
		data: {'buildId':buildId},
		dataType: "json",
		success: function(data){
			attributes=	data.attributes;	
			buildings=attributes['buildings'];//所有建筑物id，name
			floors=attributes['floors'];//对应建筑物楼层id,name
		}
	});
}


$(function(){
	getFloorNum();
	setHAndWonload();
	window.onresize = function () {
		freshHAndW();
	}
	
});

function setHAndWonload () {
	////////////////////////////////setheight
	setHeight("home_main", "left", 20);
	setHeight("home_main", "right", 20);
	setHeight("left", "building-main", 56);
	setHeight("building-main", "building-container", 30);

	var barheight = $("#right_top").get(0).offsetHeight-30;
	$("#bar-1").height(barheight+"px");
	$("#bar-2").height(barheight+"px");
	$("#bar-3").height(barheight+"px");
	$("#bar-4").height(barheight+"px");

	$("#device-power-spline").height(($("#right_cen").get(0).offsetHeight-30)+"px");
	
	$("#device-sort-power").height(($("#right_bot").get(0).offsetHeight-50)+"px");
	
	addAllBar(barheight);
	addAllSpline();
	addBuilding();
	
}

function freshHAndW () {
	////////////////////////////////setheight
	setHeight("home_main", "left", 20);
	setHeight("home_main", "right", 20);
	setHeight("left", "building-main", 56);
	setHeight("building-main", "building-container", 30);

	var barheight = $("#right_top").get(0).offsetHeight-30;
	$("#bar-1").height(barheight+"px");
	$("#bar-2").height(barheight+"px");
	$("#bar-3").height(barheight+"px");
	$("#bar-4").height(barheight+"px");

	$("#device-power-spline").height(($("#right_cen").get(0).offsetHeight-30)+"px");
	
	$("#device-sort-power").height(($("#right_bot").get(0).offsetHeight-50)+"px");
	
}

////////////////////////////addbar
function addAllBar (height){
	addMainBar('bar-1',height);
	addMainBar('bar-2',height);
	addMainBar('bar-3',height);
	addMainBar('bar-4',height);
}

////////////////////////////addapline
function addAllSpline (){
	addSpline('device-power-spline');
}

//////////////////////////addbuilding
function addBuilding (){
	$("#building-building").height((floornum*28+20+70)+"px");
	$("#building-eachfloor").height((floornum*28+20)+"px");
	for(var i=floornum;i>0;i--){
		$("#building-eachfloor").append('<div id="floor-'+i+'" onclick="selectFloor(this)" class="eachfloor"><span class="floor-font">'+i+'F</span></div>')
	}
	
}
function selectFloor(obj){
	var id = obj.id.split("-")[1];
	window.location.href="/mcs/webpage/com/lierda/main/FloorHome.jsp?floor="+id+"";
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
function setWidthByPercent(getdivid, setdivid, percent,differpx) {
	$("#" + setdivid + "").width(($("#" + getdivid + "").get(0).offsetWidth*percent/100 - differpx) + "px");
}

</script>


</html>