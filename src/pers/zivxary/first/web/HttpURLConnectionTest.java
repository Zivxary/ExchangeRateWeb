package pers.zivxary.first.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionTest {

    public String print() {
	String output = "";
	try {
	    output = sendPost();
	} catch (IOException e) {
	    e.printStackTrace();
	    output = "發送出錯";
	}
	return output;
    }

    private String sendPost() throws IOException {
	String sUrl = "https://www.esunbank.com.tw/bank/Layouts/esunbank/Deposit/DpService.aspx/GetForeignExchageRate";
	URL url = new URL(sUrl);
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	conn.setRequestMethod("POST");
	conn.setRequestProperty("Content-Length", "34");
	conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	conn.setRequestProperty("Referer",
		"https://www.esunbank.com.tw/bank/personal/deposit/rate/forex/foreign-exchange-rates");

	String urlParameters = "{day:\'2018-09-19\',time:\'15:29:26\'}";

	conn.setDoOutput(true);
	DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
	wr.writeBytes(urlParameters);
	wr.flush();
	wr.close();

	StringBuilder sb = new StringBuilder();

	int responseCode = conn.getResponseCode();
	sb.append("<br>Sending 'POST' request to URL : " + sUrl);
	sb.append("<br>Post parameters : " + urlParameters);
	sb.append("<br>Response Code : " + responseCode);

	BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	String inputLine;

	while ((inputLine = in.readLine()) != null) {
	    sb.append("<br>" + inputLine);
	}
	in.close();

	return sb.toString();
    }
}
