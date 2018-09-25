package pers.zivxary.first.web.view;

import org.json.JSONArray;
import org.json.JSONObject;

public class SpotRateJson {

    private StringBuilder sb = new StringBuilder();
    private JSONObject data;

    public SpotRateJson(String data) {
	this.data = new JSONObject(new JSONObject(data).getString("d"));
    }

    public String getTime() {
	return data.get("QuoteTime").toString();
    }

    // 輸出表格 <tr>...</tr>
    public String getHtml() {

	JSONArray rate = data.getJSONArray("Rates");

	sb.setLength(0);
	for (int i = 0; i < rate.length(); ++i) {
	    JSONObject cell = rate.getJSONObject(i);
	    sb.append("<tr>");
	    appendCell(cell, "Title");
	    appendCell(cell, "BBoardRate");
	    appendCell(cell, "SBoardRate");
	    appendCell(cell, "BuyIncreaseRate");
	    appendCell(cell, "SellDecreaseRate");
	    appendCell(cell, "CashBBoardRate");
	    appendCell(cell, "CashSBoardRate");
	    sb.append("</tr>");
	}
	return sb.toString();
    }

    private void appendCell(JSONObject jsonObject, String key) {
	sb.append("<td>");
	sb.append(jsonObject.get(key));
	sb.append("</td>");
    }

}
