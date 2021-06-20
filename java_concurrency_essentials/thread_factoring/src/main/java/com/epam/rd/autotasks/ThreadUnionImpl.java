package com.epam.rd.autotasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadUnionImpl implements ThreadUnion
{
    private static final String NAME_THREAD_PATTERN;
    private final List<Thread> threads;
    private final List<FinishedThreadResult> results;
    private final AtomicInteger counter;
    private final String name;
    private boolean shutdown;

    static {
        NAME_THREAD_PATTERN = "%s-worker-%d";
    }

    {
        threads = Collections.synchronizedList(new ArrayList<>());
        results = Collections.synchronizedList(new ArrayList<>());
        counter = new AtomicInteger(0);
    }

    public ThreadUnionImpl(String name) {
        this.name = name;
    }

    @Override
    public int totalSize() {
        return threads.size();
    }

    @Override
    public int activeSize() {
        return (int) threads.stream()
                .filter(Thread::isAlive)
                .count();
    }

    @Override
    public void shutdown() {
        shutdown = true;
        threads.forEach(Thread::interrupt);
    }

    @Override
    public boolean isShutdown() {
        return shutdown;
    }

    @Override
    public void awaitTermination() {
        synchronized (threads) {
            threads.forEach(thread -> {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            });
        }
    }

    @Override
    public boolean isFinished() {
        return isShutdown() && activeSize() == 0;
    }

    @Override
    public List<FinishedThreadResult> results() {
        return results;
    }

    @Override
    public Thread newThread(Runnable goal) {
        if (isShutdown()) {
            throw new IllegalStateException("Thread is shutdown");
        }

        Thread thread = getThread(goal);
        thread.setUncaughtExceptionHandler((t, e) ->
                results.add(new FinishedThreadResult(t.getName(), e)));
        threads.add(thread);
        return thread;
    }

    private Thread getThread(Runnable r) {
        return new Thread(r, String.format(NAME_THREAD_PATTERN, name, counter.getAndIncrement())) {
            @Override
            public void run() {
                super.run();
                results.add(new FinishedThreadResult(this.getName()));
            }
        };
    }
}
