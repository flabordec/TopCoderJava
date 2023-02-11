package FindClosestNodeToGivenTwoNodes;

import java.util.*;

class Solution {
    class State {
        private int index1;
        private int index2;
        private int distance;

        public State(int index1, int index2, int distance) {
            this.index1 = index1;
            this.index2 = index2;
            this.distance = distance;
        }

        public int getIndex1() {
            return index1;
        }

        public int getIndex2() {
            return index2;
        }

        public int getDistance() {
            return distance;
        }
    }

    public static void main(String[] args) {
        var solution = new Solution();

        {
            int[] edges = new int[]{2, 2, 3, -1};
            int node1 = 0;
            int node2 = 1;
            int result = solution.closestMeetingNode(edges, node1, node2);
            System.out.println(result);
        }

        {
            int[] edges = new int[]{1, 2, -1};
            int node1 = 0;
            int node2 = 2;
            int result = solution.closestMeetingNode(edges, node1, node2);
            System.out.println(result);
        }

        {
            int[] edges = new int[]{4, 3, 0, 5, 3, -1};
            int node1 = 4;
            int node2 = 0;
            int result = solution.closestMeetingNode(edges, node1, node2);
            System.out.println(result);
        }

        {
            int[] edges = new int[]{5, 4, 5, 4, 3, 6, -1};
            int node1 = 0;
            int node2 = 1;
            int result = solution.closestMeetingNode(edges, node1, node2);
            System.out.println(result);
        }

        {
            int[] edges = new int[]{4, 4, 8, -1, 9, 8, 4, 4, 1, 1};
            int node1 = 5;
            int node2 = 6;
            int result = solution.closestMeetingNode(edges, node1, node2);
            System.out.println(result);
        }
    }

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int[] distances1 = new int[edges.length];
        int[] distances2 = new int[edges.length];
        for (int i = 0; i < distances1.length; i++) {
            distances1[i] = -1;
            distances2[i] = -1;
        }

        HashSet<Integer> seen1 = new HashSet<>();
        HashSet<Integer> seen2 = new HashSet<>();
        Queue<State> queue = new ArrayDeque<>();
        queue.offer(new State(node1, node2, 0));
        while (!queue.isEmpty()) {
            var curr = queue.remove();

            int index1 = curr.getIndex1();
            if (!seen1.add(index1)) {
                index1 = -1;
            }

            int index2 = curr.getIndex2();
            if (!seen2.add(index2)) {
                index2 = -1;
            }

            if (index1 != -1) {
                distances1[index1] = curr.getDistance();
            }

            if (index2 != -1) {
                distances2[index2] = curr.getDistance();
            }

            int minIndex = Math.min(index1, index2);
            int maxIndex = Math.max(index1, index2);

            if (minIndex != -1 && distances1[minIndex] != -1 && distances2[minIndex] != -1) {
                return minIndex;
            }
            if (maxIndex != -1 && distances1[maxIndex] != -1 && distances2[maxIndex] != -1) {
                return maxIndex;
            }

            int next1 = index1 != -1 ? edges[index1] : -1;
            int next2 = index2 != -1 ? edges[index2] : -1;

            if (next1 != -1 || next2 != -1) {
                queue.offer(new State(next1, next2, curr.getDistance() + 1));
            }
        }
        return -1;
    }
}