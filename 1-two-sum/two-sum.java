class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int[][] pair = new int[n][2];
        
        for (int i = 0; i < n; i++) {
            pair[i][0] = nums[i];
            pair[i][1] = i;
        }
        sort(pair, 0, n - 1);

        int left = 0;
        int right = n - 1;

        while (left < right) {
            int sum = pair[left][0] + pair[right][0];
            if (sum == target) {
                return new int[]{pair[left][1], pair[right][1]};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }

    private void sort(int[][] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            sort(arr, low, p);
            sort(arr, p + 1, high);
        }
    }

    private int partition(int[][] arr, int low, int high) {
        int pivot = arr[low + (high - low) / 2][0];
        int i = low - 1, j = high + 1;
        while (true) {
            do { i++; } while (arr[i][0] < pivot);
            do { j--; } while (arr[j][0] > pivot);
            if (i >= j) return j;
            int[] temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}