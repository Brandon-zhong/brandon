package com.demo.concurrent.threadpool;

/**
 * @author brandon
 * Created on 2019-08-28.
 * desc:
 **/
public interface ThreadPool<Job extends Runnable> {

    //执行一个任务，这个任务需要实现runnable接口
    void execute(Job job);

    //关闭线程池
    void shutdown();

    //增加工作者数量
    void addWorkers(int num);

    //减少工作者数量
    void removeWorker(int num);

    //获取正在等待的任务数量
    int getJobSize();

}
