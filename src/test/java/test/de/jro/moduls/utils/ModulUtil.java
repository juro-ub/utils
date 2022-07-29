package test.de.jro.moduls.utils;

import test.de.jro.moduls.utils.mocks.CallableDummy;
import de.jro.moduls.utils.StaticUtils;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.concurrent.ExecutorService;
import de.jro.moduls.utils.SingletonExecutorServiceLoader;
import java.util.concurrent.Future;

public class ModulUtil {

    @Test
    public void test() {
        Integer[] dummy = {1, 2, 3, 4, 5, 6, 7};

        Object[][] resChunks = StaticUtils.chunkArray(dummy, 2);
        assertArrayEquals(new Object[][]{{1, 2}, {3, 4}, {5, 6}, {7}}, resChunks, "Fail to chunk array");

        Object[][] resCombs = StaticUtils.combinationsOfArray(dummy);
        assertTrue(resCombs.length == (Math.pow(2, 7)) - 1);

        List<Double> resNums = StaticUtils.extractNumbersFromStr("Teste2.21teste321");
        assertTrue(resNums.size() == 2);

        String word = StaticUtils.getBiggestWordFromStr("Hello words", " ");
        assertTrue(word.equals("Hello"));

        try {
            ExecutorService executor = SingletonExecutorServiceLoader.getFixedThreadPool();

            CallableDummy callable = new CallableDummy(0);

            Future<Boolean> future = executor.submit(callable);

            assertTrue(future.get());
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        }

    }
}
