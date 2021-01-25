class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0) {
            return 0;
        }
        // n - 最多有多少个区间不重叠 = 至少需要去除几个区间（需要移除区间的最小数量）
        return intervals.length - intervalSchedule(intervals);
    }

    //求最多有几个区间不会重叠
    public int intervalSchedule(int[][] intervals) {
        if(intervals.length == 0) {
            return 0;
        }
            
        // 按end升序排列
        Arrays.sort(intervals, new Comparator<int []>(){
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });

        // 至少有一个区间不相交
        int count = 1;
        // 排序后，第一个区间就是x
        int x_end = intervals[0][1];
        for(int[] interval : intervals) {
            int start = interval[0];
            if(start >= x_end) {
                // 找到下一个选择的区间了
                count++;
                x_end = interval[1];
            }
        }   
        return count;
    }
}