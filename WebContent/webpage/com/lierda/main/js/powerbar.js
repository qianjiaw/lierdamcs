function addPowerBar (divid,ajaxdata) {
	var myChart = echarts.init(document.getElementById(divid));
	var lightdata = ajaxdata["1"];
	var airdata = ajaxdata["15"];
	var socketdata = ajaxdata["26"];
	
	
option = {
    title : {
        text: '设备时功耗（KW·h）',
        subtext: 'data from www.lierdalux.cn',
        left:'15',
        top:'5'
    },

    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        data:['照明','空调','插座'],
        top:'20',
        left:'right'
    },
    calculable : true,
    dataZoom:[{
        type:'slider',
        start:0,
        end:100
    }],
    xAxis : [
        {
            type : 'category',
            data : ['0:00-1:00','1:00-2:00','2:00-3:00','3:00-4:00','4:00-5:00','5:00-6:00','6:00-7:00','7:00-8:00','8:00-9:00','9:00-10:00','10:00-11:00','11:00-12:00','12:00-13:00','13：00-14：00','14：00-15：00','15：00-16：00','16：00-17：00','17：00-18：00','18：00-19：00','19：00-20：00','20：00-21：00','21：00-22：00','22：00-23：00','23：00-24：00']
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'照明',
            type:'bar',
            data:lightdata,
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        },
        {
            name:'空调',
            type:'bar',
            data:airdata,
            markLine : {
                data : [
                    {type : 'average', name : '平均值'}
                ]
            }
        },
        {
            name:'插座',
            type:'bar',
            data:socketdata,
            markLine : {
                data : [
                    {type : 'average', name : '平均值'}
                ]
            }
        }
    ]
};


myChart.setOption(option);

}

function resizePowerChart (divid) {
	var myChart = echarts.getInstanceByDom(document.getElementById(divid));
	myChart.resize();
}