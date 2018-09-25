# ExchangeRateWeb 簡易匯率網站

使用 Eclipse Jee Photon、Tomcat v7.0 Server

<br>

功能部分：
* 日期驗證 
* 時間驗證
* 使用chart.js建立圖表
* 包留所選所填的資料
* 若輸入錯誤資料，則顯示前一次正確資料

缺點：
* 日期驗證，由於匯率網站部分資料不連續，造成取值不精
* 沒有針對某些幣別防呆，如：泰銖的現金歷史匯率
* 驗證都在伺服端，瀏覽器沒有任何驗證
* 包留選填資料也是在伺服端處理
* 沒有建立DataBase，以至於需要一直到匯率網站撈數據
* 如果POST相同資料，沒有預留備份，需要再去撈一次
* 沒有使用AJAX更新資料
* 沒有頁籤可以切換


### 目前成果
---

![](https://github.com/Zivxary/ExchangeRateWeb/blob/master/%E9%A0%90%E8%A6%BD.jpg)
