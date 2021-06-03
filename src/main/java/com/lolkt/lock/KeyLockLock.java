package com.lolkt.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * key lock 锁. 没有 key 添加, 释放锁不清除 keys, 在 val 处操作
 * @author hx
 * @version 创建时间：2020年9月15日  上午11:33:07
 */
public class KeyLockLock {
	/** lock 锁. 没有 key 添加, 在 val 处操作*/
	private Map<String, Lock> keys = new HashMap<>();
	
	private KeyLockLock() {}
	/**
	 * 获取 key lock 锁
	 * <pre>
	 * @author hx
	 * @version 创建时间：2020年9月15日  上午11:53:50
	 * @return
	 * </pre>
	 */
	public static KeyLockLock getKeyLock() {
		KeyLockLock keyLock = new KeyLockLock();
		return keyLock;
	}
	/**
	 * 根据 key 获取 lock
	 *     自行处理 Lock 锁及释放
	 * <pre>
	 * @author hx
	 * @version 创建时间：2020年9月15日  上午11:54:01
	 * @param key
	 * @return
	 * </pre>
	 */
	public Lock getLock(String key) {
		Lock lock = keys.get(key);
		if (null != lock) {
			return lock;
		}
		synchronized (key.intern()) {
			lock = keys.get(key);
			if (null == lock) {
				keys.put(key, lock = new ReentrantLock());
			}
		}
		return lock;
	}
}
