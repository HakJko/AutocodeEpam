package com.epam.rd.autotasks;

import java.util.List;
import java.util.concurrent.ThreadFactory;

public interface ThreadUnion extends ThreadFactory
{
    int totalSize();
    int activeSize();

    void shutdown();
    void awaitTermination();

    boolean isShutdown();
    boolean isFinished();

    List<FinishedThreadResult> results();

    static ThreadUnion newInstance(String name){
        return new ThreadUnionImpl(name);
    }
}
