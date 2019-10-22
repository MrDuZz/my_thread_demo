package cn.dpy.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


/**
 * @Author: dupinyan
 * @Description: 线程池demo
 * @Date: 2019/10/22 11:23
 * @Version: 1.0
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        new ThreadPoolDemo().execute();
    }

    public void execute() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(12, 12, 2000, TimeUnit.MICROSECONDS,
                new ArrayBlockingQueue<Runnable>(1000));
        List<Handler> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(new Handler(String.valueOf(i)));
        }

        try {
            // 回按照添加到List的次序返回，改方法阻塞至所有任务完成
            List<Future<String>> results = executor.invokeAll(list);
            results.stream().forEach( future -> {
                try {
                    System.out.println("获取到的东西" + future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 关闭线程池
        executor.shutdown();

    }


    private class Handler implements Callable<String> {
        // 模拟传参，实际可为对象类型
        String value;
        public Handler(String value) {
            this.value = value;
        }

        @Override
        public String call() {
            try {
                Thread.sleep((long) (Math.random()*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 模拟返回参数，实际可为对象类型
            System.out.println("返回参数：" + value);
            return value + "value";
        }
    }

}
