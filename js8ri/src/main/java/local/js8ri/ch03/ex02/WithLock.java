/*
 * Copyright (c) 2015. Yutaka Kato. All rights reserved.
 * https://github.com/mikan/java8-training-course
 */

package local.js8ri.ch03.ex02;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mikan
 */
public class WithLock {

    private WithLock() {
        // static use only
    }

    /**
     * Run {@code runnable} with specified lock instance.
     *
     * @param lock     lock
     * @param runnable runnable
     * @throws NullPointerException if the argument(s) is(are) {@code null}.
     * @throws IllegalStateException if the lock is already held.
     */
    public static void withLock(@Nonnull ReentrantLock lock, @Nonnull Runnable runnable) {
        Objects.requireNonNull(lock);
        Objects.requireNonNull(runnable);
        if (lock.isLocked()) {
            throw new IllegalStateException("Lock is already held.");
        }
        lock.lock();
        try {
            runnable.run();
        } finally {
            lock.unlock();
        }
    }
}
