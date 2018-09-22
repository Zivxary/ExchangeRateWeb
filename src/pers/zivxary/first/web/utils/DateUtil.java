package pers.zivxary.first.web.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private DateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
    private DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * @return 取得當前時間
     */
    public String getNowTime() {
	return timeFormat.format(new Date());
    }

    /**
     * @return 取得當前日期
     */
    public String getNowDay() {
	return dayFormat.format(new Date());
    }

    /**
     * 驗證日期格式
     * 
     * @param day 欲驗證的日期
     * @return 驗證結果
     */
    public VerifyResult verifyThreeDay(String day) {
	VerifyResult result = verify(day, dayFormat);
	if (VerifyResult.SUCCESS != result) {
	    return result;
	}
	try {
	    Date inputDay = dayFormat.parse(day.trim());
	    final long oneDay = 24 * 60 * 60 * 1000;
	    long nowDay = new Date().getTime() / oneDay * oneDay;
	    long diff = nowDay - inputDay.getTime();
	    if (diff / oneDay > 3 || diff < 0) {
		return VerifyResult.RANGE_ERROR;
	    }
	} catch (ParseException e) {
	    return VerifyResult.FORMAT_ERROR;
	}
	return VerifyResult.SUCCESS;
    }

    /**
     * 驗證時間格式
     * 
     * @param time 欲驗證的時間
     * @return 驗證結果
     */
    public VerifyResult verifyTime(String time) {
	return verify(time, timeFormat);
    }

    /**
     * 驗證Date
     * 
     * @param date       被驗證的字串
     * @param len        驗證字串長度
     * @param dateFormat 驗證格式
     * @return 驗證結果
     */
    private VerifyResult verify(String date, DateFormat dateFormat) {
	if (date == null || date.trim() == "") {
	    return VerifyResult.NULL_ERROR;
	}
	dateFormat.setLenient(false);
	try {
	    dateFormat.parse(date);
	} catch (ParseException e) {
	    return VerifyResult.FORMAT_ERROR;
	}
	return VerifyResult.SUCCESS;
    }

}
