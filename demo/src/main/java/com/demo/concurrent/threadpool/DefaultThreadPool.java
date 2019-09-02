package com.demo.concurrent.threadpool;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author brandon
 * Created on 2019-08-28.
 * desc:
 **/
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    //最大线程数
    private static final int MAX_WORKER_NUMBERS = 10;
    //默认线程数
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    //最小线程数
    private static final int MIN_WORKER_NUMBERS = 1;
    //任务列表，会将任务插入到这个列表里，工作线程从这个列表中取任务执行
    private final LinkedList<Job> jobs = new LinkedList<>();
    //这是一个工作者列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    //工作者数量
    private int workerNum = DEFAULT_WORKER_NUMBERS;
    //线程编号生成
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        //初始化线程池
        initializeWokers(DEFAULT_WORKER_NUMBERS);
    }

    private void initializeWokers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            new Thread(worker, "ThreadPool-Worker-" + threadNum.
                    incrementAndGet()).start();
        }
    }

    @Override
    public void execute(Job job) {
        //添加一个任务
        if (job == null) {
            return;
        }
        synchronized (jobs) {
            jobs.addLast(job);
            jobs.notify();
        }
    }

    @Override
    public void shutdown() {
        //关闭线程池
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        //增加新的工作者
        synchronized (jobs) {
            //判断是否超过最大线程数限制
            if (num + this.workerNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWokers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorker(int num) {
        //移除工作者
        synchronized (jobs) {
            //判断是否大于现有的工作者数量
            if (num > this.workerNum) {
                throw new IllegalArgumentException("beyond workNum");
            }
            //移除数量
            for (int i = 0; i < num; i++) {
                Worker worker = workers.get(i);
                if (workers.remove(worker)) {
                    worker.shutdown();
                }
            }
            //修改数量
            this.workerNum -= num;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    class Worker implements Runnable {
        private volatile boolean running = true;

        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    try {
                        job.run();
                    } catch (Exception ex) {
                    }
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }
}
