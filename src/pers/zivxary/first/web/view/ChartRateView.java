package pers.zivxary.first.web.view;

import javax.servlet.http.HttpSession;

import pers.zivxary.first.web.service.ChartRateService;
import pers.zivxary.first.web.type.CurrencyType;
import pers.zivxary.first.web.type.RateType;
import pers.zivxary.first.web.utils.SessionUtil;

public class ChartRateView {

    // 負責表單
    private ChartRateFrom chartRateFrom = new ChartRateFrom();

    // 從session取資料用 工具類
    private SessionUtil sessionUtil;

    // 解析圖表用數據
    private ChartRateJson chartRateJson;

    public ChartRateView(HttpSession session) {
	sessionUtil = new SessionUtil(session);

	ChartRateService service = new ChartRateService();
	service.setParameters(getCurrencyType(), getRateType(), getFromDay(), getToDay());
	chartRateJson = new ChartRateJson(service.getData());
    }

    /**
     * @return 從 ExchangeRateServlet 回傳的日期、時間驗證結果
     */
    public String getChartVerifyResult() {
	return sessionUtil.getFormSession("verifyChartResult", "");
    }

    /**
     * @return 取得使用者輸入的"起始"日期，沒有則為當前日期
     */
    public String getInputFromDay() {
	return sessionUtil.getDayFromSession("inputFromDay");
    }

    /**
     * @return 取得使用者輸入的"終迄"日期，沒有則為當前日期
     */
    public String getInputToDay() {
	return sessionUtil.getDayFromSession("inputToDay");
    }

    /**
     * @return 取得下拉式選單選項（USD、CNY...）
     */
    public String getOptions() {
	return chartRateFrom.getOptions(getCurrencyType());
    }

    /**
     * @return 取得單選選項(即期、現金)
     */
    public String getRaidos() {
	return chartRateFrom.getRaidos(getRateType());
    }

    /**
     * @return 取得買匯數據
     */
    public String getBuyRates() {
	return chartRateJson.getBuyRates();
    }

    /**
     * 
     * @return 取得賣匯數據
     */
    public String getSellRates() {
	return chartRateJson.getSellRates();
    }

    /**
     * 
     * @return 取得日期數據
     */
    public String getDates() {
	return chartRateJson.getDates();
    }

    /**
     * @return 取得幣別名稱(只有中文)
     */
    public String getCurrency() {
	return "\"" + getCurrencyType().getNameZh() + "\"";
    }

    /**
     * @return 取得匯率方式
     */
    public String getRateName() {

	return "\"" + getRateType().getNameZh() + "\"";
    }

    /**
     * @return 驗證過的起始日期，沒驗過則為當前日期，或上一次驗證過的日期
     */
    private String getFromDay() {
	return sessionUtil.getDayFromSession("fromDay");
    }

    /**
     * @return 驗證過的終迄日期，沒驗過則為當前日期，或上一次驗證過的日期
     */
    private String getToDay() {
	return sessionUtil.getDayFromSession("toDay");
    }

    /**
     * @return 取得幣別enum
     */
    private CurrencyType getCurrencyType() {
	return (CurrencyType) sessionUtil.getFormSession("currency", CurrencyType.USD);
    }

    /**
     * @return 取得匯率方式enum
     */
    private RateType getRateType() {
	return (RateType) sessionUtil.getFormSession("rateType", RateType.spot);
    }
}
