package pers.zivxary.first.web.type;

public enum RateType {
    spot("即期", 1), cash("現金", 0);

    private int id;
    private String nameZh;

    private RateType(String name, int id) {
	nameZh = name;
	this.id = id;
    }

    public String getNameZh() {
	return nameZh;
    }

    public int getId() {
	return id;
    }
}
