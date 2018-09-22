package pers.zivxary.first.web;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pers.zivxary.first.web.utils.DateUtil;
import pers.zivxary.first.web.utils.VerifyResult;

public class DateUtilTest {

    private DateUtil dateUtil = new DateUtil();

    // 日期 成功
    @Test
    public void day_success_test() {
	VerifyResult actual = null;
	VerifyResult expected = VerifyResult.SUCCESS;

	actual = dateUtil.verifyThreeDay("2018-09-21");
	assertEquals(expected, actual);
	actual = dateUtil.verifyThreeDay("2018-09-19");
	assertEquals(expected, actual);
    }

    // 日期 範圍錯誤
    @Test
    public void day_range_error_test() {
	VerifyResult actual = null;
	VerifyResult expected = VerifyResult.RANGE_ERROR;

	actual = dateUtil.verifyThreeDay("2018-09-10");
	assertEquals(expected, actual);
	actual = dateUtil.verifyThreeDay("2018-09-25");
	assertEquals(expected, actual);
    }

    // 日期 未輸入
    @Test
    public void day_null_error_test() {
	VerifyResult actual = null;
	VerifyResult expected = VerifyResult.NULL_ERROR;

	actual = dateUtil.verifyThreeDay("");
	assertEquals(expected, actual);
	actual = dateUtil.verifyThreeDay(null);
	assertEquals(expected, actual);
    }

    // 日期 格式錯誤
    @Test
    public void day_format_error_test() {
	VerifyResult actual = null;
	VerifyResult expected = VerifyResult.FORMAT_ERROR;

	actual = dateUtil.verifyThreeDay("2018-09-31");
	assertEquals(expected, actual);

    }

    // 時間 成功
    @Test
    public void time_success_test() {
	VerifyResult actual = null;
	VerifyResult expected = VerifyResult.SUCCESS;

	actual = dateUtil.verifyTime("23:57:35");
	assertEquals(expected, actual);
    }

    // 時間 未輸入
    @Test
    public void time_null_error_test() {
	VerifyResult actual = null;
	VerifyResult expected = VerifyResult.NULL_ERROR;

	actual = dateUtil.verifyTime("");
	assertEquals(expected, actual);
	actual = dateUtil.verifyTime(null);
	assertEquals(expected, actual);
    }

    // 時間 格式錯誤
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
