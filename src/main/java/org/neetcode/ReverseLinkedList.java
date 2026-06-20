package org.neetcode;

import org.utils.ListNode;

public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        ListNode prev=null;
        ListNode curr=head;
        while(curr!=null){
            ListNode temp=curr.next;
            curr.next=prev;
            prev=curr;
            curr=temp;
        }
        return prev;        // why return prev?? because it points to current so that will be the last active node
    }

}