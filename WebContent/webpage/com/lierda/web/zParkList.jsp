<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=jYNU39RZ3k37NUz1QduizaYD"></script>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
 <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zParkList" title="园区管理" actionUrl="zParkController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="园区名称" field="parkname"   width="120"></t:dgCol>
   <t:dgCol title="经度" field="lon"   width="120"></t:dgCol>
   <t:dgCol title="纬度" field="lat"   width="120"></t:dgCol>
   <t:dgCol title="园区图" field="image"   width="120"></t:dgCol>
   <t:dgCol title="地图位置" field="map"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zParkController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="zParkController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zParkController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zParkController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>

<!-- 底部 -->
<div region="south" border="false" style="height: 300px; overflow: hidden;background-color: white;">
	<div border="false" style="height:100%;width:40%;float:left;left:4%;overflow: hidden;background-color: white;" id="map_nomal">
	
	</div>
	<div border="false" style="height:100%;width:40%;float:right;right:4%;overflow: hidden;background-color: white;" id="map_panorama">
	
	</div>
	<div border="false" style="height:100%;width:92%;position:absolute;left:4%;overflow: hidden;background-color: white;z-index:99;" id="map">
	
	</div>
</div>

<script type="text/javascript">
	
	$(document).ready(function(){
		
		$('#zParkList').datagrid({
			onClickRow: function(rowIndex, rowData){
				$('#map').prop("hidden",false);
				var lon = rowData.lon;
				var lat = rowData.lat;
				var mapOption = {
					mapType: BMAP_SATELLITE_MAP,
					minZo0om:1,
					maxZoom:21,
					drawMargin:0,
					disableDragging:true,
					disableScrollWheelZoom:true,
					disableDoubleClickZoom:true
				}
				var map = new BMap.Map("map",mapOption);    // 创建Map实例
				var point = new BMap.Point(lon, lat);
				map.centerAndZoom(point, 17);  // 初始化地图,设置中心点坐标和地图级别
				var marker=new BMap.Marker(point);
				map.addOverlay(marker);
				marker.addEventListener("click",function () {
					addpanorama (map,point);
				},false);
			}
		});
		
		function addpanorama (bigmap,point) {
			var haschanged=false;
			console.log(point);
			var mapOption = {
			        navigationControl:false,
			        linksControl:false,
			        disableScrollWheelZoom:true
			}
			var panorama = new BMap.Panorama('map_panorama',mapOption);
			panorama.setPosition(point);
			panorama.setPov({heading: -80, pitch: 20});

			panorama.addEventListener ("position_changed",function () {
				haschanged = true
			});
			
			setTimeout(function () {
				if (haschanged) {
					$('#map').prop("hidden",true);
					var mapOption = {
						mapType: BMAP_SATELLITE_MAP,
						minZo0om:1,
						maxZoom:21,
						drawMargin:0,
						disableDragging:true,
						disableScrollWheelZoom:true,
						disableDoubleClickZoom:true
					}
					var map = new BMap.Map("map_nomal",mapOption);    // 创建Map实例
					map.centerAndZoom(point, 17);  // 初始化地图,设置中心点坐标和地图级别
					var marker=new BMap.Marker(point);
					map.addOverlay(marker);
				}
				else {
					bigmap.setMapType(BMAP_NORMAL_MAP);
				}
			},1000*2);
			
		}
		
	});
	
</script>