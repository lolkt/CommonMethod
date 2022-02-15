package com.leetcode.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 209.长度最小的子数组
 * 力扣题目链接(opens new window)
 * <p>
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
 * <p>
 * 示例：
 * <p>
 * 输入：s = 7, nums = [2,3,1,2,4,3] 输出：2 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 */
public class minSubArrayLen {

    @Test
    public void test() {
        int[] nums = {2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLen(7, nums));
    }


    // 滑动窗口
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= s) {
                result = Math.min(result, right - left + 1);
                sum -= nums[left++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    /**
     * 不是连续 子数组 错误
     * @param s
     * @param arr
     * @return
     */
    public int minSubArrayLenError(int s, int[] arr) {
        Arrays.sort(arr);

        for (int i = arr.length - 1; i >0; i--) {
            System.out.println(arr[i]);
            s = s - arr[i];
            if (s <= 0) {
                System.out.println("11111");
                return arr.length-i;
            }
        }
        return 0;
    }


}
