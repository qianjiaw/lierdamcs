function drawPowerChart () {
	var myChart = echarts.init(document.getElementById('room-power'));
	var symbolSize = 10;

option = {
    title: {
        text: 'LOREM IPSUM DOLOR SIT AMET',
        textStyle:{
        	fontSize:10
        }
    },
    tooltip: {
        trigger: 'axis'
    }, 
    grid: {
	    top:'25',
    	bottom:'5',
        left: '0',
        containLabel: true
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        splitLine:{
        	show:true
        },
        data: ['2010', '2011', '2012', '2013', '2014', '2015','2016','2017']
    },
    yAxis: {
        type: 'value'
    },
    series: [{
        id: 'a',
        name: 'Current Year',
        type: 'line',
        smooth: true,
        symbolSize: symbolSize,
        data: [
	    	['2010', 12],
	    	['2011', 20],
	    	['2012', 18],
	    	['2013', 27],
	    	['2014', 30],
	    	['2015', 18],
	    	['2016', 35],
	    	['2017', 30]
		]
    }, {
        name: 'Last Year',
        type: 'line',
        smooth: true,
        symbolSize: symbolSize,
        data: [
	    	['2010', 15],
	    	['2011', 27],
	    	['2012', 26],
	    	['2013', 48],
	    	['2014', 53],
	    	['2015', 38],
	    	['2016', 50],
	    	['2017', 52]
		]
    }, {
        name: 'Avg. Last 3 Months',
        type: 'line',
        smooth: true,
        symbolSize: symbolSize,
        data: [
	    	['2010', 27],
	    	['2011', 45],
	    	['2012', 45],
	    	['2013', 55],
	    	['2014', 72],
	    	['2015', 50],
	    	['2016', 70],
	    	['2017', 65]
		]
    }, {
        name: 'Avg. Last 6 Months',
        type: 'line',
        smooth: true,
        symbolSize: symbolSize,
        data: [
	    	['2010', 48],
	    	['2011', 65],
	    	['2012', 53],
	    	['2013', 67],
	    	['2014', 80],
	    	['2015', 55],
	    	['2016', 77],
	    	['2017', 76]
		]
    }]
};

 myChart.setOption(option);

}

function resizePowerChart () {
	var myChart = echarts.getInstanceByDom(document.getElementById('room-power'));
	myChart.resize();
}