<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="pers.zivxary.first.web.view.ChartRateView"%>
<%@page import="pers.zivxary.first.web.view.SpotRateView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>即期匯率</title>
</head>
<body>
	<br />
	<%
	    SpotRateView spotRateView = new SpotRateView(session);
	%>
	<form action="<%= request.getContextPath() + "/SpotRateServlet" %>" method="post">
		<label for="day"> 日期：</label> 
		<input type="text" value="<%= spotRateView.getInputDay() %>" name="day" id="day" /> 
		<label for="time"> 時間：</label>
		<input type="text" value="<%= spotRateView.getInputTime() %>" name="time" id="time" /> 
		<input type="submit" value="提交" />
	</form>
	<br /><%= spotRateView.getSpotVerifyResult()%>
	<br />
	<br /><%= spotRateView.getTableTime() %>
	<table border="1">
		<thead>
			<tr>
				<th rowspan="2">幣別</th>
				<th colspan="2">即期匯率</th>
				<th colspan="2">網路銀行/APP優惠匯率</th>
				<th colspan="2">現金匯率</th>
			</tr>
			<tr>
				<td>銀行買入</td>
				<td>銀行賣出</td>
				<td>銀行買入</td>
				<td>銀行賣出</td>
				<td>銀行買入</td>
				<td>銀行賣出</td>
			</tr>
		</thead>
		<tbody>
			<%= spotRateView.getTable() %>
		</tbody>
	</table>
	<br />
	<br />
	<br />
	<%
		ChartRateView chartRateView = new ChartRateView(session);
	%>
	<form action="<%= request.getContextPath() + "/ChartRateServlet" %>" method="post">
		<select name="currency">
			<option value="USD">美元(USD)</option>
			<option value="CNY">人民幣(CNY)</option>
			<option value="HKD">港幣(HKD)</option>
			<option value="JPY">日圓(JPY)</option>
			<option value="EUR">歐元(EUR)</option>
			<option value="AUD">澳幣(AUD)</option>
			<option value="CAD">加拿大幣(CAD)</option>
			<option value="GBP">英鎊(GBP)</option>
			<option value="ZAR">南非幣(ZAR)</option>
			<option value="NZD">紐西蘭幣(NZD)</option>
			<option value="CHF">瑞士法郎(CHF)</option>
			<option value="SEK">瑞典幣(SEK)</option>
			<option value="SGD">新加坡幣(SGD)</option>
			<option value="MXN">墨西哥披索(MXN)</option>
			<option value="THB">泰銖(THB)</option>
		</select>
		<input type="radio" name="rateType" value="spot" checked/>即期
		<input type="radio" name="rateType" value="cash" />現金
		<br /><br />
		<label for="toDay"> 開始日期：</label> 
		<input type="text" value="<%=  chartRateView.getInputFromDay() %>" name="fromDay" id="fromDay" />
		<label for="fromDay"> 結束日期：</label> 
		<input type="text" value="<%=  chartRateView.getInputToDay() %>" name="toDay" id="toDay" /> 
		<input type="submit" value="提交" />
	</form>
	<br /><%= chartRateView.getChartVerifyResult()%>
	<br />
	<script src="<%= request.getContextPath() + "/js/Chart.bundle.min.js" %>"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<div style="position: relative; height:600px; width:600px">
		<canvas id="myChart"></canvas>
	</div>
	<script>
	var ctx = document.getElementById('myChart').getContext('2d');
	var ChartDefault = Chart.defaults.global;
	ChartDefault.defaultFontFamily = 
		"'微軟正黑體','Microsoft JhengHei','Helvetica Neue', 'Helvetica', 'Arial', sans-serif";
	ChartDefault.elements.point.pointStyle = 'rectRot';
	ChartDefault.elements.point.borderWidth = 6;
	ChartDefault.elements.point.hitRadius = 10;
	ChartDefault.elements.point.hoverRadius = 6;
	var chart = new Chart(ctx, {

    	type: 'line',

    	data:
    	{
        	labels: ["January", "February", "March", "April", "May", "June", "July"],
        	yAxisID: "美元即期",
        	datasets: 
        	[
        		{
            		label: "買匯",
            		backgroundColor: 'rgba(255, 255, 255, 0)',
            		borderColor: 'rgb(0, 0, 255)',
            		data: [23,30.5,31,28,26,24]
        		},
        		{
            		label: "賣匯",
            		backgroundColor: 'rgba(255, 255, 255, 0)',
            		borderColor: 'rgb(255, 0, 0)',
            		data: [25,30,29,33,24,28]
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
    			fullWidth: true,
    			//onClick
    			//onHover
    			reverse: false,
    			labels:
    			{
    				boxWidth: 40,
    				fontSize: 12,
    				fontStyle: 'normal',
    				fontColor: '#666',
    				//fontFamily: "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif",
    				padding: 10,
    				//generateLabels
    				filter: null,
    				usePointStyle: true
    			}
    		},
    		title: 
    		{
    			display: true,
    			position: 'top', //left, bottom, right
    			fontSize: 20,
    			//fontFamily: "'微軟正黑體','Microsoft JhengHei','Helvetica Neue', 'Helvetica', 'Arial', sans-serif",
    			fontColor: '#666',
    			fontStyle: 'bold',
    			padding: 5,
    			//lineHeight: 1.5,
    			text: '美元匯率'
    		},
    		scales:
    		{
    			yAxes:
    			[
    				{
    					scaleLabel: 
    					{
    						display: true,
    						labelString: '美元即期',
    						//fontFamily: "'微軟正黑體','Microsoft JhengHei','Helvetica Neue', 'Helvetica', 'Arial', sans-serif",
    					}
    				}
    			]
    		}
    	}
	});
	</script>
</body>
</html>
