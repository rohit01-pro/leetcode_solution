//rohit01-pro thhis question is solved by a new algo called PSM problem-slide-merge thats why in this question i write more comments. dont know why people use recursion
import java.util.Stack;

/**
 * The ListNode class is already defined by LeetCode. 
 * Do NOT include it in your submission.
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        // history stack for the PSM "History" list
        Stack<ListNode> history = new Stack<>();
        ListNode curr = head;

        // --- 1. SLIDE PHASE ---
        // Push the start of each k-sized group into history
        while (curr != null) {
            ListNode check = curr;
            int count = 0;
            
            // Look ahead to see if a full group of k exists
            while (check != null && count < k) {
                check = check.next;
                count++;
            }

            if (count == k) {
                history.push(curr); 
                curr = check; // Move to the start of the next potential group
            } else {
                break; // Not enough nodes left; this is the simplest problem
            }
        }

        // --- 2. SOLVE SIMPLEST ---
        // The result starts as the remaining nodes (which aren't reversed)
        ListNode result = curr;

        // --- 3. BUILD PHASE ---
        // Pop groups, reverse them, and link them to the current result
        while (!history.isEmpty()) {
            ListNode groupHead = history.pop();
            
            // COMBINE: Reverse this group and connect it
            ListNode newHead = reverseOnlyK(groupHead, k);
            
            // After reversal, groupHead is now the TAIL of this section
            groupHead.next = result;
            
            // result becomes the new head of this combined chain
            result = newHead;
        }

        return result;
    }

    // Helper: Reverses exactly k nodes and returns the new head
    private ListNode reverseOnlyK(ListNode head, int k) {
        ListNode prev = null;
        ListNode curr = head;
        while (k > 0) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
            k--;
        }
        return prev;
    }
}