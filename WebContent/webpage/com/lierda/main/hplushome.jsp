<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="/plug-in/Highcharts-5.0.11/code/highcharts.js"></script>
<script src="/plug-in/Echarts/echarts.min.js"></script>

<link rel="stylesheet" href="/plug-in/lierda/main/hplushome.css" />

<title>首页</title>
</head>
<body style="position:absolute;width:100%;height: 100%;">
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
				<div id="devicesate-title" class="main-righttop-title">
					<span id="main-device-state" class="main-device-state"></span>
					<p  id="main-righttop-text" class="main-message-text">设备运行状态</p>
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
				<div id="realtime-consumption-title" class="main-rightcenter-title">
					<span id="main-pic-power" class="main-pic-power"></span>
					<p id="main-rightcenter-text" class="main-message-text">设备实时能耗</p>
				</div>
				<div id="device-power-spline" class="device-power-spline">
					
				</div>
			</div>
			<div id="right_bot" class="right_bot">
				<div id="classification-title" class="main-rightbottom-title">
					<span id="main-sort-power" class="main-pic-sortpower"></span>
					<p id="main-rightbottom-text" class="main-message-text">分类分项能耗</p>
				</div>
				<div id="device-sort-power" class="device-sort-power">
					<div id="air-condition-power" class="each-sort-power">
						<div id="sort-air-pic" class="air-condition-pic">
							<img alt="" src="/images/lierda/main-icon/air-condition-pic.png" class="device-sort-power-img"></img>
						</div>
						<div id="sort-air-text" class="sort-power-text">
							<p class="sort-text-title">空调用电</p>
							<p class="sort-text-used"><span id="air-today-used" style="font-size:20px;"></span> KW·h</p>
							<p class="sort-text-compare">昨日同期比 <span id="air-used-compare"></span><img id="air-used-comparepic" class="sort-pic-compare" alt="" src=""></p>
						</div>
					</div>
					<div id="light-power" class="each-sort-power">
						<div id="sort-light-pic" class="light-pic">
							<img alt="" src="/images/lierda/main-icon/light-pic.png" class="device-sort-power-img"></img>
						</div>
						<div id="sort-light-text" class="sort-power-text">
							<p class="sort-text-title">照明用电</p>
							<p class="sort-text-used"><span id="light-today-used" style="font-size:20px;"></span> KW·h</p>
							<p class="sort-text-compare">昨日同期比 <span id="light-used-compare"></span><img id="light-used-comparepic" class="sort-pic-compare" alt="" src=""></p>
						</div>
					</div>
					<div id="energy-power" class="each-sort-power">
						<div id="sort-energy-pic" class="energy-pic">
							<img alt="" src="/images/lierda/main-icon/energy-pic.png" class="device-sort-power-img"></img>
						</div>
						<div id="sort-energy-text" class="sort-power-text"></div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	
	<div id="building-choose-main" class="building-choose-main" onclick="hidechoose()">
		
	</div>
</body>
<script type="text/javascript" src="/plug-in/jquery/jquery-1.8.3.min.js"></script>
<script src="/webpage/com/lierda/main/js/bar.js"></script>
<script src="/webpage/com/lierda/main/js/powerbar.js"></script>
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
		url:"/zBuildingController.do?getAllBuildings",
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
		url:"/zFloorController.do?getDetailByBuildingid",
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
			getDeviceStatus();
			getDayPower();
		}
	});
}

function getDeviceStatus () {
	$.ajax({
		type:"post",
		async: false,
		url:"/zBuildingController.do?getDeviceCount",
		data: {'buildId':buildId},
		dataType: "json",
		success: function(data){
			attributes=	data.attributes;

			////////////////////////////after get data
			////////////////////////////add ALLBar
			var barheight = $("#device-state-bar").get(0).offsetHeight;
			addAllBar(barheight,attributes);
		}
	});
}

function getPowerBybid () {
	$.ajax({
		type:"post",
		async: false,
		url:"/zBuildingController.do?getPowerBybid",
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

function getDayPower () {
	$.ajax({
		type:"post",
		async: false,
		url:"/zBuildingController.do?getDayPower",
		data: {'buildId':buildId},
		dataType: "json",
		success: function(data){
			attributes=	data.attributes;
			console.log(attributes);
			
			//////////////////////////////////after get data
			addRightBot(attributes);
		
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

	$("#device-state-bar").height(($("#right_top").get(0).offsetHeight-getHeight("devicesate-title"))+"px");
	$("#device-power-spline").height(($("#right_cen").get(0).offsetHeight-getHeight("realtime-consumption-title"))+"px");
	$("#device-sort-power").height(($("#right_bot").get(0).offsetHeight-getHeight("classification-title"))+"px");
	/*after set device-sort-power height*/
	$("#air-used-comparepic").width($("#air-used-comparepic").get(0).offsetHeight);
	
	$("#sort-air-pic").width(($("#sort-air-pic").get(0).offsetHeight)+"px");
	$("#sort-light-pic").width(($("#sort-light-pic").get(0).offsetHeight)+"px");
	$("#sort-energy-pic").width(($("#sort-energy-pic").get(0).offsetHeight)+"px");
	var pictop = ($("#device-sort-power").get(0).offsetHeight - $("#sort-air-pic").get(0).offsetHeight)/2;
	$("#sort-air-pic").css("top",""+pictop+"px");
	$("#sort-light-pic").css("top",""+pictop+"px");
	$("#sort-energy-pic").css("top",""+pictop+"px");
	
	////////////////////////////////////set Width
	$("#main-message").width(getHeight("main-message")*6/7+"px");
	$("#main-device-state").width(getHeight("main-device-state")*6/7+"px");
	$("#main-pic-power").width(getHeight("main-pic-power")*6/7+"px");
	$("#main-sort-power").width(getHeight("main-sort-power")*6/7+"px");
	
	
	///////////////////////////////////set LineHeight
	
	//////////////////////////////////getdata and add bar/spline
	getBuildFloorMessage();
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

	$("#device-state-bar").height(($("#right_top").get(0).offsetHeight-getHeight("devicesate-title"))+"px");
	
	$("#device-power-spline").height(($("#right_cen").get(0).offsetHeight-getHeight("realtime-consumption-title"))+"px");
	resizePowerChart('device-power-spline');
	
	$("#device-sort-power").height(($("#right_bot").get(0).offsetHeight-getHeight("classification-title"))+"px");
	
	$("#sort-air-pic").width(($("#sort-air-pic").get(0).offsetHeight)+"px");
	$("#sort-light-pic").width(($("#sort-light-pic").get(0).offsetHeight)+"px");
	$("#sort-energy-pic").width(($("#sort-energy-pic").get(0).offsetHeight)+"px");
	var pictop = ($("#device-sort-power").get(0).offsetHeight - $("#sort-air-pic").get(0).offsetHeight)/2;
	$("#sort-air-pic").css("top",""+pictop+"px");
	$("#sort-light-pic").css("top",""+pictop+"px");
	$("#sort-energy-pic").css("top",""+pictop+"px");
	
	////////////////////////////////////set Width
	$("#main-message").width(getHeight("main-message")*6/7+"px");
	$("#main-device-state").width(getHeight("main-device-state")*6/7+"px");
	$("#main-pic-power").width(getHeight("main-pic-power")*6/7+"px");
	

	///////////////////////////////////set LineHeight
	
}

////////////////////////////addbar
function addAllBar (height,attr){
	/////////////////////////////////    attr["counts"]   [0/1/2/3]:数组  0、1、2、3  照明  插座   空调   其他设备
	/////////////////////////////////    attr["usingCount"]   [0/1/2/3]:数组  0、1、2、3  照明  插座   空调   其他设备
	
	addDeviceBar('bar-1',"#FDE47C","#F9C446",height,attr["counts"][0],attr["usingCount"][0]);
	addDeviceBar('bar-2',"#77E8F3","#42CDE4",height,attr["counts"][2],attr["usingCount"][2]);
	addDeviceBar('bar-3',"#88D6B2","#50AC7B",height,attr["counts"][1],attr["usingCount"][1]);
	addDeviceBar('bar-4',"#F088AB","#DC5072",height,attr["counts"][3],attr["usingCount"][3]);
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
	window.location.href="/webpage/com/lierda/main/FloorHome.jsp?floorid="+id+"";
}

function addRightBot (attributes) {
	var lightRecordToday=parseFloat(attributes['stripRecordToday']).toFixed(2);
	var lightRecordYesterday=parseFloat(attributes['stripRecordYesterday']).toFixed(2);
	var conditionerRecordToday=parseFloat(attributes['conditionerRecordToday']).toFixed(2);
	var conditionerRecordYesterday=parseFloat(attributes['conditionerRecordYesterday']).toFixed(2);

	///////////////////////////////////////////////////////////////////空调用电
	$("#air-today-used").text(""+conditionerRecordToday+"");
	if (isNaN(conditionerRecordToday) || isNaN(conditionerRecordYesterday)) {
		console.log("===============================NaN");
		$("#air-today-used").text("-");
		$("#air-used-compare").text("-%");
		$("#air-used-comparepic").attr('src',"/images/lierda/main-icon/used.png");
	}
	else if (conditionerRecordYesterday < conditionerRecordToday) {
		console.log("====================================================up");
		$("#air-used-compare").text(""+(conditionerRecordToday/conditionerRecordYesterday*100-100).toFixed(0)+"%");
		$("#air-used-comparepic").attr('src',"/images/lierda/main-icon/usedup.png");
	}
	else if (conditionerRecordYesterday > conditionerRecordToday) {
		console.log("====================================================down"+((conditionerRecordYesterday-conditionerRecordToday)/conditionerRecordYesterday*100).toFixed(2));
		$("#air-used-compare").text(""+((conditionerRecordYesterday-conditionerRecordToday)/conditionerRecordYesterday*100).toFixed(0)+"%");
		$("#air-used-comparepic").attr('src',"/images/lierda/main-icon/useddown.png");
	}
	else {
		console.log("====================================================");
		$("#air-used-compare").text("0%");
		$("#air-used-comparepic").attr('src',"/images/lierda/main-icon/used.png");
	}
	
	///////////////////////////////////////////////////////////////////照明用电
	$("#light-today-used").text(""+lightRecordToday+"");
	if (isNaN(lightRecordToday) || isNaN(lightRecordYesterday)) {
		$("#light-today-used").text("-");
		$("#light-used-compare").text("-%");
		$("#light-used-comparepic").attr('src',"/images/lierda/main-icon/used.png");
	}
	else if (lightRecordYesterday < lightRecordToday) {
		$("#light-used-compare").text(""+(lightRecordToday/lightRecordYesterday*100-100).toFixed(0)+"%");
		$("#light-used-comparepic").attr('src',"/images/lierda/main-icon/usedup.png");
	}
	else if (lightRecordYesterday > lightRecordToday) {
		$("#light-used-compare").text(""+((lightRecordYesterday-lightRecordToday)/lightRecordYesterday*100).toFixed(0)+"%");
		$("#light-used-comparepic").attr('src',"/images/lierda/main-icon/useddown.png");
	}
	else {
		$("#light-used-compare").text("0%");
		$("#light-used-comparepic").attr('src',"/images/lierda/main-icon/used.png");
	}
	
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
	refreshBuilding(buildName);
}

function freshpage() {
	window.location.reload();
}

</script>


</html>