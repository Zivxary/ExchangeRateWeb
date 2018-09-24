package pers.zivxary.first.web.connection;

import java.io.IOException;
import java.net.MalformedURLException;

public interface IRateConnection {

    // 取得回傳結果
    String getResult();

    // 設置請求參數
    void setParameter(String parameter);

    // 設置請求屬性
    void setPropertys(String key, String value);

    // 運行
    void run() throws MalformedURLException, IOException;

}