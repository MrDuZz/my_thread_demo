package cn.dpy.thread.service.impl;

import cn.dpy.thread.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2019/10/21 15:54
 * @Version: 1.0
 */
@Service
public class AsyncServiceImpl implements AsyncService {


    @Async("taskExecutor")
    @Override
    public void executeAsync() {
        try {
            System.out.println("当前运行的线程名称：" + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
