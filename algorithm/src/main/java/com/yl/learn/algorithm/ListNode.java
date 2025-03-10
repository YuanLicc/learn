package com.yl.learn.algorithm;

public class ListNode {
     public int val;
     public ListNode next;
     
     public ListNode(){}
     public ListNode(int x) { val = x; }
     
     
     public String toString1() {
          ListNode tmp = this;
          StringBuilder sb = new StringBuilder().append(val);
          tmp = tmp.next;
          
          while (tmp != null) {
               sb.append("->").append(tmp.val);
               tmp = tmp.next;
          }
          
          return sb.toString();
     }
     
     
     public static class Builder {
          
          public static ListNode build(int[] array) {
               assert array != null && array.length > 0;
               
               ListNode head = new ListNode(array[0]);
               ListNode tmp = head;
               
               for(int i = 1; i < array.length; i++) {
                    tmp.next = new ListNode(array[i]);
                    tmp = tmp.next;
               }
               
               return head;
          }
          
     }
     
     
}
