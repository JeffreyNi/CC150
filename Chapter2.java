/*************************************************************
 *
 *  This class implements all problems in Chapter2, Cracking the 
 *  Code interview. Chapter2 mainly deals with linked list 
 *  manipulation.
 *
 *  @author Jiafeng Ni
 *
 *************************************************************/

import java.util.*;

public class Chapter2 {

    static class ListNode {
	int val;
	ListNode next;
	
	public ListNode(int val) {
	    this.val = val;
	    this.next = null;
	}

	public ListNode(int val, ListNode next) {
	    this.val = val;
	    this.next = next;
	}

	public String toString() {
	    if (this == null) {return "";}

	    ListNode cur = this;
	    StringBuilder res = new StringBuilder();
	    
	    if (cur != null) {
		res.append(cur.val);
		cur = cur.next;
	    }

	    while (cur != null) {
		res.append("->");
		res.append(cur.val);
		cur = cur.next;
	    }

	    return res.toString();
	}

    }


    /**
     * 2.1 Write code to remove duplicates from an unsorted list.
     * 
     * FOLLOW UP:
     * How would you solve this problem if a temporary buffer is not
     * allowed?
     *
     * This implementation uses an extra buffer (HashSet) to keep track
     * of values that has already existed.
     *
     * Remove duplicates in a linked list
     * @param head the header of the linked list ot be manipulated
     */
    public static void removeDuplicates(ListNode head) {
	ListNode node = head;
	ListNode previous = null;
	HashSet<Integer> set = new HashSet<Integer>();

	while (node != null) {
	    if (set.contains(node.val)) {
		previous.next = node.next;
	    } else {
		set.add(node.val);
		previous = node;
	    }
	    node = node.next;
	}
    }

    /**
     * Implementation of the same problem above without using a buffer.
     * Time complexity then will become O(N^2)
     */
    public static void removeDuplicates2(ListNode head) {
	if (head == null) {return;}
	
	ListNode previous = head;
	ListNode current = head.next;
	
	while (current != null) {
	    ListNode runner = head;
	    while (runner != current) {
		if (runner.val == current.val) {
		    previous.next = current.next;
		    break;
		} else {
		    runner = runner.next;
		}
	    }

	    if (runner == current){
		previous = current;
	    }

	    current = current.next;
	}
    }

    /**
     * 2.2 Implement an algorithm to find the nth to last element of a singly
     * linked list.
     * 
     * Returns the nth to last element of a linked list.
     * @param head the header of the linked list to be searched
     * @return the nth to last element of a linked list
     */
    public static ListNode nthToLast(ListNode head, int n) {
	if (head == null || n < 1)  {return null;}
	
	ListNode first = head;
	ListNode second = head;
	
	for (int i = 1; i < n; i++) {
	    if (second == null) { return null; }
	    second = second.next;
	}
	
	while (second != null) {
	    first = first.next;
	    second = second.next;
	}

	return first;
    }

    /**
     * 2.3 Implement an algorithm to delete a node in the middle of a
     * singly linked list, given only access to that node.
     * 
     * Example:
     * Input: the node 'c' from the linked list a->b->c->d->e
     * Result: nothing returned, but the new linked list looks like a->b->d->e
     * 
     * Notice this function can do nothing if the middle node is the
     * last one in the list     
     *
     * Remove the given node in the linked list. Return false if 
     * failed (in case middle node is null or the last node in the list)
     * @param middle the middle node in the linked list
     
     */
    public static boolean deleteMiddle(ListNode middle) {
	if (middle == null || middle.next == null) {
	    return false;
	}
	
	middle.val = middle.next.val;
	middle.next = middle.next.next;
	return true;
    }


    /**
     * 2.4 You have two numbers represented by a linked list, where each node
     * contains a single digit. The digits are stored in reverse order, such 
     * that 1's digit is at hte head of the list. Write a function that add 
     * the two numbers and returns the sum as a linked list.
     *
     * Example:
     * Input: (3->1->5), (5->9->2)
     * Output: 8->0->8
     * 
     * Add two numbers represented by two linked list (reversed order), return
     * the resulted represented using a new liked list.
     * @param 
     */
    public static ListNode addList(ListNode num1, ListNode num2) {
	int tmp = 0;
	ListNode fake = new ListNode(0);
	ListNode previous = fake;

	while (num1 != null || num2 != null) {
	    if (num1 != null) {
		tmp += num1.val;
		num1 = num1.next;
	    }
	    
	    if (num2 != null) {
		tmp += num2.val;
		num2 = num2.next;
	    }

	    previous.next = new ListNode(tmp % 10);
	    previous = previous.next;
	    tmp /= 10;
	}

	if (tmp != 0) {
	    previous.next = new ListNode(tmp);
	}
	
	return fake.next;
    }
    

    /**
     * 2.5 Given a circular linked list, implement an algorithm which 
     * returns node at the beginning of the loop.
     *
     * Definition:
     * Circular linked list: A (corrupt) linked list in which a node's 
     * next pointer points to an earlier node, so as to make a loop in
     * the Linked list
     *
     * Example:
     * Input: A->B->C->D->E->C (the same C as earlier)
     * Output: C
     *
     * This implementation calculates the length of the circle.
     * 
     * Returns the node in the linked list where the cycle begins.
     * @head the header of the linked list having a cycle in it
     * @return the node in the linked list where the cycle begins
     */
    public static ListNode findBeginning(ListNode head) {
	if (head == null) {return null;}

	ListNode slow = head;
	ListNode faster = head.next;
	
	while (slow != faster) {
	    slow = slow.next;
	    faster = faster.next.next;
	}

	int len = 1;
	faster = faster.next;
	while (slow != faster) {
	    faster = faster.next.next;
	    slow = slow.next;
	    len++;
	}

	slow = head;
	faster = head;

	while (len > 0) {
	    faster = faster.next;
	    len--;
	}

	while (slow != faster) {
	    slow = slow.next;
	    faster = faster.next;
	}
	
	return slow;
    }

    /**
     * Different implementation of the same problem as above.
     * 
     * Idea:
     * 1. Using two pointers, one moves forward one step at a
     *    time, the other moves forward two steps at a time. 
     *    It is guaranteed that they will meet if there is a 
     *    cycle, and during the first round of the slower pointer
     *    in the cycle.
     * 2. Suppose number of nodes before the cycle is L1, number
     *    of nodes in the cycle is L2. When two pointers meet with
     *    each other, slower pointer covers L1 + l1 nodes, faster
     *    pointer covers L1 + l2 nodes. Then, we have the functon:
     *       
     *        L1 + l2 = 2 * (L1 + l1)
     *             l2 = L1 + 2l1
     *
     *    Since the faster pointer is l1 nodes ahead of beginning 
     *    node in the cycle, we also have
     *
     *        l2 - l1 = k * L2
     *        L1 + l1 = k * L2
     * 3. If we reset the slower pointer to the header, the faster 
     *    pointer will be L1 + l1 nodes ahead of the slower one. Then 
     *    we set the speed of both pointers to one. When slower pointer
     *    gets to the beginning node of the cycle, it covers L1 nodes.
     *    The same number of nodes will be covered by the faster pointer.
     *    Since we have L1 = k * L2 - l1, and at the beginning the faster
     *    pointer is l1 nodes ahead of beginning node of the cycle.
     *    It is obvious that faster pointer will also be at the beginning
     *    node of the cycle after L1 steps. (i.e. they will meet again!)
     */
    public static ListNode findBeginning2(ListNode head) {
	if (head == null) {return null;}
	
	ListNode faster = head;
	ListNode slow = head;

	while (faster.next != null && faster.next.next != null) {
	    slow = slow.next;
	    faster = faster.next.next;
	    if (slow == faster) {break;}
	}
	
	if (faster == null) {return null;}

	slow = head;
	while (slow != faster) {
	    slow = slow.next;
	    faster = faster.next;
	}
	
	return slow;
    }

    public static void main(String[] args) {
	ListNode node1 = new ListNode(9);
	ListNode node2 = new ListNode(8, node1);
	ListNode node3 = new ListNode(7, node2);
	ListNode head1 = new ListNode(6, node3);
	node1.next = node3;

	ListNode node4 = new ListNode(3);
	ListNode node5 = new ListNode(3, node4);
	ListNode node6 = new ListNode(2, node5);
	ListNode head2 = new ListNode(1, node6);

	System.out.println(findBeginning2(head1).val);
    }

}