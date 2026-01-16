//rohit01-pro two pointer 
class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        
        int sPointer = 0;
        int tPointer = 0;
        
        while (tPointer < t.length()) {
            if (s.charAt(sPointer) == t.charAt(tPointer)) {
                sPointer++;
            }
            if (sPointer == s.length()) return true;
            tPointer++;
        }
        return false;
    }
}