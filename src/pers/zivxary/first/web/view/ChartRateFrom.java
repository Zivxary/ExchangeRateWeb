package pers.zivxary.first.web.view;

import pers.zivxary.first.web.type.CurrencyType;
import pers.zivxary.first.web.type.RateType;

public class ChartRateFrom {

    private StringBuilder sb = new StringBuilder();

    // 取得下拉式選單選項
    public String getOptions(CurrencyType selectType) {

	sb.setLength(0);

	// <option value="THB" selected>泰銖(THB)</option>
	for (CurrencyType type : CurrencyType.values()) {
	    sb.append("<option value=\"").append(type.name()).append("\"");
	    if (type == selectType) {
		sb.append(" selected");
	    }
	    sb.append(">").append(type.getFullName()).append("</option>");
	}
	return sb.toString();
    }

    // 取得單選選項
    public String getRaidos(RateType selectType) {
	sb.setLength(0);
	for (RateType type : RateType.values()) {
	    sb.append("<input type=\"radio\" name=\"rateType\" value=\"").append(type.name()).append("\"");
	    if (type == selectType) {
		sb.append(" checked");
	    }
	    sb.append("/>").append(type.getNameZh());
	}
	return sb.toString();
    }
}
