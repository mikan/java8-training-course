/*
 * Copyright (c) 2015. Yutaka Kato. All rights reserved.
 * https://github.com/mikan/java8-training-course
 */
package local.js8ri.ch03.ex16;

import java.time.LocalTime;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 *
 * @author mikan
 */
public class DoInOrderAsync {

    private static final int LOOP_COUNT = 5;
    private static final int LOOP_INTERVAL = 1000;

    private DoInOrderAsync() {
        // static use only
    }

    public static void main(String[] args) throws InterruptedException {

        Supplier<String> supplier = () -> {
            LocalTime time = LocalTime.now();
            if (time.getSecond() % 2 == 0) {
                return "[EVEN] " + time;
            } else {
                throw new RuntimeException("[ODD]  " + time);
            }
        };
        BiConsumer<String, Throwable> consumer = (s, t) -> {
            if (t == null) {
                System.out.println(s);
            } else {
                System.err.println(t.getMessage());
            }
        };
        Consumer<Throwable> handler = (t) -> {
            System.err.println(t.getMessage());
        };

        for (int i = 0; i < LOOP_COUNT; i++) {
            doInOrderAsync(supplier, consumer);
            Thread.sleep(LOOP_INTERVAL);
        }
        for (int i = 0; i < LOOP_COUNT; i++) {
            doInOrderAsync(supplier, consumer, handler);
            Thread.sleep(LOOP_INTERVAL);
        }
    }

    public static <T> void doInOrderAsync(Supplier<? extends T> first, BiConsumer<? super T, Throwable> second,
            Consumer<? super Throwable> handler) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        Objects.requireNonNull(handler);
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    T result = first.get();
                    second.accept(result, null);
                } catch (Throwable t) {
                    handler.accept(t);
                    second.accept(null, t);
                }
            }
        };
        t.start();
    }

    public static <T> void doInOrderAsync(Supplier<? extends T> first, BiConsumer<? super T, ? super Throwable> second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    T result = first.get();
                    second.accept(result, null);
                } catch (Throwable t) {
                    second.accept(null, t);
                }
            }
        };
        t.start();
    }
}
