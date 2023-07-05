package com.nick.spring17.utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 雪花算法生成器
 *
 * @author wjw
 * Created by wjw on 2016/11/15.
 */
@Component
public class SnowFlakeKeyGen implements EnvironmentAware {

    public SnowFlakeKeyGen() {
    }

    public SnowFlakeKeyGen(long workerId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
        }
        this.workerId = workerId;
    }

    @Override
    public void setEnvironment(Environment environment) {
        workerId = NumberUtils.toLong(environment.getProperty("workerId"));
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
        }
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }
        return nextId(timestamp);
    }

    public synchronized String nextStringId() {
        return nextId() + "";
    }

    private synchronized long nextId(long time) {
        long timestamp = time;
        if (lastTimestamp == timestamp) {
            long sequenceMask = 63L;
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = timestamp % 9;
        }

        lastTimestamp = timestamp;
        return (timestamp - TWEPOCH << TIMESTAMP_LEFT_SHIFT) | (workerId << WORKER_ID_SHIFT) | sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 根据指定时间生成ID
     *
     * @return 当前时间
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * Worker Id 位数
     */
    private static final long WORKER_ID_BITS = 6L;

    /// private static final long MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS);

    /**
     * 最大Worker Id
     */
    private static final long MAX_WORKER_ID = 63L;

    private static final long SEQUENCE_BITS = 6L;

    private static final long WORKER_ID_SHIFT = 6L;

    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    ///private final long sequenceMask = -1L ^ (-1L << SEQUENCE_BITS);

    private long workerId;

    private long sequence = 0L;

    private long lastTimestamp = -1L;

    private final static long TWEPOCH = 1452866247339L;
}
