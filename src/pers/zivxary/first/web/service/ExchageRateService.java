package pers.zivxary.first.web.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import pers.zivxary.first.web.utils.JsonFormat;

public class ExchageRateService {

    private static final String EXCHAGE_RATE_URL = "https://www.esunbank.com.tw/bank/Layouts/esunbank/Deposit/DpService.aspx/GetForeignExchageRate";

    private String postInfo = "";
    private String jsonString = "";

    // 取得發送資訊
    public String getPostInfo() {
	return postInfo;
    }

    // 取得Json格式化字串
    public String getJsonString() {
	return new JsonFormat().format(jsonString);
    }

    // 取得JSONObject實例
    public JSONObject getJsonObject() {
	return new JSONObject(jsonString);
    }

    // 回傳 發送資料 + 回應的JSON格式化字串
    public String run(String day, String time) {
	String output = "";
	HttpURLConnection conn = null;
	try {
	    conn = getHttpURLConnection();
	    String parameters = getParameters(day, time);
	    sendPost(conn, parameters);
	    postInfo = getPostInfo(conn, parameters);
	    jsonString = getJsonString(conn);

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
	sdFormat.applyPattern("HH:mm:ss");
	String time = sdFormat.format(date);
	return getParameters(day, time);
    }

    // 取得要POST的參數
    private String getParameters(String day, String time) {
	return "{day:\'" + day + "\',time:\'" + time + "\'}";
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

    // 回應的Json資料
    private String getJsonString(HttpURLConnection conn) throws IOException {
	StringBuilder sb = new StringBuilder();
	try (BufferedReader in = getBufferedReader(conn)) {

	    String inputLine = null;
	    while ((inputLine = in.readLine()) != null) {
		sb.append(inputLine);
	    }
	}
	return sb.toString();
    }

    // 取得讀取流
    private BufferedReader getBufferedReader(HttpURLConnection conn) throws IOException {
	return new BufferedReader(new InputStreamReader(conn.getInputStream()));
    }

}
