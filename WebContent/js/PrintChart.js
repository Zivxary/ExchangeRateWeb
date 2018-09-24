
var ChartDefault = Chart.defaults.global;
ChartDefault.defaultFontFamily = 
	"'微軟正黑體','Microsoft JhengHei','Helvetica Neue', 'Helvetica', 'Arial', sans-serif";
var defaultPoint = ChartDefault.elements.point;
defaultPoint.pointStyle = 'rectRot';
defaultPoint.radius = 1;
defaultPoint.borderWidth = 4;
defaultPoint.hitRadius = 1;
defaultPoint.hoverRadius = 5;
		
var setting = function(dates,buyRates,sellRates) {
	return {
		    	type: 'line',
		    	data:
		    	{
		        	labels: dates,
		        	yAxisID: "美元即期",
		        	datasets: 
		        	[
		        		{
		            		label: "買匯",
		            		backgroundColor: 'rgba(255, 255, 255, 0)',
		            		borderColor: 'rgb(0, 0, 255)',
		            		data: buyRates
		        		},
		        		{
		            		label: "賣匯",
		            		backgroundColor: 'rgba(255, 255, 255, 0)',
		            		borderColor: 'rgb(255, 0, 0)',
		            		data: sellRates
		        		}
		        	]
		    	},
		    	options: 
		    	{
		    		tooltips:
		    		{
		    			mode: 'point'
		    		},
		    		legend: 
		    		{
		    			display: true,
		    			position: 'bottom',
		    			reverse: false,
		    			labels:
		    			{
		    				boxWidth: 40,
		    				fontSize: 12,
		    				usePointStyle: true
		    			}
		    		},
		    		title: 
		    		{
		    			display: true,
		    			fontSize: 20,
		    			padding: 5,
		    			text: '美元匯率'
		    		},
		    		scales:
		    		{
		    			xAxes: [{
		    				type: 'time',
		                    time: {parser: 'YYYY-MM-DD'}
		                }],
		    			yAxes:
		    			[
		    				{
		    					scaleLabel: 
		    					{
		    						display: true,
		    						labelString: '美元即期',
		    					}
		    				}
		    			]
		    		}
		    	}
	};
}
		
		