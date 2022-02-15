package com.leetcode.array;

import org.junit.Test;

import java.util.Arrays;

public class sortArray {


    @Test
    public void test() {
        int[] arr = {4, 3, 2, 1};
        bubble(arr);
        System.out.println(Arrays.toString(arr));

    }


    @Test
    public void test2() {
        int[] arr = {3, 22, 5};
        // Arrays.sort(arr);
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 快速排序
     *
     * @param a
     * @param left
     * @param right
     */
    private void sort(int[] a, int left, int right) {
        /*
         * Traditional (without sentinel) insertion sort,
         * optimized for server VM, is used in case of
         * the leftmost part.
         */
        for (int i = left, j = i; i < right; j = ++i) {
            int ai = a[i + 1];

            System.out.println(ai + " " + a[j]);
            while (ai < a[j]) {
                a[j + 1] = a[j];
                if (j-- == left) {
                    break;
                }
            }
            a[j + 1] = ai;
        }
    }


    /**
     * 冒泡
     *
     * @param arr
     */
    public void bubble(int[] arr) {

        int temp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                System.out.println(arr[j] + " " + arr[j+1]);
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            System.out.println(Arrays.toString(arr));
        }
    }


}
