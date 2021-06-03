package com.plf.redis;

/**
 * @ClassName: MyBloomFilter
 * @Description: 布隆过滤器用于判断一个元素是否在一个集合中，它有一定的误判率，4
 * 不存在的元素，一定不存在。存在的不一定真的存在，
 * 它使用的是数组，它的空间效率是一般算法的1/8左右
 *  布隆过滤器的核心思想：
 *  add 操作: 计算k个hash函数的值，把对应的结果映射到位数组上，将相应的位数组上的值 赋值为1
 *  contain 操作： 计算k个hash函数的值，判断k个所有的值是否都为1，如果都为1，返回true,如果有一个不等于0 则返回false
 *  参考：
 *  1.https://www.bilibili.com/video/BV1n5411s7Wb?from=search&seid=7498069184359593981
 *  2.https://www.cnblogs.com/xiaobaituyun/p/11011393.html
 * @Author: fuGuoWen
 * @Date: 2020/5/30 22:36
 * @Version: v1.0 文件初始创建
 */
public class MyBloomFilter {
    private byte[] data;
    private int[] slots;

    /** 初始化容器的大小 */
    public MyBloomFilter(int num) {
        this.data = new byte[num*2];
        slots=new int[3];
    }

    /**
     * @Description: 把对应的元素添加待BloomFilter
     * @Return
     * @Throws
     */
    public void add(Integer key){
        /** 计算hash1的函数取模以后的桶为 */
        int location1 = Math.abs(hash1(key) % data.length);
        /** 计算hash2的函数取模以后的桶为 */
        int location2 = Math.abs(hash2(key) % data.length);
        /** 计算hash3的函数取模以后的桶为 */
        int location3 = Math.abs(hash3(key) % data.length);
        /** 把对应的hash函数的桶位置为1 */
        data[location1]=1;
        data[location2]=1;
        data[location3]=1;

    }
    /**
     * @Description: 判断一个元素是否在布隆过滤器中
     *
     * @Return java.lang.Boolean  true:在布隆过滤器中  false: 不在布隆过滤器中
     * @Throws
     */
    public Boolean contain(Integer key){
        int location1 = Math.abs(hash1(key) % data.length);
        int location2 = Math.abs(hash2(key) % data.length);
        int location3 = Math.abs(hash3(key) % data.length);
        /** 只要有一个元素部位1，计算结果为0，返回false,否则 计算结果为1，返回true */
        return data[location1]*data[location2]*data[location3]==1;
    }
    /**
     * @Description: hash1 计算key的hash 值
     * @Return
     * @Throws
     */
    public int hash1(Integer key){
        return key.hashCode();
    }

    /**
     * @Description: hash2 计算key的hash 值
     * @Return
     * @Throws
     */
    public int hash2(Integer key){
        return key.hashCode()^(key.hashCode() >>>3);
    }

    /**
     * @Description: hash3 计算key的hash 值
     * @Return
     * @Throws
     */
    public int hash3(Integer key){
        return key.hashCode()^(key.hashCode() >>>16);
    }

}