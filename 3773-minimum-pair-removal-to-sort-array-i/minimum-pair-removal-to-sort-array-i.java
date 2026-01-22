import java.util.ArrayList;
import java.util.List;

class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        int operations = 0;
        while (!isSorted(list)) {
            int minSum = Integer.MAX_VALUE;
            int minIdx = -1;
            for (int i = 0; i < list.size() - 1; i++) {
                int currentSum = list.get(i) + list.get(i + 1);
                if (currentSum < minSum) {
                    minSum = currentSum;
                    minIdx = i;
                }
            }
            int combined = list.get(minIdx) + list.get(minIdx + 1);
            list.remove(minIdx + 1);
            list.set(minIdx, combined);
            
            operations++;
        }

        return operations;
    }
    private boolean isSorted(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}