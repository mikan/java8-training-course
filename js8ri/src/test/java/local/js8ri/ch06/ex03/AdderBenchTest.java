/*
 * Copyright (c) 2015. Yutaka Kato. All rights reserved.
 * https://github.com/mikan/Java8TrainingCourse
 */

package local.js8ri.ch06.ex03;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.concurrent.TimeUnit;

/**
 * Created by mikan on 2015/09/16.
 *
 * @author mikan
 */
public class AdderBenchTest {

    @Rule
    public Timeout timeout = new Timeout(20, TimeUnit.SECONDS);

    @Test
    public void testBench() {
        AdderBench bench = new AdderBench();
        bench.incrementWithAtomicLong(1000, 100000);
        bench.incrementWithLongAdder(1000, 100000);
    }
}