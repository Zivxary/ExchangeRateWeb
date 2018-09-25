package pers.zivxary.first.web.view;

import javax.servlet.http.HttpSession;

import pers.zivxary.first.web.service.ISpotRateSerivce;
import pers.zivxary.first.web.service.SpotRateService;
import pers.zivxary.first.web.utils.SessionUtil;

public class SpotRateView {

    private SpotRateJson table;
    private SessionUtil sessionUtil;

    public SpotRateView(HttpSession session) {
	sessionUtil = new SessionUtil(session);

	ISpotRateSerivce service = new SpotRateService();
	service.setParameters(getDay(), getTime());
	table = new SpotRateJson(service.getData());
    }

    /**
     * 從 ExchangeRateServlet 回傳的日期、時間驗證結果
     * 
     * @return 驗證結果
     */
    public String getSpotVerifyResult() {
	return sessionUtil.getFormSession("verifySpotResult", "");
    }

    /**
     * 查看session.getAttribute("inputDay")是否有值。有就使用，沒有就取當前日期 "inputDay" 為使用者輸入的日期
     * 
     * @return 日期
     */
    public String getInputDay() {
	return sessionUtil.getDayFromSession("inputDay");
    }

    /**
     * 查看session.getAttribute("inputTime")是否有值。有就使用，沒有就取當前日期 "inputTime" 為使用者輸入的時間
     * 
     * @return 日期
     */
    public String getInputTime() {
	return sessionUtil.getTimeFromSession("inputTime");
    }

    /**
     * 從 ExchageRateService 取得request中時間的JSON匯率資料，轉換為Html表格
     * 
     * @return 匯率表格
     */
    public String getTable() {
	return table.getHtml();
    }

    /**
     * 從 ExchageRateService 取得request中時間的JSON匯率資料，抓出該資料的時間
     * 
     * @return 匯率資料實際時間
     */
    public String getTableTime() {
	return "取值時間：" + table.getTime();
    }

    /**
     * 查看session.getAttribute("day")是否有值。有就使用，沒有就取當前日期 "day" 為驗證後可使用的日期，驗證失敗則為空
     * 
     * @return 日期
     */
    private String getDay() {
	return sessionUtil.getDayFromSession("day");
    }

    /**
     * 查看session.getAttribute("time")是否有值。有就使用，沒有就取當前日期 "time" 為驗證後可使用的日期，驗證失敗則為空
     * 
     * @return 日期
     */
    private String getTime() {
	return sessionUtil.getTimeFromSession("time");
    }

}
