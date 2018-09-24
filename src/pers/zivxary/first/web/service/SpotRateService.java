package pers.zivxary.first.web.service;

import java.io.IOException;

import pers.zivxary.first.web.connection.IRateConnection;
import pers.zivxary.first.web.connection.RateConnection;

public class SpotRateService implements ISpotRateSerivce {

    private static final String RATE_URL = "https://www.esunbank.com.tw/bank/Layouts/esunbank/Deposit/DpService.aspx/GetForeignExchageRate";

    private IRateConnection conn = null;

    public SpotRateService() {
	conn = new RateConnection(RATE_URL);
    }

    @Override
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
    @Override
    public void setParameters(String day, String time) {
	String parameter = "{day:\'" + day + "\',time:\'" + time + "\'}";
	conn.setParameter(parameter);
    }

}
