package com.lolkt.callback;

/**
 * 使用object.wait  object.notify 实现异步转同步
 */
public class TestRecord {


    public static void main(String[] args) {
       com.lolkt.callback.DeferredResultHolder resultHolder = new DeferredResultHolder();

        String channelId = "1";
       com.lolkt.callback.ResultSync result = resultHolder.put(com.lolkt.callback.DeferredResultHolder.CALLBACK_CMD_RECORDINFO + channelId);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                RequestMessage message = new RequestMessage();
                message.setDeviceId("1");
                message.setType(com.lolkt.callback.DeferredResultHolder.CALLBACK_CMD_RECORDINFO);
                message.setData(new Record("aa", 10));

                resultHolder.invokeResult(message);
            }
        }).start();

        if (result.get() == null) {
            System.out.println("-null");
            return;
        }
        Record mUser = (Record) result.get();
        System.out.println("-" + mUser.toString());
    }



    static class Record {
        String name;
        int time;

        public Record(String name, int age) {
            this.name = name;
            this.time = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + time +
                    '}';
        }
    }
}
