package cn.dpy.thread.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2019/10/19 11:42
 * @Version: 1.0
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {


    /**
     * 核心线程数
     */
    private int corePoolSize;


    /**
     * 最大线程数
     */
    private int maxPoolSize;

    /**
     * 配置队列大小
     */
    private int queueCapacity;

    /**
     * 配置线程池中的线程的名称前缀
     */
    private int keepAliveSeconds;

    /**
     * rejection-policy：当pool已经达到max size的时候，如何处理新任务
     * CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
     */
    private ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();

    private String threadNamePrefix = "AsyncExecutorThread-";

    @Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(100);
        executor.setKeepAliveSeconds(60);
        executor.setRejectedExecutionHandler(callerRunsPolicy);
        executor.setThreadNamePrefix("du-test");
        // 执行初始化
        executor.initialize();
        return executor;
    }

}
