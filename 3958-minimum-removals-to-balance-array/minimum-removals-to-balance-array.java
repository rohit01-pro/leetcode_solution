import java.util.Arrays;

class Solution {
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        if (n <= 1) return 0;
        Arrays.sort(nums);
        int maxKeep = 0;
        int right = 0;
        for (int left = 0; left < n; left++) {
            while (right < n && (long) nums[right] <= (long) k * nums[left]) {
                right++;
            }
            maxKeep = Math.max(maxKeep, right - left);
        }
        
        return n - maxKeep;
    }
}