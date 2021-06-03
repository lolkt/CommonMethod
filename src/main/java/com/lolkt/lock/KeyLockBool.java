package com.lolkt.lock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * key 普通锁. 用完 keys
 * @author hx
 * @version 创建时间：2020年9月15日  上午11:32:10
 */
public class KeyLockBool {
	/** 普通锁. 用完清除*/
	private ConcurrentMap<String, Boolean> keys = new ConcurrentHashMap<>();
	
	private KeyLockBool() {}
	/**
	 * 创建获取 key 普通锁
	 * <pre>
	 * @author hx
	 * @version 创建时间：2020年9月15日  上午11:32:51
	 * @return
	 * </pre>
	 */
	public static KeyLockBool getKeyLockBool() {
		KeyLockBool keyLock = new KeyLockBool();
		return keyLock;
	}
	/**
	 * 根据 key 获取锁
	 * <pre>
	 * @author hx
	 * @version 创建时间：2020年9月15日  上午11:29:47
	 * @param key
	 * @return
	 * </pre>
	 */
	public boolean getLock(String key) {
		return null == keys.putIfAbsent(key, true);
	}
	/**
	 * 释放锁
	 * <pre>
	 * @author hx
	 * @version 创建时间：2020年9月15日  上午11:32:01
	 * @param key
	 * </pre>
	 */
	public void unlock(String key) {
		if (null == key) {
			return;
		}
		keys.remove(key);
	}
}
