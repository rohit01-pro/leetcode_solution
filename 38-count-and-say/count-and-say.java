class Solution {
    public String countAndSay(int n) {
        if (n <= 0) return "";
        String result = "1";
        for (int i = 1; i < n; i++) {
            result = buildNext(result);
        }
        return result;
    }
    private String buildNext(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            int count = 1;
            char charVal = s.charAt(i);
            while (i + 1 < s.length() && s.charAt(i + 1) == charVal) {
                count++;
                i++;
            }
            sb.append(count).append(charVal);
            i++;
        }
        return sb.toString();
    }
}