//rohit01-pro
class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int target = nums.get(i);
            if (target == 2) {
                ans[i] = -1;
            } else {
                for (int bit = 0; bit < 31; bit++) {
                    if (((target >> (bit + 1)) & 1) == 0) {
                        ans[i] = target ^ (1 << bit);
                        break;
                    }
                }
            }
        }
        return ans;
    }
}