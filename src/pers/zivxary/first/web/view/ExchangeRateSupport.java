package pers.zivxary.first.web.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.zivxary.first.web.service.ExchageRateService;
import pers.zivxary.first.web.utils.DateUtil;

public class ExchangeRateSupport {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public ExchangeRateSupport(HttpServletRequest request, HttpServletResponse response) {
	this.request = request;
	this.response = response;
    }

    public String getDay() {
	String day = (String) request.getAttribute("day");
	day = (day == null) ? new DateUtil().getNowDay() : day;
	return day;
    }

    public String getTime() {
	String time = (String) request.getAttribute("time");
	time = (time == null) ? new DateUtil().getNowTime() : time;
	return time;
    }

    public String getInputDay() {
	String day = (String) request.getAttribute("inputDay");
	day = (day == null) ? new DateUtil().getNowDay() : day;
	return day;
    }

    public String getInputTime() {
	String time = (String) request.getAttribute("inputTime");
	time = (time == null) ? new DateUtil().getNowTime() : time;
	return time;
    }

    public String getVerifyResult() {
	String result = (String) request.getAttribute("verifyResult");
	return result == null ? "" : result;
    }

    public String getTable() {
	ExchageRateService service = new ExchageRateService();
	service.run(getDay(), getTime());
	ExchageRateTable table = new ExchageRateTable();

	return table.html(service.getJsonObject());
    }
}
