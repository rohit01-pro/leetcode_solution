class Solution {
//rohit01-pro
    public long zeroFilledSubarray(int[] nums) {
        long totalSubarrays = 0;
        long currentZeroStreak = 0;
        
        for (int num : nums) {
            if (num == 0) {
                currentZeroStreak++;
                totalSubarrays += currentZeroStreak;
            } else {
                currentZeroStreak = 0;
            
            }
        }

        return totalSubarrays;
        
    }
}