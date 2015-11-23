package exercise.pinyin;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PinyinServiceTest {
	private PinyinService pinyinService;

	@Before
	public void setUp() throws Exception {
		PinyinDAO dao = mock(PinyinDAO.class);
		when(dao.get("章")).thenReturn("Zhang");
		when(dao.get("子")).thenReturn("Zi");
		when(dao.get("怡")).thenReturn("Yi");
		when(dao.get("武")).thenReturn("Wu");

		pinyinService = new PinyinService(dao);
	}

	@Test
	public void test_lookup_one_char() {
		assertEquals("Zhang", pinyinService.getPinyin("章"));
	}

	@Test
	public void test_lookup_another_char() {

		assertEquals("Zi", pinyinService.getPinyin("子"));
	}

	@Test
	public void test_lookup_multi_chars() {
		assertEquals("ZhangZiYi", pinyinService.getPinyin("章子怡"));
	}

	@Test
	public void test_lookup_with_non_exist_char() {
		assertEquals("Wu?", pinyinService.getPinyin("武瞾"));
	}
	
	@Test
	public void test_lookup_pinyinHeader() {
		assertEquals("ZZY", pinyinService.getPinyinHeader("章子怡"));
	}

}
