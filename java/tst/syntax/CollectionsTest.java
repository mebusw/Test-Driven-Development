package syntax;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CollectionsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSet() {
        List<Integer> arr = new ArrayList<Integer>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(2);
        arr.add(4);
        arr.add(4);

        Set<Integer> set = new LinkedHashSet<Integer>();
        set.addAll(arr);

        assertEquals("[1, 2, 3, 2, 4, 4]", arr.toString());
        assertEquals("[1, 2, 3, 4]", set.toString());

    }

}
