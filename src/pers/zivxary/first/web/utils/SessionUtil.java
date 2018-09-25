package pers.zivxary.first.web.utils;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    private IDateUtil dateUtil = new DateUtil();
    private HttpSession session;

    public SessionUtil(HttpSession session) {
	this.session = session;
    }

    /**
     * 查看session.getAttribute(key)是否有值。有就使用，沒有就取當前日期
     * 
     * @param key 鍵值
     * @return 返回取到的值，沒有就返回當前日期
     */
    public String getDayFromSession(String key) {
	return getFormSession(key, dateUtil.getNowDay());
    }

    /**
     * 查看session.getAttribute(key)是否有值。有就使用，沒有就取當前時間
     * 
     * @param key 鍵值
     * @return 返回取到的值，沒有就返回當前時間
     */
    public String getTimeFromSession(String key) {
	return getFormSession(key, dateUtil.getNowTime());
    }

    /**
     * 從session.getAttribute 取key，如果有就返回，沒有就返回defaultVal
     * 
     * @param key        鍵值
     * @param defaultVal 沒有值時的返回值
     * @return 返回取到的值，沒有就返回defaultVal
     */
    public String getFormSession(String key, String defaultVal) {
	String s = (String) session.getAttribute(key);
	return (s == null) ? defaultVal : s;
    }

    /**
     * 從session.getAttribute 取key，如果有就返回，沒有就返回defaultVal
     * 
     * @param key        鍵值
     * @param defaultVal 沒有值時的返回值
     * @return 返回取到的值，沒有就返回defaultVal
     */
    public Object getFormSession(String key, Object defaultVal) {
	Object o = session.getAttribute(key);
	return (o == null) ? defaultVal : o;
    }
}
