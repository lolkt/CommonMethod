package com.leetcode.dfs;

import java.util.Stack;

public class FreeCodesIpDfs {

    public static void main(String[] args) {
        String str = "19216801";
        dfs(str, -1, 1, new Stack<>());
    }


    public static void dfs(String str, int index, int level, Stack<String> res) {
        System.out.println("str=" + str + " index" + index + " level" + level);

        if (level == 5 || index == str.length() - 1) {
            if (level == 5 && index == str.length() - 1) {
                System.out.println("toString:" + res.toString());
            }
            return;
        }

        for (int i = 1; i < 4; i++) {
            System.out.println("2===start" + (index + 1) + " end" + (index + 1 + i));
            String x = str.substring(index + 1, index + 1 + i);
            System.out.println("x==" + x);

            if (Integer.parseInt(x) < 256 && ("0".equals(x) || !x.startsWith("0"))) {
                res.push(x);
                dfs(str, index + i, level + 1, res);
                res.pop();
            }
        }

    }
}
