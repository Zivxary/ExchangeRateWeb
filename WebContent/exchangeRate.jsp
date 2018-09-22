<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="pers.zivxary.first.web.view.ExchangeRateSupport"%>
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
	<% ExchangeRateSupport support = new ExchangeRateSupport(request, response); %>
	<form action="<%= request.getContextPath() + "/exchangeRateServlet"%>"
		method="post">
		<label for="day"> 日期：</label> 
		<input type="text" value="<%= support.getInputDay() %>" name="day" id="day" /> 
		<label for="time"> 時間：</label>
		<input type="text" value="<%= support.getInputTime() %>" name="time" id="time" /> 
		<input type="submit" value="提交" />
	</form>
	<br /><%= support.getVerifyResult() %>
	<br />
	<br />
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
			<%= support.getTable() %>
		</tbody>
	</table>
</body>
</html>
