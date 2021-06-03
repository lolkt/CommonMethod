package com.plf.common.id;

/**
 * 雪花算法
 * 生成一个64bit的整性数字 第一位符号位固定为0，41位时间戳，10位workId，12位序列号
 * 位数可以有不同实现
 * 优点：每个毫秒值包含的ID值很多，不够可以变动位数来增加，性能佳（依赖workId的实现）。
 *      时间戳值在高位，中间是固定的机器码，自增的序列在低位，整个ID是趋势递增的。
 *      能够根据业务场景数据库节点布置灵活挑战bit位划分，灵活度高。
 * <p>
 * 缺点：强依赖于机器时钟，如果时钟回拨，会导致重复的ID生成，所以一般基于此的算法发现时钟回拨， 都会抛异常处 理，阻止ID生成，这可能导致服务不可用。
 */
public class SnowFlakeIdWorker {


    //因为二进制里第一个 bit 为如果是 1，那么都是负数，但是我们生成的 id 都是正数，所以第一个 bit 统一都是 0。

    //机器ID  2进制5位  32位减掉1位 31个
    private long workerId;
    //机房ID 2进制5位  32位减掉1位 31个
    private long datacenterId;
    //代表一毫秒内生成的多个id的最新序号  12位 4096 -1 = 4095 个
    private long sequence;
    //设置一个时间初始值    2^41 - 1   差不多可以用69年
    private long twepoch = 1585644268888L;
    //5位的机器id
    private long workerIdBits = 5L;
    //5位的机房id
    private long datacenterIdBits = 5L;
    //每毫秒内产生的id数 2 的 12次方
    private long sequenceBits = 12L;
    // 这个是二进制运算，就是5 bit最多只能有31个数字，也就是说机器id最多只能是32以内
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    // 这个是一个意思，就是5 bit最多只能有31个数字，机房id最多只能是32以内
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    private long workerIdShift = sequenceBits;
    private long datacenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private long sequenceMask = -1L ^ (-1L << sequenceBits);
    //记录产生时间毫秒数，判断是否是同1毫秒
    private long lastTimestamp = -1L;

    public long getWorkerId() {
        return workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }


    public SnowFlakeIdWorker(long workerId, long datacenterId, long sequence) {

        // 检查机房id和机器id是否超过31 不能小于0
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }

        if (datacenterId > maxDatacenterId || datacenterId < 0) {

            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    // 这个是核心方法，通过调用nextId()方法，让当前这台机器上的snowflake算法程序生成一个全局唯一的id
    public synchronized long nextId() {
        // 这儿就是获取当前时间戳，单位是毫秒
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {

            System.err.printf(
                    "clock is moving backwards. Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }

        // 下面是说假设在同一个毫秒内，又发送了一个请求生成一个id
        // 这个时候就得把seqence序号给递增1，最多就是4096
        if (lastTimestamp == timestamp) {

            // 这个意思是说一个毫秒内最多只能有4096个数字，无论你传递多少进来，
            //这个位运算保证始终就是在4096这个范围内，避免你自己传递个sequence超过了4096这个范围
            sequence = (sequence + 1) & sequenceMask;
            //当某一毫秒的时间，产生的id数 超过4095，系统会进入等待，直到下一毫秒，系统继续产生ID
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }

        } else {
            sequence = 0;
        }
        // 这儿记录一下最近一次生成id的时间戳，单位是毫秒
        lastTimestamp = timestamp;
        // 这儿就是最核心的二进制位运算操作，生成一个64bit的id
        // 先将当前时间戳左移，放到41 bit那儿；将机房id左移放到5 bit那儿；将机器id左移放到5 bit那儿；将序号放最后12 bit
        // 最后拼接起来成一个64 bit的二进制数字，转换成10进制就是个long型
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) | sequence;
    }

    /**
     * 当某一毫秒的时间，产生的id数 超过4095，系统会进入等待，直到下一毫秒，系统继续产生ID
     *
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(long lastTimestamp) {

        long timestamp = timeGen();

        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    //获取当前时间戳
    private long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * main 测试类
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(1 & 4596);
        System.out.println(2 & 4596);
        System.out.println(6 & 4596);
        System.out.println(6 & 4596);
        System.out.println(6 & 4596);
        System.out.println(6 & 4596);
        SnowFlakeIdWorker worker = new SnowFlakeIdWorker(1,1,1);
		for (int i = 0; i < 22; i++) {
			System.out.println(worker.nextId());
		}
    }
}