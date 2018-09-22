package pers.zivxary.first.web.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil implements IDateUtil {

    private static final long ONE_DAY = 24 * 60 * 60 * 1000;

    private DateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
    private DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    /*
     * （非 Javadoc）
     * 
     * @see pers.zivxary.first.web.utils.IDateUtil#getNowTime()
     */
    @Override
    public String getNowTime() {
	return timeFormat.format(new Date());
    }

    /*
     * （非 Javadoc）
     * 
     * @see pers.zivxary.first.web.utils.IDateUtil#getNowDay()
     */
    @Override
    public String getNowDay() {
	return dayFormat.format(new Date());
    }

    /*
     * （非 Javadoc）
     * 
     * @see pers.zivxary.first.web.utils.IDateUtil#verifyThreeDay(java.lang.String)
     */
    @Override
    public VerifyResult verifyThreeDay(String day) {

	final long threeDay = ONE_DAY * 3;
	return verifyDayRange(day, threeDay);
    }

    /*
     * （非 Javadoc）
     * 
     * @see pers.zivxary.first.web.utils.IDateUtil#verifyThreeYear(java.lang.String)
     */
    @Override
    public VerifyResult verifyThreeYear(String day) {

	final long threeYear = ONE_DAY * 365 * 3;
	return verifyDayRange(day, threeYear);
    }

    /*
     * （非 Javadoc）
     * 
     * @see pers.zivxary.first.web.utils.IDateUtil#verifyTime(java.lang.String)
     */
    @Override
    public VerifyResult verifyTime(String time) {
	return verifyFormat(time, timeFormat);
    }

    /*
     * （非 Javadoc）
     * 
     * @see pers.zivxary.first.web.utils.IDateUtil#verifyDay(java.lang.String,
     * java.lang.String)
     */
    @Override
    public VerifyResult verifyDay(String startDay, String endDay) {
	try {
	    Date startDate = dayFormat.parse(startDay);
	    Date endDate = dayFormat.parse(endDay);
	    if (startDate.getTime() > endDate.getTime()) {
		return VerifyResult.RANGE_ERROR;
	    }
	} catch (ParseException e) {
	    return VerifyResult.FORMAT_ERROR;
	}
	return VerifyResult.SUCCESS;
    }

    /**
     * 驗證Date格式
     * 
     * @param date       被驗證的字串
     * @param dateFormat 驗證格式
     * @return SUCCESS, FORMAT_ERROR, NULL_ERROR
     */
    private VerifyResult verifyFormat(String date, DateFormat dateFormat) {
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

    /**
     * 驗證日期範圍，與當前日期比對
     * 
     * @param day   欲驗證的日期
     * @param range 限制的範圍
     * @return SUCCESS, FORMAT_ERROR, RANGE_ERROR
     */
    private VerifyResult verifyDayRange(String day, long range) {

	VerifyResult result = verifyFormat(day, dayFormat);
	if (VerifyResult.SUCCESS != result) {
	    return result;
	}

	try {
	    Date inputDay = dayFormat.parse(day.trim());

	    long nowDay = new Date().getTime() / ONE_DAY * ONE_DAY;
	    long diff = nowDay - inputDay.getTime();
	    if (diff - range > 0 || diff < 0) {
		return VerifyResult.RANGE_ERROR;
	    }
	} catch (ParseException e) {
	    return VerifyResult.FORMAT_ERROR;
	}

	return VerifyResult.SUCCESS;
    }

}
