package com.leetcode.array;

import org.junit.Test;

/**
 * 704. 二分查找
 * 力扣题目链接(opens new window)
 * <p>
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 */
public class search {


    @Test
    public void test() {
        int[] nums = {-3, 0, 1, 2, 4, 5};
        System.out.println(search(nums, 2));
    }


    public int search(int[] nums, int target) {
        // 避免当 target 小于nums[0] nums[nums.length - 1]时多次循环运算
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }
        int left = 0, right = nums.length - 1;


        while (left <= right) {
            System.out.println("left=" + left + " right=" + right);

            int mid = left + ((right - left) / 2);
            System.out.println("mid=" + mid  );
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid - 1;
        }
        return -1;
    }
}
