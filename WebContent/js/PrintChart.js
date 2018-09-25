
var ChartDefault = Chart.defaults.global;
ChartDefault.defaultFontFamily = 
	"'微軟正黑體','Microsoft JhengHei','Helvetica Neue', 'Helvetica', 'Arial', sans-serif";
var defaultPoint = ChartDefault.elements.point;
defaultPoint.pointStyle = 'rectRot';
defaultPoint.radius = 1;
defaultPoint.borderWidth = 4;
defaultPoint.hoverRadius = 5;		
var setting = function(dates,buyRates,sellRates,currency,rateType) {
	return {
		    	type: 'line',
		    	data:
		    	{
		        	labels: dates,
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
		    		responsive: true,
		    		maintainAspectRatio: false,
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
		    			text: currency + '匯率'
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
		    						labelString: currency + rateType,
		    					}
		    				}
		    			]
		    		}
		    	}
	};
}
		
		