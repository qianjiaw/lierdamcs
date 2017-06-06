

function addDeviceBar (divid,height) {
	////////////////////need to getdata from db
	var total = 1502;
	/*var data = ***********************dbdata*********************;*/
	////////////////////need to get data from jsp
	
	var chart = new Highcharts.Chart(
			divid,
			{
				chart : {
					plotBackgroundColor : null,
					plotBorderWidth : null,
					plotShadow : false,
				},
				credits : {
					enabled : false
				},
				title : {
					floating : true,
					text : '已使用比例',
					style : {
						color : '#FF00FF',
						fontWeight : 'bold',
						textAlign : 'center',
						fontSize : height/10+'px'
					}
				},
				subtitle : {
					floating : true,
					text : total,
					style : {
						color : '#FF00FF',
						textAlign : 'center',
						fontSize : '12px',
					},
	                verticalAlign:'top',
					x : 0,
					y : 5
				},
				plotOptions : {
					pie : {
						dataLabels : {
							enabled : false,
						},
						allowPointSelect : true,
						cursor : 'pointer',
						point : {
							events : {
								mouseOver : function(e) {
									chart.setTitle({
										text : '\t'
												+ (e.target.y * 100 / total)
														.toFixed(0) + '%'
									});
								}
							}
						},
					}
				},
				series : [ {
					type : 'pie',
					innerSize : '90%',
					name : '设备状态',
					data : [ [ '已使用', 1306 ], [ '未使用', 196 ] ]
				} ]
			},
			function(c) {
				// 环形图圆心
				var centerY = c.series[0].center[1],
				titleHeight = parseInt(c.title.styles.fontSize);
				c.setTitle({
					y : centerY + titleHeight / 2
				});
			}
	);
}
