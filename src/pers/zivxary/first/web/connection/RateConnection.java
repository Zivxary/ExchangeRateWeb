package pers.zivxary.first.web.connection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RateConnection implements IRateConnection {

    private String urlstring;
    private String parameter = "";
    private String result = "";

    private Map<String, String> propertys = new HashMap<String, String>();

    public RateConnection(String url) {
	urlstring = url;
	initPropertys();
    }

    private void initPropertys() {
	propertys.put("Content-Type", "application/json; charset=UTF-8");
	propertys.put("Referer", "https://www.esunbank.com.tw/bank/personal/deposit/rate/forex/foreign-exchange-rates");
	propertys.put("Content-Length", String.valueOf(parameter.length()));
    }

    @Override
    public String getResult() {
	return result;
    }

    @Override
    public void setParameter(String parameter) {
	this.parameter = parameter;
    }

    @Override
    public void setPropertys(String key, String value) {
	propertys.put(key, value);
    }

    @Override
    public void run() throws MalformedURLException, IOException {
	HttpURLConnection conn = null;
	try {
	    if (parameter == "") {
		throw new NullPointerException("empty parameter");
	    }
	    conn = getHttpURLConnection(new URL(urlstring));
	    sendPost(conn, parameter);
	    result = getResult(conn);
	} finally {
	    parameter = "";
	    if (conn != null) {
		conn.disconnect();
	    }
	}
    }

    private HttpURLConnection getHttpURLConnection(URL url) throws IOException {
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	conn.setRequestMethod("POST");
	for (String key : propertys.keySet()) {
	    conn.setRequestProperty(key, propertys.get(key));
	}
	return conn;
    }

    private void sendPost(HttpURLConnection conn, String parameter) throws IOException {
	conn.setDoOutput(true);
	try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
	    wr.writeBytes(parameter);
	    wr.flush();
	}
    }

    private String getResult(HttpURLConnection conn) throws IOException {
	StringBuilder sb = new StringBuilder();
	try (BufferedReader in = getBufferedReader(conn)) {
	    String inputLine = null;
	    while ((inputLine = in.readLine()) != null) {
		sb.append(inputLine);
	    }
	}
	return sb.toString();
    }

    private BufferedReader getBufferedReader(HttpURLConnection conn) throws IOException {
	return new BufferedReader(new InputStreamReader(conn.getInputStream()));
    }

}