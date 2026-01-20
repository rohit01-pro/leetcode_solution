//rohit01-pro
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        int[] pointers = new int[primes.length];
        ugly[0] = 1;

        for (int i = 1; i < n; i++) {
            long minNext = Long.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                long currentNext = (long) ugly[pointers[j]] * primes[j];
                if (currentNext < minNext) {
                    minNext = currentNext;
                }
            }
            ugly[i] = (int) minNext;
            for (int j = 0; j < primes.length; j++) {
                if ((long) ugly[pointers[j]] * primes[j] == minNext) {
                    pointers[j]++;
                }
            }
        }
        return ugly[n - 1];
    }
}