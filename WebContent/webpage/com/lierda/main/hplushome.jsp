<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="/mcs/plug-in/Highcharts-5.0.11/code/highcharts.js"></script>
<script src="/mcs/plug-in/Echarts/echarts.min.js"></script>

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
					<div id="building-select" class="building-select" onclick="choosebuilding()">
						<p id="buildingname"></p>
					</div>
				</div>
				<div id="building-container" class="building-container">
					<div id="building-building" class="building-building">
						<div id="building-header" class="building-header">
							<img alt="" id="buildingheader-pic" class="buildingheader-pic" src="/mcs/images/lierda/main-icon/building-header.png" usemap="#buildingheadermap" ></img>
							<map name="buildingheadermap" id="buildingheadermap">
  								<area id="buildingheader" shape="poly" coords="5,70,78,0,151,70" onclick="freshpage()" />
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
					<div class="bar-text" style="left:0px;margin-left: 1%;">照明设备</div>
					<div id="bar-2" class="each-bar">
						
					</div>
					<div class="bar-text" style="left:0px;margin-left: 25.75%;">空调设备</div>
					<div id="bar-3" class="each-bar">
						
					</div>
					<div class="bar-text" style="right:0px;margin-right: 25.75%;">插座设备</div>
					<div id="bar-4" class="each-bar">
						
					</div>
					<div class="bar-text" style="right:0px;margin-right: 1%;">其他设备</div>
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
	
	<div id="building-choose-main" class="building-choose-main" onclick="hidechoose()">
		
	</div>
</body>
<script type="text/javascript" src="/mcs/plug-in/jquery/jquery-1.8.3.min.js"></script>
<script src="/mcs/webpage/com/lierda/main/js/bar.js"></script>
<script src="/mcs/webpage/com/lierda/main/js/powerbar.js"></script>
<script>

var buildings=[];
var floors=[];
var floornum = 0;
var showBuilding = {};
var buildId="";
var buildName = "";

function getAllBuilding(){
	$.ajax({
		type:"post",
		async: false,
		url:"/mcs/zBuildingController.do?getAllBuildings",
		data: {},
		dataType: "json",
		success: function(data){
			attributes=	data.attributes;
			buildings=attributes['buildings'];//所有建筑物id，name
		}
	});
}

function getBuildFloorMessage(){
	$.ajax({
		type:"post",
		async: false,
		url:"/mcs/zFloorController.do?getDetailByBuildingid",
		data: {'buildId':buildId},
		dataType: "json",
		success: function(data){
			attributes=	data.attributes;
			var currentBuild=attributes['currentBuild'];
			showBuilding = currentBuild[0];
			console.log(showBuilding);
			buildId=showBuilding.id;
			floors=attributes['floors'];
			floornum = floors.length;
			

			////////////////////////after get buildid
			getPowerBybid();
		}
	});
}

function getPowerBybid () {
	$.ajax({
		type:"post",
		async: false,
		url:"/mcs/zBuildingController.do?getPowerBybid",
		data: {'buildId':buildId},
		dataType: "json",
		success: function(data){
			attributes=	data.attributes;
			var currentPower=attributes['currentPower'];
			var recordingEntities=attributes['recordingEntities'];
			var currentPowerTotal=attributes['currentPowerTotal'];
			var typePowerMap=attributes['typePowerMap'];
			console.log(typePowerMap);
			
			
			////////////////////////////after get data
			////////////////////////////addPowerBar
			addPowerBar('device-power-spline',typePowerMap);
		}
	});
}

$(function(){
	getdata();
	window.onresize = function () {
		freshHAndW();
	}
	
});

function getdata () {
	getAllBuilding();
	setHAndWonload();
}

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
	
	
	//////////////////////////////////getdata and add bar/spline
	getBuildFloorMessage();
	addAllBar(barheight);
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
	resizePowerChart('device-power-spline');
	
	$("#device-sort-power").height(($("#right_bot").get(0).offsetHeight-50)+"px");
	
}

////////////////////////////addbar
function addAllBar (height){
	addDeviceBar('bar-1',height);
	addDeviceBar('bar-2',height);
	addDeviceBar('bar-3',height);
	addDeviceBar('bar-4',height);
}

//////////////////////////addbuilding
function addBuilding (){
	$("#building-building").height((floornum*28+20+70)+"px");
	$("#building-eachfloor").height((floornum*28+20)+"px");
	for(i in floors){
		$("#building-eachfloor").append('<div id="floor-'+floors[(floors.length-1)-i].id+'" onclick="selectFloor(this)" class="eachfloor"><span class="floor-font">'+floors[(floors.length-1)-i].floorname+'F</span></div>')
	}
	$("#buildingname").text(""+showBuilding.buildingname+"");
}
function refreshBuilding (buildName){
	$("#building-building").height((floornum*28+20+70)+"px");
	$("#building-eachfloor").height((floornum*28+20)+"px");
	$("#building-eachfloor").empty();
	for(i in floors){
		$("#building-eachfloor").append('<div id="floor-'+floors[(floors.length-1)-i].id+'" onclick="selectFloor(this)" class="eachfloor"><span class="floor-font">'+floors[(floors.length-1)-i].floorname+'F</span></div>')
	}

	$("#buildingname").text(""+buildName+"");
}
function selectFloor(obj){
	var id = obj.id.split("-")[1];
	window.location.href="/mcs/webpage/com/lierda/main/FloorHome.jsp?floorid="+id+"";
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
function choosebuilding () {
	$("#building-choose-main").css("display","block");
	$("#building-choose-main").empty();
	for(key in buildings){
		$("#building-choose-main").append(
			'<div id="building-'+buildings[key].id+'-'+buildings[key].buildingname+'" onclick="doChooseBuilding(this)" class="each-building">'+
    			'<img style="width: 100%;height:156px;" alt="" src="/mcs/images/lierda/main-icon/building-choose.png"></img>'+
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
	refreshBuilding(buildName);
}

function freshpage() {
	window.location.reload();
}

</script>


</html>