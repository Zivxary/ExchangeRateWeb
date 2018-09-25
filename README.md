# ExchangeRateWeb 簡易匯率網站

使用 Eclipse Jee Photon、Tomcat v7.0 Server

<br>

功能部分：
* 日期驗證 
* 時間驗證
* 使用chart.js建立圖表
* 保留所選所填的資料
* 若輸入錯誤資料，則顯示前一次正確資料

缺點：
* 日期驗證，由於匯率網站部分資料不連續，造成取值不精
* 沒有針對某些幣別防呆，如：泰銖的現金歷史匯率
* 驗證都在伺服端，瀏覽器沒有任何驗證
* 保留選填資料也是在伺服端處理
* 沒有建立DataBase，以至於需要一直到匯率網站撈數據
* 如果POST相同資料，沒有預留備份，需要再去撈一次
* 沒有使用AJAX更新資料
* 沒有頁籤可以切換

專案部分：
* git 常常檔案改了一大堆才上傳，導致commit message不精確
* 使用 request.getRequestDispatcher(...).forward(request, response)似乎不是很好
* JSP只有大量使用 <% %> 跟 <%= %>，其他還要查才會用
* web.xml 如何使用，還是一頭霧水，雖然這專案沒用到，但知道很重要
* Javascript 只會最基礎的，稍微能修改點東西的程度
* 沒有為以後可能增加DataBase 預留擴充性
* 基本上要加啥功能都要查老半天，HTML標籤、JS寫法...
* 這些是一個禮拜內，每天至少花5小時以上，查資料寫程式、一直重構而來的
* 設計方式還在 bottom-up 所以變成增加個啥，就需要一直重構

### 目前成果
---

![](https://github.com/Zivxary/ExchangeRateWeb/blob/master/%E9%A0%90%E8%A6%BD.jpg)
