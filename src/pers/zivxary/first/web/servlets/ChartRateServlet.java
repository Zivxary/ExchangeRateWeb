package pers.zivxary.first.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pers.zivxary.first.web.utils.DateUtil;
import pers.zivxary.first.web.utils.IDateUtil;
import pers.zivxary.first.web.utils.VerifyResult;

@WebServlet("/ChartRateServlet")
public class ChartRateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IDateUtil dateUtil = new DateUtil();

    public ChartRateServlet() {
	super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	HttpSession session = request.getSession();

	String fromDay = request.getParameter("fromDay");
	String toDay = request.getParameter("toDay");

	VerifyResult fromDayResult = dateUtil.verifyThreeYear(fromDay);
	VerifyResult toDayResult = dateUtil.verifyThreeYear(toDay);

	String verifyResult = "";

	if (toDayResult == VerifyResult.SUCCESS && fromDayResult == VerifyResult.SUCCESS
		&& dateUtil.verifyDay(fromDay, toDay) == VerifyResult.SUCCESS) {

	    session.setAttribute("fromDay", fromDay);
	    session.setAttribute("toDay", toDay);
	    verifyResult = verifyFromDay(fromDayResult) + verifyToDay(toDayResult);

	} else if (toDayResult == VerifyResult.SUCCESS && fromDayResult == VerifyResult.SUCCESS) {
	    verifyResult = "起始日期不得大於終迄日期";
	} else {
	    verifyResult = verifyFromDay(fromDayResult) + verifyToDay(toDayResult);
	}

	session.setAttribute("verifyChartResult", verifyResult);

	session.setAttribute("inputFromDay", fromDay);
	session.setAttribute("inputToDay", toDay);

	request.getRequestDispatcher("/exchangeRate.jsp").forward(request, response);
	return;

    }

    private String verifyFromDay(VerifyResult result) {
	switch (result) {
	case SUCCESS:
	    return "&nbsp;起始日期正確&nbsp;";

	case FORMAT_ERROR:
	    return "&nbsp;起始日期格式錯誤&nbsp;";

	case NULL_ERROR:
	    return "&nbsp;起始日期未填寫&nbsp;";

	case RANGE_ERROR:
	    return "&nbsp;起始日期超出範圍（限三年內）&nbsp;";

	default:
	    return "&nbsp;未知錯誤&nbsp;";
	}
    }

    private String verifyToDay(VerifyResult result) {
	switch (result) {
	case SUCCESS:
	    return "&nbsp;終迄日期正確&nbsp;";

	case FORMAT_ERROR:
	    return "&nbsp;終迄日期格式錯誤&nbsp;";

	case NULL_ERROR:
	    return "&nbsp;終迄日期未填寫&nbsp;";

	case RANGE_ERROR:
	    return "&nbsp;終迄日期超出範圍（限三年內）&nbsp;";

	default:
	    return "&nbsp;未知錯誤&nbsp;";
	}
    }

}
