package DataStreamAsDisjointIntervals;

import java.util.ArrayList;
import java.util.Arrays;

class Scratch {
    public static void main(String[] args) {
        SummaryRanges obj = new SummaryRanges();
        obj.addNum(1);
        obj.addNum(3);
        obj.addNum(2);
        obj.addNum(3);

        obj.addNum(5);
        obj.addNum(6);

        obj.addNum(9);
        obj.addNum(8);

        print(obj.getIntervals());
    }

    public static void print(int[][] result) {
        StringBuilder builder = new StringBuilder();
        for (var list : result) {
            boolean first = true;
            builder.append("[");
            for (var word : list) {
                if (!first)
                    builder.append(", ");
                first = false;
                builder.append(word);
            }
            builder.append("]\n");
        }
        System.out.println(builder);
    }
}

class SummaryRanges {

    class Range {
        private int start;
        private int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    private ArrayList<Range> ranges = new ArrayList<>();

    public SummaryRanges() {

    }

    public void addNum(int value) {
        if (ranges.isEmpty()) {
            ranges.add(new Range(value, value));
            return;
        }

        int ix = binarySearch(ranges, 0, ranges.size(), value);
        if (ix < 0) {
            ix = - ix - 1;
            // not found
            if (ix - 1 >= 0 && value >= ranges.get(ix - 1).start && value <= ranges.get(ix - 1).end) {
                // no op, already in range
                return;
            } else if (ix - 1 >= 0 && ranges.get(ix - 1).end == value - 1) {
                if (ix < ranges.size() && ranges.get(ix).start == value + 1) {
                    // merge ranges
                    Range oldRange = ranges.remove(ix);
                    ranges.get(ix - 1).end = oldRange.end;
                } else {
                    // change end of the left side
                    ranges.get(ix - 1).end = value;
                }
            } else if (ix < ranges.size() && ranges.get(ix).start == value + 1) {
                ranges.get(ix).start = value;
            } else {
                ranges.add(ix, new Range(value, value));
            }
        }
    }

    public int[][] getIntervals() {
        int[][] ret = new int[ranges.size()][];
        for (int i = 0; i < ranges.size(); i++) {
            ret[i] = new int[]{ ranges.get(i).start, ranges.get(i).end };
        }
        return ret;
    }

    private int binarySearch(ArrayList<Range> a, int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            var midVal = a.get(mid);

            if (midVal.start < key)
                low = mid + 1;
            else if (midVal.start > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */