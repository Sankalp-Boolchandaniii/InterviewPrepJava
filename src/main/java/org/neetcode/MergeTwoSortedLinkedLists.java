package org.neetcode;

import org.utils.ListNode;

public class MergeTwoSortedLinkedLists {

    public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        if (head1==null || head2==null){
            return head1==null?head2:head1;
        }
        if (head1.val<=head2.val){
            head1.next=mergeTwoLists(head1.next, head2);
            return head1;
        } else {
            head2.next=mergeTwoLists(head2.next, head1);
            return head2;
        }
    }
}
