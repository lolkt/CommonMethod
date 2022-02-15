package com.leetcode.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 977.有序数组的平方
 * 力扣题目链接(opens new window)
 *
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 *
 * 示例 1： 输入：nums = [-4,-1,0,3,10] 输出：[0,1,9,16,100] 解释：平方后，数组变为 [16,1,0,9,100]，排序后，数组变为 [0,1,9,16,100]
 *
 * 示例 2： 输入：nums = [-7,-3,2,3,11] 输出：[4,9,9,49,121]
 *
 * #思路
 */
public class sortedSquares {

    @Test
    public void test() {
        int[] nums = {-3, -1, 0, 2, 4};
        int[] ints = sortedSquares(nums);
        System.out.println(Arrays.toString(ints));
    }

    public int[] sortedSquares(int[] nums) {
        int right = nums.length - 1;
        int left = 0;
        int[] result = new int[nums.length];
        int index = result.length - 1;
        while (left <= right) {

            System.out.println("left="+nums[left] * nums[left]+" right="+nums[right] * nums[right]);
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                result[index--] = nums[left] * nums[left];
                ++left;
            } else {
                result[index--] = nums[right] * nums[right];
                --right;
            }
        }
        return result;
    }
}