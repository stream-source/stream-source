package com.qxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author:wx
 * @Date:2020/5/6 14:24
 */
@Configuration
//@EnableAsync
public class ThreadExecutorConfig {

    /**
     * 手动创建线程池参数：
     * 1，核心线程数；
     * 2，最大线程数；
     * 3，线程空闲时间；
     * 4，时间单位；
     * 5，缓冲队列；
     * 6，拒绝策略
     * */
    private static final int CORE_POOL_SIZE = 2;
    private static final int MAX_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 1000;
    private static final int QUEUE_CAPACITY = 6;

    private static final String THREAD_NAME_PREFIX = "streamSourceExecutor-";


    @Bean
    public Executor taskThreadExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        //线程名字前缀
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        //达到最大线程数量时，不新建线程中执行任务，而是由调用者所在当前线程执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();

        return executor;
    }


}
