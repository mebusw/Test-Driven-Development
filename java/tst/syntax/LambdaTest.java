package syntax;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LambdaTest {


    @Test
    public void lambda() {
        int numbers[] = new int[]{1, 2, 3, 4, 5};

        List res = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0)
                res.add(numbers[i]);
        }
        System.out.println(res);


        int result[] = IntStream.of(numbers).filter(x -> x % 2 != 0).filter(x -> x > 1).toArray();

        IntStream.of(result).forEach((x) -> System.out.println(x));

    }


}