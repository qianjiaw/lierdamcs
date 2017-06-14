function AllLightOn () {
	alert("AllLightOn");
}

function AllLightOff () {
	alert("AllLightOff");
}

function AllAirOn () {
	alert("AllAirOn");
}

function AllAirOff () {
	alert("AllAirOff");
}

function listenSenceChange () {
	$("#sence-lighton").mouseover(function(e){
		sencelighton();
	});
	$("#sence-lightoff").mouseover(function(e){
		sencelightoff();
	});
	$("#sence-airon").mouseover(function(e){
		sencelighton();
	});
	$("#sence-airoff").mouseover(function(e){
		sencelighton();
	});
	$("#sence-lighton").mouseout(function(e){
		sencemain();
	});
	$("#sence-lightoff").mouseout(function(e){
		sencemain();
	});
	$("#sence-airon").mouseout(function(e){
		sencemain();
	});
	$("#sence-airoff").mouseout(function(e){
		sencemain();
	});
}


function sencelighton () {
	$("#sence-pic").attr('src',"/images/lierda/main-icon/sence-one.png"); 
}
function sencelightoff () {
	$("#sence-pic").attr('src',"/images/lierda/main-icon/sence-one.png"); 
}
function senceairon () {
	$("#sence-pic").attr('src',"/images/lierda/main-icon/sence-one.png"); 
}
function senceairoff () {
	$("#sence-pic").attr('src',"/images/lierda/main-icon/sence-one.png"); 
}
function sencemain () {
	$("#sence-pic").attr('src',"/images/lierda/main-icon/sence-main.png"); 
}