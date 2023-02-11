package ShortestPathWithAlternatingColors;

import java.util.*;

class Scratch {
    public static void main(String[] args) {
        Solution sol = new Solution();
        {
            println(sol.shortestAlternatingPaths(3, new int[][]{new int[]{0, 1}, new int[]{1, 2}}, new int[][]{}));
            println(sol.shortestAlternatingPaths(3, new int[][]{new int[]{0, 1}}, new int[][]{new int[]{2,1}}));
            println(sol.shortestAlternatingPaths(3, new int[][]{new int[]{0, 1}}, new int[][]{new int[]{1, 2}}));
        }
    }

    private static void println(int[] arr) {
        if (arr.length == 0) {
            System.out.println("<empty>");
        }

        StringBuilder builder = new StringBuilder();
        builder.append(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            builder.append(", ");
            builder.append(arr[i]);
        }
        System.out.println(builder);
    }
}

class Solution {
    class State {
        int node;
        int distance;
        boolean isRed;

        public State(int node, int distance, boolean isRed) {
            this.node = node;
            this.distance = distance;
            this.isRed = isRed;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return node == state.node && isRed == state.isRed;
        }

        @Override
        public int hashCode() {
            return Objects.hash(node, isRed);
        }
    }

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        int[] distances = new int[n];
        distances[0] = 0;
        for (int i = 1; i < distances.length; i++)
            distances[i] = -1;

        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(0, 0, true));
        queue.offer(new State(0, 0, false));

        HashSet<State> seen = new HashSet<>();
        while (!queue.isEmpty()) {
            State curr = queue.poll();
            if (seen.add(curr)) {
                if (curr.distance < distances[curr.node] || distances[curr.node] == -1) {
                    distances[curr.node] = curr.distance;
                }

                int[][] edges = curr.isRed ? blueEdges : redEdges;
                for (int i = 0; i < edges.length; i++) {
                    int from = edges[i][0];
                    int to = edges[i][1];

                    if (from == curr.node) {
                        queue.offer(new State(to, curr.distance + 1, !curr.isRed));
                    }
                }
            }
        }
        return distances;
    }
}