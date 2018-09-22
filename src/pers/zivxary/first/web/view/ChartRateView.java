package pers.zivxary.first.web.view;

import javax.servlet.http.HttpSession;

import pers.zivxary.first.web.utils.SessionUtil;

public class ChartRateView {

    private SessionUtil sessionUtil;

    public ChartRateView(HttpSession session) {
	sessionUtil = new SessionUtil(session);
    }

    /**
     * 從 ExchangeRateServlet 回傳的日期、時間驗證結果
     * 
     * @return 驗證結果
     */
    public String getChartVerifyResult() {
	return sessionUtil.getFormSession("verifyChartResult", "");
    }

    /**
     * 查看session.getAttribute("inputFromDay")是否有值。有就使用，沒有就取當前日期 "inputFromDay"
     * 為使用者輸入的起始日期
     * 
     * @return 日期
     */
    public String getInputFromDay() {
	return sessionUtil.getDayFromSession("inputFromDay");
    }

    /**
     * 查看session.getAttribute("inputToDay")是否有值。有就使用，沒有就取當前日期 "inputToDay"
     * 為使用者輸入的終迄日期
     * 
     * @return 日期
     */
    public String getInputToDay() {
	return sessionUtil.getDayFromSession("inputToDay");
    }

}
