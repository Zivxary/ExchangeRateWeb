package pers.zivxary.first.web.service;

public interface ISpotRateSerivce {

    // 取得資料
    String getData();

    // 設置要POST的參數
    void setParameters(String day, String time);

}