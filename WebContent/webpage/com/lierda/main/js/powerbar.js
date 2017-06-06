function addPowerBar (divid) {
	var myChart = echarts.init(document.getElementById(divid));
option = {
    title : {
        text: '设备时功耗（KW·h）',
        subtext: '假数据'
    },

    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        data:['照明','空调','插座']
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
            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        },
        {
            name:'空调',
            type:'bar',
            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
            markLine : {
                data : [
                    {type : 'average', name : '平均值'}
                ]
            }
        },
        {
            name:'插座',
            type:'bar',
            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 15.6, 12.2, 48.7, 1.8, 6.0, 2.3],
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