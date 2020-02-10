package com.example.ruifight_3.saolouruifight.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * l只有非核心线程，最大线程数非常大，所有线程都活动时，会为新任务创建新线程，否则利用空闲线程（60s空闲时间，过了就会被回收，所以线程池中有0个线程的可能）处理任务。
 * （2）任务队列SynchronousQueue相当于一个空集合，导致任何任务都会被立即执行。
 * 比较适合执行大量的耗时较少的任务
 */
public class ThreadExecutorService {

    public static ExecutorService newCachedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    }
}
