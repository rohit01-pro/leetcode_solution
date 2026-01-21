//rohit01-pro
class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        int count = 10; // n=1: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        int uniqueDigits = 9;
        int availableNumber = 9;
        while (n > 1 && availableNumber > 0) {
            uniqueDigits = uniqueDigits * availableNumber;
            count += uniqueDigits;
            availableNumber--;
            n--;
        }
        return count;
    }
}