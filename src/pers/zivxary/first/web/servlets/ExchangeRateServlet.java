package pers.zivxary.first.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.zivxary.first.web.utils.DateUtil;
import pers.zivxary.first.web.utils.VerifyResult;

@WebServlet("/exchangeRateServlet")
public class ExchangeRateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private DateUtil dateUtil = new DateUtil();

    public ExchangeRateServlet() {
	super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String day = request.getParameter("day");
	String time = request.getParameter("time");

	VerifyResult dayResult = dateUtil.verifyThreeDay(day);
	VerifyResult timeReslt = dateUtil.verifyTime(time);

	String verifyResult = verifyDay(dayResult) + verifyTime(timeReslt);
	request.setAttribute("verifyResult", verifyResult);

	if (dayResult == VerifyResult.SUCCESS && timeReslt == VerifyResult.SUCCESS) {
	    request.setAttribute("day", day);
	    request.setAttribute("time", time);
	}

	request.setAttribute("inputDay", day);
	request.setAttribute("inputTime", time);

	request.getRequestDispatcher("/exchangeRate.jsp").forward(request, response);
	return;
    }

    private String verifyDay(VerifyResult result) {
	switch (result) {
	case SUCCESS:
	    return "&nbsp;日期正確&nbsp;";

	case FORMAT_ERROR:
	    return "&nbsp;日期格式錯誤&nbsp;";

	case NULL_ERROR:
	    return "&nbsp;日期未填寫&nbsp;";

	case RANGE_ERROR:
	    return "&nbsp;日期超出範圍（限三天內）&nbsp;";

	default:
	    return "&nbsp;未知錯誤&nbsp;";
	}
    }

    private String verifyTime(VerifyResult result) {
	switch (result) {
	case SUCCESS:
	    return "&nbsp;時間正確&nbsp;";

	case FORMAT_ERROR:
	    return "&nbsp;時間格式錯誤&nbsp;";

	case NULL_ERROR:
	    return "&nbsp;時間未填寫&nbsp;";

	case RANGE_ERROR:
	default:
	    return "&nbsp;未知錯誤&nbsp;";
	}
    }

}
