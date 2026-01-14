//rohit01-pro
class Solution {
    public double separateSquares(int[][] squares) {
        int n = squares.length;
        int[] xCoords = new int[2 * n];
        for (int i = 0; i < n; i++) {
            xCoords[2 * i] = squares[i][0];
            xCoords[2 * i + 1] = squares[i][0] + squares[i][2];
        }
        
        sort(xCoords, 0, 2 * n - 1);
        int m = 0;
        for (int i = 0; i < 2 * n; i++) {
            if (i == 0 || xCoords[i] != xCoords[i - 1]) {
                xCoords[m++] = xCoords[i];
            }
        }
        
        long[][] events = new long[2 * n][4];
        for (int i = 0; i < n; i++) {
            events[2 * i] = new long[]{squares[i][1], squares[i][0], squares[i][0] + squares[i][2], 1};
            events[2 * i + 1] = new long[]{(long)squares[i][1] + squares[i][2], squares[i][0], squares[i][0] + squares[i][2], -1};
        }
        
        sortEvents(events, 0, 2 * n - 1);
        
        int[] count = new int[4 * m];
        long[] sum = new long[4 * m];
        
        double totalArea = 0;
        for (int i = 0; i < 2 * n - 1; i++) {
            update(1, 0, m - 2, events[i], xCoords, count, sum, m);
            totalArea += (double) sum[1] * (events[i + 1][0] - events[i][0]);
        }
        
        for (int i = 0; i < 4 * m; i++) {
            count[i] = 0;
            sum[i] = 0;
        }
        
        double targetArea = totalArea / 2.0;
        double currentArea = 0;
        for (int i = 0; i < 2 * n - 1; i++) {
            update(1, 0, m - 2, events[i], xCoords, count, sum, m);
            double delta = (double) sum[1] * (events[i + 1][0] - events[i][0]);
            if (currentArea + delta >= targetArea - 1e-7) {
                if (sum[1] == 0) return (double) events[i][0];
                return events[i][0] + (targetArea - currentArea) / sum[1];
            }
            currentArea += delta;
        }
        
        return (double) events[2 * n - 1][0];
    }

    private void update(int node, int start, int end, long[] event, int[] xCoords, int[] count, long[] sum, int m) {
        int l = find(xCoords, (int) event[1], m);
        int r = find(xCoords, (int) event[2], m) - 1;
        if (l <= r) {
            updateTree(node, start, end, l, r, (int) event[3], xCoords, count, sum);
        }
    }

    private int find(int[] arr, int val, int m) {
        int low = 0, high = m - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == val) return mid;
            if (arr[mid] < val) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    private void updateTree(int node, int start, int end, int l, int r, int val, int[] xCoords, int[] count, long[] sum) {
        if (l <= start && end <= r) {
            count[node] += val;
        } else {
            int mid = (start + end) / 2;
            if (l <= mid) updateTree(node * 2, start, mid, l, r, val, xCoords, count, sum);
            if (r > mid) updateTree(node * 2 + 1, mid + 1, end, l, r, val, xCoords, count, sum);
        }
        
        if (count[node] > 0) {
            sum[node] = xCoords[end + 1] - xCoords[start];
        } else if (start != end) {
            sum[node] = sum[node * 2] + sum[node * 2 + 1];
        } else {
            sum[node] = 0;
        }
    }

    private void sort(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivot = arr[left + (right - left) / 2];
        int i = left, j = right;
        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++; j--;
            }
        }
        sort(arr, left, j);
        sort(arr, i, right);
    }

    private void sortEvents(long[][] arr, int left, int right) {
        if (left >= right) return;
        long pivot = arr[left + (right - left) / 2][0];
        int i = left, j = right;
        while (i <= j) {
            while (arr[i][0] < pivot) i++;
            while (arr[j][0] > pivot) j--;
            if (i <= j) {
                long[] temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++; j--;
            }
        }
        sortEvents(arr, left, j);
        sortEvents(arr, i, right);
    }
}