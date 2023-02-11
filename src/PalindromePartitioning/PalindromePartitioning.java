package PalindromePartitioning;

import java.util.*;

class Solution {

    public static void main(String[] args) {
        var solution = new Solution();

        {
            var result = solution.partition("aabaaca");
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

    public List<List<String>> partition(String s) {
        List<List<String>> solution = new ArrayList<>();
        List<String> partialSolution = new ArrayList<>();
        findWord(s, 0, solution, partialSolution);
        return solution;
    }

    public void findWord(String s, int start, List<List<String>> solution, List<String> partialSolution) {

        if (start == s.length()) {
            solution.add(partialSolution);
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                List<String> newPartialSolution = new ArrayList<>();
                newPartialSolution.addAll(partialSolution);
                newPartialSolution.add(s.substring(start, end + 1));
                findWord(s, end + 1, solution, newPartialSolution);
            }
        }
    }

    public boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            char sC = s.charAt(start);
            char eC = s.charAt(end);
            if (sC != eC) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}