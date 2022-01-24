package com.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

public class IpDfs {
    public static void main(String[] args) {
        String test="19216801";
        IpDfs ipDfs = new IpDfs();
        System.out.println(ipDfs.restoreIpAddresses(test));
    }


    List<String> list = new ArrayList<>();
    StringBuilder sb = new StringBuilder();

    public List<String> restoreIpAddresses(String s) {
        //长度不够，或者超出范围直接返回
        if (s.length() < 4 || s.length() > 12) {
            return list;
        }
        //方便操作，将 String 转为 StringBuilder
        sb.append(s);
        backTracking(sb, 0, 0);
        return list;
    }

    private void backTracking(StringBuilder sb, int startIndex, int pointNum) {
        //逗号数量为3时，结束分隔
        if (pointNum == 3) {
            //判断第四段子字符串是否合法
            if (isValid(sb.toString(), startIndex, sb.length() - 1)) {
                list.add(sb.toString());
            }
        }
        for (int i = startIndex; i < sb.length(); i++) {
            if (isValid(sb.toString(), startIndex, i)) {
                //在i的后面插入一个逗点
                sb.insert(i + 1, ".");
                //插入逗点后，下一个子串的起始位置为i+2
                backTracking(sb, i + 2, ++pointNum);
                //回溯删掉逗点
                pointNum--;
                sb.deleteCharAt(i + 1);
            } else {
                break;
            }
        }
    }
    //判断子字符串是否合法
    private boolean isValid(String s, int start, int end) {

        System.out.println("isVaild:"+s);
        if (start > end) {
            return false;
        }
        //0开头的数字不合法
        if (s.charAt(start) == '0' && start != end) {
            return false;
        }
        int num = 0;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') {
                return false;
            }
            num = num * 10 + s.charAt(i) - '0';
            if (num > 255) {
                return false;
            }
        }
        return true;
    }
}
//————————————————
//        版权声明：本文为CSDN博主「南淮北安」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/nanhuaibeian/article/details/116598229