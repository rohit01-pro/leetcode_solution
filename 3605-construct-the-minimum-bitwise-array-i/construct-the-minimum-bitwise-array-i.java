//rohit01-pro
class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int target = nums.get(i);
            int minVal = -1;
            
            // Iterate to find the smallest x satisfy the condition
            for (int x = 0; x <= target; x++) {
                if ((x | (x + 1)) == target) {
                    minVal = x;
                    break;
                }
            }
            ans[i] = minVal;
        }
        return ans;
    }
}