package com.leetcode.link;

import org.junit.Test;

public class reversal {


    /**
     * 翻转链表
     * 时间复杂度O(N)
     * 空间复杂度O(1)
     */
    public static Node ReverseList2(Node head) {
        if (head == null) {
            return null;
        }

        // 指针1：当前节点
        Node currentNode = head;
        // 指针2：当前节点的前一个节点
        Node prevNode = null;

        while (currentNode != null) {
            // 指针3：当前节点的后一个节点
            Node nextNode = currentNode.next;
            // 将当前节点的后一个节点指向前一个节点
            currentNode.next = prevNode;
            // 将前一个节点指向当前节点
            prevNode = currentNode;
            // 将当前节点指向后一个节点
            currentNode = nextNode;
        }

        return currentNode;
    }

    /**
     * 递归
     * @param head
     * @return
     */
    public static Node ReverseList3(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        System.out.println("1====="+head.next.data);
        Node new_head = ReverseList3(head.next);
        System.out.println("2====="+head.next.data);
        head.next.next = head;
        head.next = null;
        return new_head;

    }

    /**
     * 5.4.3
     */

    @Test
    public void ReverseTest1() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        Node newHead = ReverseList3(node1);

    }
}
