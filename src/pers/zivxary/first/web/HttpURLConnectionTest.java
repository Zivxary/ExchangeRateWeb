package pers.zivxary.first.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HttpURLConnectionTest {

    private static final String EXCHAGE_RATE_URL = "https://www.esunbank.com.tw/bank/Layouts/esunbank/Deposit/DpService.aspx/GetForeignExchageRate";

    // 回傳 發送資料 + 回應的JSON格式化字串
    public String print() {
	String output = "";
	HttpURLConnection conn = null;
	try {
	    conn = getHttpURLConnection();
	    String parameters = getParameters();
	    output = getResponse(conn, parameters);

	} catch (IOException e) {
	    e.printStackTrace();
	    output = "發送出錯";

	} finally {
	    if (conn != null) {
		conn.disconnect();
	    }
	}
	return output;
    }

    // 取得已設定好的HttpURLConnection
    private HttpURLConnection getHttpURLConnection() throws IOException {
	URL url = new URL(EXCHAGE_RATE_URL);
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	conn.setRequestMethod("POST");
	conn.setRequestProperty("Content-Length", "34");
	conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	conn.setRequestProperty("Referer",
		"https://www.esunbank.com.tw/bank/personal/deposit/rate/forex/foreign-exchange-rates");
	return conn;
    }

    // 取得要POST的參數
    private String getParameters() {
	Date date = new Date();
	SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
	String day = sdFormat.format(date);
	sdFormat.applyPattern("hh:mm:ss");
	String time = sdFormat.format(date);
	return "{day:\'" + day + "\',time:\'" + time + "\'}";
    }

    // POST並取得回應
    private String getResponse(HttpURLConnection conn, String parameters) throws IOException {

	sendPost(conn, parameters);
	String info = getPostInfo(conn, parameters);
	String jsonString = getJsonString(conn);

	return info + "<br>" + jsonString;
    }

    // 發送
    private void sendPost(HttpURLConnection conn, String parameters) throws IOException {
	conn.setDoOutput(true);
	try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
	    wr.writeBytes(parameters);
	    wr.flush();
	}
    }

    // POST的資訊
    private String getPostInfo(HttpURLConnection conn, String parameters) throws IOException {
	StringBuilder sb = new StringBuilder();

	int responseCode = conn.getResponseCode();
	sb.append("<br>Sending 'POST' request to URL : " + EXCHAGE_RATE_URL);
	sb.append("<br>Post parameters : " + parameters);
	sb.append("<br>Response Code : " + responseCode);
	return sb.toString();
    }

    // 回應的Json資料 + 格式化
    private String getJsonString(HttpURLConnection conn) throws IOException {
	StringBuilder sb = new StringBuilder();
	try (BufferedReader in = getBufferedReader(conn)) {

	    String inputLine = null;
	    while ((inputLine = in.readLine()) != null) {
		sb.append(inputLine);
	    }
	}
	return new JsonFormat().format(sb.toString());
    }

    // 取得讀取流
    private BufferedReader getBufferedReader(HttpURLConnection conn) throws IOException {
	return new BufferedReader(new InputStreamReader(conn.getInputStream()));
    }

}
