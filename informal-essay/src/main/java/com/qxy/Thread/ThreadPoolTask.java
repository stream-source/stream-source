package com.qxy.Thread;

import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wx
 * @date 2020/11/3 6:51 下午
 * 线程池执行多线程任务
 */
public class ThreadPoolTask {

    public static void main(String[] args) {
        List<String> idList = new LinkedList<>();
        for (int i =0; i < 2000; i ++) {
            idList.add("i" + i);
        }
        Map<String, Object> taskMap = new LinkedHashMap<>();
        idList.forEach(e -> taskMap.put(e, Boolean.FALSE));
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1));
        //多线程执行任务
        idList.forEach(id -> {
            Future<?> taskResult = threadPoolExecutor.submit(new HandlerTask(taskMap, id));
            try {
                taskResult.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //关闭线程池
        threadPoolExecutor.shutdown();
    }
}

class HandlerTask implements Runnable {
    private final Map<String, Object> taskMap;

    private final String id;

    public HandlerTask(Map<String, Object> taskMap, String id) {
        this.taskMap = taskMap;
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("start====" + Thread.currentThread().getName());
        handler(id);
        System.out.println("end======" + Thread.currentThread().getName());
    }

    private void handler(String id) {
        Object o = taskMap.get(id);
        if (!Boolean.TRUE.equals(o)) {
            taskMap.put(id, true);
            System.out.println("id ===== " + id);
        }
    }
}
