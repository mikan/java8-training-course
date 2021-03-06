/*
 * Copyright (c) 2015. Yutaka Kato. All rights reserved.
 * https://github.com/mikan/java8-training-course
 */
package local.js8ri.ch09.ex02;

import java.io.File;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Test for {@link TryWithResources2}.
 *
 * @author mikan
 */
public class TryWithResources2Test {

    @Test
    public void testMain_emptyInput() throws Throwable {
        TryWithResources2.main(null); // Check exception
        File file = new File("build/ch9.ex02.txt");
        assertTrue(file.exists());
    }
}
