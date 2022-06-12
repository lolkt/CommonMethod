package com.leetcode.array;

public class quickSort {

    public static void quickSort(int[] arr, int low, int high) {
        if (arr.length <= 0) return;
        if (low >= high) return;
        int left = low;
        int right = high;

        int temp = arr[left];   //挖坑1：保存基准的值
        while (left < right) {
            while (left < right && arr[right] >= temp) {  //坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= temp) {   //坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;   //基准值填补到坑3中，准备分治递归快排
        quickSort(arr, low, left - 1);
        quickSort(arr, left + 1, high);
    }

    public static void main(String[] args) {
        int[] arr = {2, 8, 10, 6, 0, 12, 4};
        quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
/*————————————————
    版权声明：本文为CSDN博主「ddingddong~」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/weixin_42643931/article/details/107430790*/
}
