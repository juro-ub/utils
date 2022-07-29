package de.jro.moduls.utils;

import java.util.*;
import java.util.concurrent.*;

public class SingletonExecutorServiceLoader {

    private static Map<String, ExecutorService> STORE = new HashMap<String, ExecutorService>();
    private static Map<String, ScheduledExecutorService> STORE_SES = new HashMap<String, ScheduledExecutorService>();
    private static final int poolsize = 40;

    public static ExecutorService getFixedThreadPool() throws Exception {
        //create context
        ExecutorService result;
        String name = "fixedThreadPool";
        synchronized (STORE) {
            result = STORE.get(name);
            if (result == null) {
                System.out.println("creating threadpool for thread " + name + " size: " + poolsize);
                result = Executors.newFixedThreadPool(poolsize);
                STORE.put(name, result);
            }
        }
        return result;
    }

    public static ScheduledExecutorService getSingleThreadScheduled() throws Exception {
        //create context
        ScheduledExecutorService result;
        String name = "singleThreadPool";
        synchronized (STORE_SES) {
            result = STORE_SES.get(name);
            if (result == null) {
                System.out.println("creating single schedule for thread " + name);
                result = Executors.newSingleThreadScheduledExecutor();
                STORE_SES.put(name, result);
            }
        }
        return result;
    }
}
