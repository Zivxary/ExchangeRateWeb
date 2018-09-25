package pers.zivxary.first.web;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pers.zivxary.first.web.type.VerifyResult;
import pers.zivxary.first.web.utils.DateUtil;
import pers.zivxary.first.web.utils.IDateUtil;

public class DateUtilTest {

    private IDateUtil dateUtil = new DateUtil();

    // 驗證三日內 成功
    @Test
    public void threeDay_success_test() {
	VerifyResult actual = null;
	VerifyResult expected = VerifyResult.SUCCESS;

	actual = dateUtil.verifyThreeDay("2018-09-21");
	assertEquals(expected, actual);
	actual = dateUtil.verifyThreeDay("2018-09-20");
	assertEquals(expected, actual);
    }

    // 驗證三日內 範圍錯誤
    @Test
    public void threeDay_range_error_test() {
	VerifyResult actual = null;
	VerifyResult expected = VerifyResult.RANGE_ERROR;

	actual = dateUtil.verifyThreeDay("2018-09-10");
	assertEquals(expected, actual);
	actual = dateUtil.verifyThreeDay("2018-10-01");
	assertEquals(expected, actual);
    }

    // 驗證三日內 未輸入
    @Test
    public void threeDay_null_error_test() {
	VerifyResult actual = null;
	VerifyResult expected = VerifyResult.NULL_ERROR;

	actual = dateUtil.verifyThreeDay("");
	assertEquals(expected, actual);
	actual = dateUtil.verifyThreeDay(null);
	assertEquals(expected, actual);
    }

    // 驗證三日內 格式錯誤
    @Test
    public void threeDay_format_error_test() {
	VerifyResult actual = null;
	VerifyResult expected = VerifyResult.FORMAT_ERROR;

	actual = dateUtil.verifyThreeDay("2018-09-31");
	assertEquals(expected, actual);
    }

    // 驗證三年內 範圍錯誤
    @Test
    public void threeYear_range_error_test() {
	VerifyResult actual = null;
	VerifyResult expected = VerifyResult.RANGE_ERROR;

	actual = dateUtil.verifyThreeYear("2015-09-10");
	assertEquals(expected, actual);
	actual = dateUtil.verifyThreeYear("2019-09-25");
	assertEquals(expected, actual);
    }

    // 驗證日期順序 範圍錯誤
    @Test
    public void day_range_error_test() {
	VerifyResult actual = null;
	VerifyResult expected = VerifyResult.RANGE_ERROR;

	actual = dateUtil.verifyDay("2018-09-23", "2018-09-22");
	assertEquals(expected, actual);
    }

    // 驗證日期順序 成功
    @Test
    public void day_success_test() {
	VerifyResult actual = null;
	VerifyResult expected = VerifyResult.SUCCESS;

	actual = dateUtil.verifyDay("2018-09-21", "2018-09-22");
	assertEquals(expected, actual);
	actual = dateUtil.verifyDay("2018-09-22", "2018-09-22");
	assertEquals(expected, actual);
    }

    // 驗證時間 成功
    @Test
    public void time_success_test() {
	VerifyResult actual = null;
	VerifyResult expected = VerifyResult.SUCCESS;

	actual = dateUtil.verifyTime("23:57:35");
	assertEquals(expected, actual);
    }

    // 驗證時間 未輸入
    @Test
    public void time_null_error_test() {
	VerifyResult actual = null;
	VerifyResult expected = VerifyResult.NULL_ERROR;

	actual = dateUtil.verifyTime("");
	assertEquals(expected, actual);
	actual = dateUtil.verifyTime(null);
	assertEquals(expected, actual);
    }

    // 驗證時間 格式錯誤
    @Test
    public void time_format_error_test() {
	VerifyResult actual = null;
	VerifyResult expected = VerifyResult.FORMAT_ERROR;

	actual = dateUtil.verifyTime("25:00:00");
	assertEquals(expected, actual);
	actual = dateUtil.verifyTime("20:00");
	assertEquals(expected, actual);
    }
}
