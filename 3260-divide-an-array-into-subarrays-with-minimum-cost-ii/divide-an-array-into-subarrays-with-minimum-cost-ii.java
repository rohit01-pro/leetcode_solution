import java.util.TreeMap;

class Solution {
    private TreeMap<Integer, Integer> left = new TreeMap<>();
    private TreeMap<Integer, Integer> right = new TreeMap<>();
    private int leftSize = 0;
    private int rightSize = 0;
    private long sum = 0;
    private int m;

    public long minimumCost(int[] nums, int k, int dist) {
        this.m = k - 1;
        int n = nums.length;
        for (int i = 1; i <= dist + 1; i++) {
            add(nums[i]);
        }
        long minSum = sum;
        for (int i = 2; i + dist < n; i++) {
            remove(nums[i - 1]);
            add(nums[i + dist]);
            minSum = Math.min(minSum, sum);
        }
        return (long) nums[0] + minSum;
    }
    private void add(int v) {
        left.put(v, left.getOrDefault(v, 0) + 1);
        sum += v;
        leftSize++;
        
        if (leftSize > m) {
            int last = left.lastKey();
            removeLeft(last);
            addRight(last);
        }
    }
    private void remove(int v) {
        if (left.containsKey(v)) {
            removeLeft(v);
            if (rightSize > 0) {
                int first = right.firstKey();
                removeRight(first);
                addLeft(first);
            }
        } else {
            removeRight(v);
        }
    }
    private void addLeft(int v) {
        left.put(v, left.getOrDefault(v, 0) + 1);
        sum += v;
        leftSize++;
    }
    private void removeLeft(int v) {
        int c = left.get(v);
        if (c == 1) left.remove(v);
        else left.put(v, c - 1);
        sum -= v;
        leftSize--;
    }
    private void addRight(int v) {
        right.put(v, right.getOrDefault(v, 0) + 1);
        rightSize++;
    }
    private void removeRight(int v) {
        int c = right.get(v);
        if (c == 1) right.remove(v);
        else right.put(v, c - 1);
        rightSize--;
    }
}