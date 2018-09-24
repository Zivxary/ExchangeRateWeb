package pers.zivxary.first.web.service;

import java.io.IOException;

import pers.zivxary.first.web.connection.RateConnection;

public class ChartRateService {

    private static final String RATE_URL = "https://www.esunbank.com.tw/bank/Layouts/esunbank/Deposit/DpService.aspx/GetLineChartJson";

    private RateConnection conn;

    public ChartRateService() {
	conn = new RateConnection(RATE_URL);
    }

    public String getData() {
	try {
	    conn.run();
	} catch (IOException e) {
	    e.printStackTrace();
	    return "";
	}
	return conn.getResult();
    }

    // 設置要POST的參數
    public void setParameters(String currency, String startDate, String endDate) {
	// String parameter = "{data:{\"Currency\":\" + currency + \",\"Startdate\":\" +
	// startDate + \",\"Enddate\":\" + endDate + \"}}";
	String parameter = "{data:{\"Currency\":\"CNY\",\"Currencytype\":\"1\",\"Rangetype\":\"0\",\"Startdate\":\"2017-09-24\",\"Enddate\":\"2018-09-24\"}}";
	conn.setParameter(parameter);
    }

}
