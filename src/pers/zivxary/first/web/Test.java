package pers.zivxary.first.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test {

    public String printRequest(HttpServletRequest request) {

	Enumeration<?> headerNames = request.getHeaderNames();
	StringBuilder stringBuilder = new StringBuilder();

	while (headerNames.hasMoreElements()) {
	    String paramName = (String) headerNames.nextElement();
	    stringBuilder.append("<tr><td>" + paramName + "</td>\n");
	    String paramValue = request.getHeader(paramName);
	    stringBuilder.append("<td> " + paramValue + "</td></tr>\n");
	    stringBuilder.append("\r\n");
	}

	return stringBuilder.toString();
    }

    public String printHttpServletResponse(HttpServletResponse response) {

	return "";
    }
}
