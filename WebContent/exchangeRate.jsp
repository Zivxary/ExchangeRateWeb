<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="pers.zivxary.first.web.ExchageRateTable"%>
<%@page import="pers.zivxary.first.web.PostExchageRate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<% 
		PostExchageRate postExchageRate = new PostExchageRate(); 
		postExchageRate.run();
	%>
	<% ExchageRateTable table = new ExchageRateTable(); %>
	<%= postExchageRate.getPostInfo() %>
	<table border="1">
		<thead>
			<tr>
				<th rowspan="2">幣別</th>
				<th colspan="2">即期匯率</th>
				<th colspan="2">網路銀行/APP優惠匯率</th>
				<th colspan="2">現金匯率</th>
			</tr>
			<tr>
				<th>銀行買入</th>
				<th>銀行賣出</th>
				<th>銀行買入</th>
				<th>銀行賣出</th>
				<th>銀行買入</th>
				<th>銀行賣出</th>
			</tr>
		</thead>
		<tbody>
			
			<%= table.html(postExchageRate.getJsonObject()) %>
		</tbody>
	</table>
	<%= postExchageRate.getJsonString() %>
</body>
</html>