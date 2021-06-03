package com.lolkt.lock;

import java.util.concurrent.locks.Lock;

public class TestLock {
    /**
     * 设备 ID 锁
     */
    private KeyLockLock deviceLocks = KeyLockLock.getKeyLock();


    public static void main(String[] args) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                TestLock testLock = new TestLock();
                String res = testLock.saveDeviceImgLock("aa", "cc");
                System.out.println("=="+res);
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                TestLock testLock = new TestLock();
                String res2 = testLock.saveDeviceImgLock("aa", "dd");
                System.out.println("=="+res2);
            }
        }).start();
    }


    /**
     * 保存设备图片
     * 同一时间只能有一个
     * <pre>
     * @author hx
     * @version 创建时间：2020年12月10日  下午3:31:33
     * @param deviceId
     * @param imgName 图片名称
     * @return
     *     如果返回 "" 空字符串可以再次获取
     *         抛出异常和返回 null 不能再次获取. RuntimeException 异常说明已经有获取的, 可以再次尝试访问 cdn 获取
     * </pre>
     */

    public String saveDeviceImgLock(String deviceId, String imgName) {
        Lock lock = deviceLocks.getLock(deviceId);
        boolean tryLock = lock.tryLock();
        String retPath = "";
        if (!tryLock) {
            throw new RuntimeException("已有正在保存中的图片请稍候再试");
        }
        try {
            retPath = saveDeviceImgPro(deviceId, imgName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return retPath;
    }

    private String saveDeviceImgPro(String deviceId, String imgName) {
        return deviceId;
    }
}
