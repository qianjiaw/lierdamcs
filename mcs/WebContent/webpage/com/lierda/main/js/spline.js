

function addSpline (divid) {
	Highcharts.setOptions({
	    global: {
	        useUTC: false
	    }
	});
	var chart = new Highcharts.Chart(divid, {
	    chart: {
	        type: 'spline',
	        animation: Highcharts.svg, // don't animate in old IE
	        marginRight: 10,
	        events: {
	            load: function () {
	                // set up the updating of the chart each second
	                var series1 = this.series[0],
	                	series2 = this.series[1],
	                	series3 = this.series[2],
	                    chart = this;
	                setInterval(function () {
	                    var x = (new Date()).getTime(), // current time
	                        y1 = Math.random(),
	                        y2 = Math.random(),
	                        y3 = Math.random();
	                    series1.addPoint([x, y1], true, true);
	                    series2.addPoint([x, y2], true, true);
	                    series3.addPoint([x, y3], true, true);
	                }, 1000*60*60);
	            }
	        }
	    },
	    credits : {
			enabled : false
		},
	    title: {
	        text: ''
	    },
	    legend: {
	        align: 'center',
	        verticalAlign: 'top',
	        borderWidth: 0
	    },
	    xAxis: {
	        type: 'datetime',
	        tickPixelInterval: 40
	    },
	    yAxis: {
	        title: {
	            text: '值'
	        },
	        plotLines: [{
	            value: 0,
	            width: 1,
	            color: '#808080'
	        }]
	    },
	    tooltip: {
	        formatter: function () {
	            return '<b>' + this.series.name + '</b><br/>' +
	                Highcharts.dateFormat('%Y-%m-%d %H:%M', this.x) + '<br/>' +
	                Highcharts.numberFormat(this.y, 2);
	        }
	    },
	    exporting: {
	        enabled: false
	    },
	    series: [{
	        name: '照明',
	        data: (function () {
	            // generate an array of random data
	            var data = [],
	                time = (new Date()).getTime(),
	                i;
	            for (i = -19; i <= 0; i += 1) {
	                data.push({
	                    x: time + i * 1000*60*60,
	                    y: Math.random()
	                });
	            }
	            return data;
	        }())
	    },{
	        name: '空调',
	        data: (function () {
	            // generate an array of random data
	            var data = [],
	                time = (new Date()).getTime(),
	                i;
	            for (i = -19; i <= 0; i += 1) {
	                data.push({
	                	x: time + i * 1000*60*60,
	                    y: Math.random()
	                });
	            }
	            return data;
	        }())
	    },{
	        name: '插座',
	        data: (function () {
	            // generate an array of random data
	            var data = [],
	                time = (new Date()).getTime(),
	                i;
	            for (i = -19; i <= 0; i += 1) {
	                data.push({
	                	x: time + i * 1000*60*60,
	                    y: Math.random()
	                });
	            }
	            return data;
	        }())
	    }]
	});
}


