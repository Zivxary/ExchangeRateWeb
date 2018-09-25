package pers.zivxary.first.web.view;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONObject;

public class ChartRateJson {

    private JSONObject base;

    private JSONArray rateArray;

    public ChartRateJson(String data) {
	base = new JSONObject(new JSONObject(data).getString("d"));
	rateArray = base.getJSONArray("Rates");
    }

    public String getFormatJson() {
	String s1 = base.getJSONObject("Line").getJSONArray("series").getJSONObject(0).getJSONArray("data")
		.getJSONArray(0).getDouble(1) + "";
	String s2 = base.getJSONObject("Line").getJSONArray("series").toString();
	return s1 + "<br /><br />" + s2;
    }

    public String getBuyRates() {
	return getRates("BuyRate");
    }

    public String getSellRates() {
	return getRates("SellRate");
    }

    private String getRates(String key) {
	JSONArray array = rateArray;
	return IntStream.range(0, array.length()).mapToObj(i -> String.valueOf(array.getJSONObject(i).getFloat(key)))
		.collect(Collectors.joining(",", "[", "]"));
    }

    public String getDates() {
	JSONArray array = rateArray;
	return IntStream.range(0, array.length()).mapToObj(i -> array.getJSONObject(i).getString("Time"))
		.map(s -> "new Date(\"" + s + "\")").collect(Collectors.joining(",", "[", "]"));
    }

}
