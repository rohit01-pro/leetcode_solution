//rohit01-pro two pointer approch
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;

        int[] lastSeen = new int[128];
        for (int i = 0; i < 128; i++) {
            lastSeen[i] = -1;
        }

        int left = 0;
        for (int right = 0; right < n; right++) {
            char currentChar = s.charAt(right);

            if (lastSeen[currentChar] >= left) {

                left = lastSeen[currentChar] + 1;
            }
            lastSeen[currentChar] = right;
            
            int currentWindowSize = right - left + 1;
            if (currentWindowSize > maxLength) {
                maxLength = currentWindowSize;
            }
        }
        
        return maxLength;
    }
}