//////////////////////////////////////////////////add Room Table
function addtable (type,devices) {
	
	$("#room-table-tr").empty();
	$("#room-table-trdetail").empty();
	$("#room-device-body").empty();
	
	if ("1"==type) {
		
		$("#room-table-tr").append(
			'<th id="room-device-th1" class="room-device-th th1" rowspan="2">序号'+
				'<button class="unclickth" onclick="changethstate(this)" id="0-0" type="number"></button>'+
			'</th>'+
			'<th id="room-device-th2" class="room-device-th th2" rowspan="2">设备编号'+
				'<button class="unclickth" onclick="changethstate(this)" id="1-0" type="number"></button>'+
			'</th>'+
			'<th id="room-device-th3" class="room-device-th th3" rowspan="2">调光属性'+
				'<button class="unclickth" onclick="changethstate(this)" id="2-0"></button>'+
			'</th>'+
			'<th id="room-device-th4" class="room-device-th th4" rowspan="2">隶属于'+
				'<button class="unclickth" onclick="changethstate(this)" id="3-0"></button>'+
			'</th>'+
			'<th id="room-device-th5" class="room-device-th th5" colspan="3">设备状态'+
				'<button class="unclickth" onclick="changethstate(this)" id="4-0"></button>'+
			'</th>'+
			'<th id="room-device-th6" class="room-device-th th6" rowspan="2">控制操作'+
				'<button class="unclickth" onclick="changethstate(this)" id="5-0"></button>'+
			'</th>'
		);
		$("#room-table-trdetail").append(
			'<th class="room-device-th th5-1">开关</th>'+
			'<th class="room-device-th th5-2">调光</th>'+
			'<th class="room-device-th th5-3">调色</th>'
		);
		
		for (var i=0;i<devices.length;i++) {
			var device = JSON.parse(devices[i].attributes);
			$("#room-device-body").append(
				'<tr style="height:50px;">'+
				'<td>'+(i+1)+'</td>'+
				'<td>'+device['MAC']+'</td>'+
				'<td>可调</td>'+
				'<td>'+devices[i].ddcmac+'</td>'+
				'+<td><div class="device-state-'+device['SWI']+'"></div></td> <td>'+device['LEV']+'</td> <td>'+device['CTM']+'</td>'+
				'<td></td>'+
				'</tr>'
			);
		}
	}
	
	if ("26"==type) {
		
	}
	
	if ("19"==type) {
		
	}

	if ("15"==type) {
		
	}

	if ("5"==type) {
		
	}

	if ("7"==type) {
		
	}

	if ("13"==type) {
		
	}

	if ("18"==type) {
		
	}
		
}