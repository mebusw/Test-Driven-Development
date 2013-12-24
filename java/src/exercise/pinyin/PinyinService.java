package exercise.pinyin;

public class PinyinService {
	PinyinDAO dao;

	public PinyinService(PinyinDAO dao) {
		this.dao = dao;
	}

	public String getPinyin(String cnChars) {
		String[] pyChars = readPyForCn(cnChars);

		return transformPy(pyChars, new PinyinTransformer() {
			public String transform(String pyString) {
				return pyString.substring(0, 1).toUpperCase()
						+ pyString.substring(1);
			}
		});
	}

	public String getPinyinHeader(String cnChars) {
		String[] pyChars = readPyForCn(cnChars);
		return transformPy(pyChars, new PinyinTransformer() {
			public String transform(String pyString) {
				return pyString.substring(0, 1).toUpperCase();
			}
		});
	}

	private String transformPy(String[] pyChars, PinyinTransformer transformer) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < pyChars.length; i++) {
			sb.append(transformer.transform(pyChars[i]));
		}
		return sb.toString();
	}

	private String[] readPyForCn(String cnChars) {
		String[] pyChars = new String[cnChars.length()];
		for (int i = 0; i < cnChars.length(); i++) {
			String cnChar = cnChars.substring(i, i + 1);
			pyChars[i] = dao.get(cnChar);
			if (pyChars[i] == null) {
				pyChars[i] = "?";
			}
		}
		return pyChars;
	}
}

interface PinyinTransformer {
	public String transform(String pyString);
}
