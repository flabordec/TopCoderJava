package BestTeamWithNoConflicts;

class Scratch {
    public static void main(String[] args) {
        Solution sol = new Solution();
        {
//            System.out.println(sol.bestTeamScore(new int[]{1,3,5,10,15 }, new int[]{1,2,3,4,5 }));
//            System.out.println(sol.bestTeamScore(new int[]{4,5,6,5 }, new int[]{2,1,2,1 }));
            System.out.println(sol.bestTeamScore(new int[]{1,2,3,5 }, new int[]{8,9,10,1 })); // 6
            System.out.println(sol.bestTeamScore(new int[]{6,4,5,5 }, new int[]{2,2,1,1 })); // 16
            System.out.println(sol.bestTeamScore(new int[]{4,4,5 }, new int[]{2,2,1 })); // 8
            System.out.println(sol.bestTeamScore(new int[]{4,5 }, new int[]{2,1 })); // 5
            System.out.println(sol.bestTeamScore(new int[]{6,4,4,4,5,5 }, new int[]{2,2,2,2,1,1 })); // 18
        }
    }
}

class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
        for (int i = 0; i < scores.length; i++) {
            int bestJ = i;
            for (int j = i + 1; j < scores.length; j++) {
                if (ages[j] > ages[bestJ]) {
                    bestJ = j;
                } else if (ages[j] == ages[bestJ] && scores[j] > scores[bestJ]){
                    bestJ = j;
                }
            }
            int temp;

            temp = scores[i];
            scores[i] = scores[bestJ];
            scores[bestJ] = temp;

            temp = ages[i];
            ages[i] = ages[bestJ];
            ages[bestJ] = temp;
        }

        return bestTeamScoreHelper(scores, ages, 0, 1000001, 1000001, 0);
    }

    public int bestTeamScoreHelper(int[] scores, int[] ages, int ix, int minScoreCurrentAge, int minScorePreviousAge, int cs) {

        if (ix == scores.length)
            return 0;

        int bestScore = 0;

        // 1. we pick the current player
        // we have to check if there is a conflict
        int score = scores[ix];
        int age = ages[ix];

        int prevMinScorePreviousAge = minScorePreviousAge;
        int prevMinScoreCurrentAge = minScoreCurrentAge;

        if  (ix > 0 && age != ages[ix - 1]) {
            minScorePreviousAge = minScoreCurrentAge;
            minScoreCurrentAge = 1000001;
        }

        // A conflict exists if a younger player has a strictly higher score than an older player. A conflict does
        // not occur between players of the same age.
        boolean conflict = score > minScorePreviousAge;
        if (!conflict) {
            minScoreCurrentAge = score;

            cs += score;
            bestScore = Math.max(
                    bestScore,
                    bestTeamScoreHelper(scores, ages, ix + 1, minScoreCurrentAge, minScorePreviousAge, cs) + score);
            cs -= score;

            minScoreCurrentAge = prevMinScoreCurrentAge;
        }

        // 2. we skip the current player
        bestScore = Math.max(
                bestScore,
                bestTeamScoreHelper(scores, ages, ix + 1, minScoreCurrentAge, minScorePreviousAge, cs));

        minScorePreviousAge = prevMinScorePreviousAge;

        return bestScore;
    }
}