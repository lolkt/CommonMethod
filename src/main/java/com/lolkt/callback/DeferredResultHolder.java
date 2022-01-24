package com.lolkt.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DeferredResultHolder {
    private static Logger log = LoggerFactory.getLogger(DeferredResultHolder.class);

    public static final String CALLBACK_CMD_DEVICEINFO = "CALLBACK_DEVICEINFO";

    public static final String CALLBACK_CMD_CATALOG = "CALLBACK_CATALOG";

    public static final String CALLBACK_CMD_RECORDINFO = "CALLBACK_RECORDINFO";


    private Map<String,ResultSync> mapSleep = new ConcurrentHashMap<>();

    public ResultSync put(String key) {
       ResultSync result = new ResultSync();
        mapSleep.put(key, result);
        return result;
    }

    public void invokeResult(RequestMessage msg) {
       ResultSync result = mapSleep.get(msg.getId());
        if (result == null) {
            return;
        }
        log.info("invokeResult data={}", msg.getData());
        result.set(msg.getData());
    }
}
 