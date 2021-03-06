/*
 * Copyright (c) 2015. Yutaka Kato. All rights reserved.
 * https://github.com/mikan/java8-training-course
 */
package local.js8ri.ch01.ex08;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mikan
 */
public class CaptureChecker {

    private CaptureChecker() {
        // static use only
    }

    public static void sample1() {
        String[] names = {"Peter", "Paul", "Mary"};
        List<Runnable> runners = new ArrayList<>();
        for (String name : names) {
            runners.add(() -> System.out.println("[sample1]" + name));
        }
        runners.forEach(run -> run.run());
    }

    public static void sample2() {
        String[] names = {"Peter", "Paul", "Mary"};
        List<Runnable> runners = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            runners.add(() -> System.out.println("[sample2]" + name));
        }
        runners.forEach(run -> run.run());
    }

    public static void sample3() {
        String[] names = {"Peter", "Paul", "Mary"};
        List<Runnable> runners = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            int j = i;
            runners.add(() -> System.out.println("[sample3]" + names[j]));
        }
        runners.forEach(run -> run.run());
    }
}
