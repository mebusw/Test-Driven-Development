package exercise.pinyin;

public class PinyinService {
	PinyinDAO dao;

	public PinyinService(PinyinDAO dao) {
		this.dao = dao;
	}

	public String getPinyin(String cnChars) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cnChars.length(); i++) {
			String cnChar = cnChars.substring(i, i + 1);
			String pyChar = dao.get(cnChar);
			if (pyChar == null) {
				pyChar = "?";
			}
			String capitalizedPyChar = pyChar.substring(0, 1).toUpperCase()
					+ pyChar.substring(1);
			sb.append(capitalizedPyChar);
		}
		return sb.toString();
	}

	public String getPinyinHeader(String cnChars) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cnChars.length(); i++) {
			String cnChar = cnChars.substring(i, i + 1);
			String pyChar = dao.get(cnChar);
			if (pyChar == null) {
				pyChar = "?";
			}
			String capitalizedPyChar = pyChar.substring(0, 1).toUpperCase()
					;
			sb.append(capitalizedPyChar);
		}
		return sb.toString();
	}
}
