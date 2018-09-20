package pers.zivxary.first.web;

import org.json.JSONArray;
import org.json.JSONObject;

public class ExchageRateTable {

    StringBuilder sb = new StringBuilder();

    public String html(JSONObject jsonObject) {

	JSONArray data = new JSONObject(jsonObject.getString("d")).getJSONArray("Rates");

	sb.setLength(0);
	for (int i = 0; i < data.length(); ++i) {
	    JSONObject cell = data.getJSONObject(i);
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
