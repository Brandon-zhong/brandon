package com.spirngboot.utils;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author brandon
 * Created on 2018-12-10.
 * desc: 线程池工具类,用线程池来管理线程
 */
public class ThreadPoolUtil {

    private static final Logger log = LoggerFactory.getLogger(ThreadPoolUtil.class);

    private static final Integer POOL_SIZE = 10;


    private static ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(POOL_SIZE,
            new BasicThreadFactory.Builder().namingPattern("thead-pool-scheduled-%d").daemon(true).build());


    /**
     * 通过线程池执行线程任务
     *
     * @param task 要指定的线程任务
     */
    public static void executorTask(Runnable task) {
        threadPool.execute(task);
        log.debug("【线程池任务】线程池中线程数：" + threadPool.getPoolSize());
        log.debug("【线程池任务】队列中等待执行的任务数：" + threadPool.getQueue().size());
        log.debug("【线程池任务】已执行完任务数：" + threadPool.getCompletedTaskCount());
    }

    public static Future submitTask(Callable task) {
        Future submit = threadPool.submit(task);
        return submit;
    }


    /**
     * 关闭线程池
     */
    public static void shutdown() {
        log.debug("shutdown threadPool...");
        threadPool.shutdown();
        try {
            if (!threadPool.isTerminated()) {
                log.debug("直接关闭失败[" + threadPool.toString() + "]");
                threadPool.awaitTermination(3, TimeUnit.SECONDS);
                if (threadPool.isTerminated()) {
                    log.debug("成功关闭[" + threadPool.toString() + "]");
                } else {
                    log.debug("[" + threadPool.toString() + "]关闭失败，执行shutdownNow...");
                    if (threadPool.shutdownNow().size() > 0) {
                        log.debug("[" + threadPool.toString() + "]没有关闭成功");
                    } else {
                        log.debug("shutdownNow执行完毕，成功关闭[" + threadPool.toString() + "]");
                    }
                }
            } else {
                log.debug("成功关闭[" + threadPool.toString() + "]");
            }
        } catch (InterruptedException e) {
            log.warn("接收到中断请" + threadPool.toString() + "停止操作");
        }
    }


}
