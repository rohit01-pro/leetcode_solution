class Solution {
    public boolean isGood(int[] nums) {
        int n = nums.length - 1;
        if (n < 1) return false; 

        int[] count = new int[201]; 
        int maxVal = 0;

        for (int num : nums) {
            count[num]++;
            if (num > maxVal) maxVal = num;
        }
        if (maxVal != n) return false;

        for (int i = 1; i < n; i++) {
            if (count[i] != 1) return false;
        }
        return count[n] == 2;
    }
}