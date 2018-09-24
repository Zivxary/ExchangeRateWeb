<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="pers.zivxary.first.web.view.ChartRateView"%>
<%@page import="pers.zivxary.first.web.view.SpotRateView"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
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
	<script src="<%= request.getContextPath() + "/js/Chart.bundle.min.js" %>" type="text/javascript"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" type="text/javascript"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/locale/af.js" type="text/javascript"></script>
	
	<div style="position: relative; height:1200px; width:1000px">
		<canvas id="myChart"></canvas>
	</div>
	<script src="<%= request.getContextPath() + "/js/PrintChart.js" %>" type="text/javascript"></script>
	<script>
		var dates = <%= chartRateView.getDates() %> ;
		var buyRates = <%= chartRateView.getBuyRate() %>;
		var sellRates = <%= chartRateView.getSellRate() %>;
		var ctx = document.getElementById('myChart').getContext('2d');
		var chart = new Chart(ctx, setting(dates,buyRates,sellRates));
	</script>
	<br />
	<br /><%= chartRateView.getDates() %>
</body>
</html>
