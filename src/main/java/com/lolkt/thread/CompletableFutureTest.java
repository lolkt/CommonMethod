package com.lolkt.thread;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureTest {


    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception {

        completableFuture2();
    }


    private static void completableFuture1() throws Exception {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "aa";
        }, executor);


        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "bb";
        });

        //等待所有的异步任务全部完成
        CompletableFuture.allOf(completableFuture1, completableFuture2).get();

        System.out.println(completableFuture1.get() + "   " + completableFuture2.get());

    }


    private static void completableFuture2() throws Exception {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "aa";
        }, executor);


        CompletableFuture<Void> completableFuture2 = completableFuture1.thenAcceptAsync((res) -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(res + "bb");
        });
        CompletableFuture.allOf(completableFuture1, completableFuture2).get();

        System.out.println(completableFuture1.get() + "   " + completableFuture2.get());


    }


}
