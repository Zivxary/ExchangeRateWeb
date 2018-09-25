package pers.zivxary.first.web.service;

import java.io.IOException;

import pers.zivxary.first.web.connection.RateConnection;
import pers.zivxary.first.web.type.CurrencyType;
import pers.zivxary.first.web.type.RateType;

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
    public void setParameters(CurrencyType currencyType, RateType rateType, String startDate, String endDate) {

	String parameter = "{data:{\"Currency\":\"" + currencyType.name() + "\",\"Currencytype\":\"" + rateType.getId()
		+ "\",\"Rangetype\":\"0\",\"Startdate\":\"" + startDate + "\",\"Enddate\":\"" + endDate
		+ "\",\"CurrencyTitle\":\"" + currencyType.getFullName() + "\"}}";

	conn.setParameter(parameter);
    }

}
