import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles.length == 0 || rectangles[0].length == 0) return false;

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        
        long totalArea = 0;
        Set<String> corners = new HashSet<>();

        for (int[] rect : rectangles) {
            int x1 = rect[0];
            int y1 = rect[1];
            int x2 = rect[2];
            int y2 = rect[3];

            minX = Math.min(minX, x1);
            minY = Math.min(minY, y1);
            maxX = Math.max(maxX, x2);
            maxY = Math.max(maxY, y2);

            totalArea += (long) (x2 - x1) * (y2 - y1);

            String[] currentCorners = {
                x1 + " " + y1,
                x1 + " " + y2,
                x2 + " " + y1,
                x2 + " " + y2
            };

            for (String corner : currentCorners) {
                if (!corners.add(corner)) {
                    corners.remove(corner);
                }
            }
        }

        if (!corners.contains(minX + " " + minY) || 
            !corners.contains(minX + " " + maxY) || 
            !corners.contains(maxX + " " + minY) || 
            !corners.contains(maxX + " " + maxY) || 
            corners.size() != 4) {
            return false;
        }

        return totalArea == (long) (maxX - minX) * (maxY - minY);
    }
}