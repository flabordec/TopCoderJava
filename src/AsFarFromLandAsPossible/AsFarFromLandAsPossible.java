package AsFarFromLandAsPossible;

import java.util.LinkedList;
import java.util.Queue;

class Scratch {
    public static void main(String[] args) {
        Solution sol = new Solution();
        {
            System.out.println(sol.maxDistance(new int[][]{ new int[]{1,0,1},new int[]{0,0,0},new int[]{1,0,1}}));
            System.out.println(sol.maxDistance(new int[][]{ new int[]{1,0,0},new int[]{0,0,0},new int[]{0,0,0}}));
            System.out.println(sol.maxDistance(new int[][]{ new int[]{0,0,0},new int[]{0,0,0},new int[]{0,0,0}}));
        }
    }
}

class Solution {
    private int[] di = new int[]{0, 0, 1, -1};
    private int[] dj = new int[]{1, -1, 0, 0};

    class State {
        private int i;
        private int j;
        private int distance;

        public State(int i, int j, int distance) {
            this.i = i;
            this.j = j;
            this.distance = distance;
        }
    }

    public int maxDistance(int[][] grid) {
        Queue<State> queue = new LinkedList<>();

        int[][] distances = new int[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            distances[i] = new int[grid[i].length];
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new State(i, j, 0));
                    distances[i][j] = 0;
                } else {
                    distances[i][j] = -1;
                }
            }
        }

        int maxDistance = -1;
        while (!queue.isEmpty()) {
            var curr = queue.poll();
            for (int d = 0; d < di.length; d++) {
                int ni = curr.i + di[d];
                int nj = curr.j + dj[d];

                if (ni >= 0 && ni < grid.length &&
                        nj >= 0 && nj < grid[ni].length &&
                        (curr.distance + 1 < distances[ni][nj] || distances[ni][nj] == -1)) {
                    distances[ni][nj] = curr.distance + 1;
                    queue.offer(new State(ni, nj, curr.distance + 1));
                    maxDistance = Math.max(maxDistance, curr.distance + 1);
                }
            }
        }
        return maxDistance;
    }
}