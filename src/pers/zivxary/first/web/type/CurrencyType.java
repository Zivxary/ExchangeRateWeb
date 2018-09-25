package pers.zivxary.first.web.type;

public enum CurrencyType {
    USD("美金"), CNY("人民幣"), HKD("港幣"), JPY("日圓"), EUR("歐元"), AUD("澳幣"), CAD("加拿大幣"), GBP("英鎊"), ZAR("南非幣"), NZD("紐西蘭幣"),
    CHF("瑞士法郎"), SEK("瑞典幣"), SGD("新加坡幣"), MXN("墨西哥披索"), THB("泰銖");

    private String nameZh;

    private CurrencyType(String name) {
	this.nameZh = name;
    }

    public String getFullName() {
	return nameZh + "(" + name() + ")";
    }

    public String getNameZh() {
	return nameZh;
    }
}
