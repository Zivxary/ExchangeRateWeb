package pers.zivxary.first.web.utils;

import pers.zivxary.first.web.type.VerifyResult;

public interface IDateUtil {

    /**
     * @return 取得當前時間
     */
    String getNowTime();

    /**
     * @return 取得當前日期
     */
    String getNowDay();

    /**
     * 驗證日期格式（三日內）
     * 
     * @param day 欲驗證的日期
     * @return SUCCESS, FORMAT_ERROR, RANGE_ERROR, NULL_ERROR
     */
    VerifyResult verifyThreeDay(String day);

    /**
     * 驗證日期格式（三年內）
     * 
     * @param day 欲驗證的日期
     * @return SUCCESS, FORMAT_ERROR, RANGE_ERROR, NULL_ERROR
     */
    VerifyResult verifyThreeYear(String day);

    /**
     * 驗證時間格式
     * 
     * @param time 欲驗證的時間
     * @return SUCCESS, FORMAT_ERROR, NULL_ERROR
     */
    VerifyResult verifyTime(String time);

    /**
     * 驗證endDay是否大於startDay
     * 
     * @param startDay 開始日
     * @param endDay   結束日
     * @return SUCCESS, FORMAT_ERROR, RANGE_ERROR
     */
    VerifyResult verifyDay(String startDay, String endDay);

}