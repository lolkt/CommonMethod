package com.leetcode.link;

public class mergeTwoList {


    public static Node mergeTwoList(Node head1, Node head2) {
        if (head1 == null && head2 == null) {
            return null;
        }
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }


        //合并后的链表
        Node head = null;
        if (head1.data > head2.data) {
            //把head较小的结点给头结点
            head = head2;
            //继续递归head2
            head.next = mergeTwoList(head1, head2.next);
        } else {
            head = head1;
            head.next = mergeTwoList(head1.next, head2);
        }
        return head;

    }



    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        /**
         * 1.3.5
         */
        node1.next = node3;
        node3.next = node5;

        /**
         * 2.4
         */
        node2.next = node4;
        Node node = mergeTwoList(node1, node2);
//        Node node = mergeTwoList2(node2, node1);
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

/*————————————————
    版权声明：本文为CSDN博主「田维常」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/o9109003234/article/details/84245783*/
}
