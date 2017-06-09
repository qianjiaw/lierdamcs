

function addDeviceBar (divid,colorstart,colorend,height,total,using) {
	
	var title = '已使用比例';
	
	////////////////////need to getdata from db
	var Number_total = parseInt(total);
	var Number_using = parseInt(using);
	var Number_unusing = Number_total-Number_using;
	
	if (Number_total == 0) {
		title = '无该设备';
	}
	
	 Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function (color) {
	        return {
	            radialGradient: { cx: 0.5, cy: 0.3, r: 0.5 },
	            stops: [
	                [0.5, colorstart],
	                [0.75, colorend] // darken
	            ]
	        };
	    });
	
	var chart = new Highcharts.Chart(
			divid,
			{
				chart : {
					backgroundColor:'#fafbff',
					plotBackgroundColor : '#fafbff',
					plotBorderWidth : null,
					plotShadow : false,
					height:height
				},
				credits : {
					enabled : false
				},
				title : {
					floating : true,
					text : title,
					style : {
						color : colorend,
						fontWeight : 'bold',
						textAlign : 'center',
						fontSize : height/10+'px'
					}
				},
				subtitle : {
					floating : true,
					text : Number_total,
					style : {
						color : colorend,
						textAlign : 'center',
						fontSize : height/10+'px'
					},
	                verticalAlign:'center',
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
												+ (Number_using * 100 / Number_total)
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
					data : [ 
						{
							name:'已使用',
							y:Number_using
						},
						{
							name:'未使用',
							y:Number_unusing,
							color:'#F3F3F3'
						}
					]
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
