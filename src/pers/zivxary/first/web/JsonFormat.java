package pers.zivxary.first.web;

public class JsonFormat {

    private StringBuilder sb = new StringBuilder();

    public String format(String jsonString) {

	if (jsonString == null || "".equals(jsonString)) {
	    return "";
	}

	int tabCount = 0;
	sb.setLength(0);

	for (int i = 0; i < jsonString.length(); ++i) {
	    char ch = jsonString.charAt(i);
	    switch (ch) {
	    case '{':
	    case '[':
		sb.append(ch);
		newLine();
		appendTab(++tabCount);
		break;

	    case '}':
	    case ']':
		newLine();
		appendTab(--tabCount);
		sb.append(ch);
		break;

	    case ',':
		sb.append(ch);
		newLine();
		appendTab(tabCount);

	    case '\\':
		break;

	    default:
		sb.append(ch);
		break;
	    }
	}

	return sb.toString();
    }

    private void appendTab(int count) {
	for (int i = 0; i < count; ++i) {
	    sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
	}
    }

    private void newLine() {
	sb.append("<br>");
    }
}
