//rohit01-pro
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        if (numRows <= 0) {
            return triangle;
        }
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    int prevRowLeft = triangle.get(i - 1).get(j - 1);
                    int prevRowRight = triangle.get(i - 1).get(j);
                    row.add(prevRowLeft + prevRowRight);
                }
            }
            triangle.add(row);
        }
        return triangle;
    }
}