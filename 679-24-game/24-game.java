//rohit01-pro
class Solution {
    public boolean judgePoint24(int[] cards) {
        double[] nums = new double[4];
        for (int i = 0; i < 4; i++) {
            nums[i] = (double) cards[i];
        }
        return solve(nums);
    }

    private boolean solve(double[] nums) {
        int n = nums.length;
        if (n == 1) {
            return Math.abs(nums[0] - 24.0) < 1e-6;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                double[] nextNums = new double[n - 1];
                int idx = 0;
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) {
                        nextNums[idx++] = nums[k];
                    }
                }

                for (double res : getPossibilities(nums[i], nums[j])) {
                    nextNums[n - 2] = res;
                    if (solve(nextNums)) return true;
                }
            }
        }
        return false;
    }

    private double[] getPossibilities(double a, double b) {
        double[] res = new double[]{a + b, a - b, b - a, a * b, 0, 0};
        int count = 4;
        
        if (Math.abs(b) > 1e-6) {
            res[count++] = a / b;
        }
        if (Math.abs(a) > 1e-6) {
            res[count++] = b / a;
        }
        double[] validRes = new double[count];
        for (int i = 0; i < count; i++) {
            validRes[i] = res[i];
        }
        return validRes;
    }
}