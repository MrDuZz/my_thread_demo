package cn.dpy.thread.controller;

import cn.dpy.thread.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2019/10/19 11:26
 * @Version: 1.0
 */
@RestController
@RequestMapping("thread")
@Slf4j
public class ThreadPoolController extends BaseController {


    @Autowired
    private AsyncService asyncService;


    @RequestMapping(value = "test", method = RequestMethod.GET)
    public MessageResult getThread() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
                asyncService.executeAsync();
            } catch (InterruptedException e) {
                log.info("---->线程出现异常---->", e);
            }
        }
        return success();
    }

    @RequestMapping(value = "myName", method = RequestMethod.POST)
    public MessageResult test(String myName) {
        log.info("myName={}", myName);
        return success();
    }

}
