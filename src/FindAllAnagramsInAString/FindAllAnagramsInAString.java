package FindAllAnagramsInAString;

import java.util.ArrayList;
import java.util.List;

class Scratch {
    public static void main(String[] args) {
        Solution sol = new Solution();
        {
            println(sol.findAnagrams("cbaebabacd", "abc")); // true
            println(sol.findAnagrams("eidbaooo", "ab")); // true
            println(sol.findAnagrams("eidboaoo", "ab")); // false
            println(sol.findAnagrams("ab", "abc")); // false
            println(sol.findAnagrams("ab", "a")); // true
            println(sol.findAnagrams("aa", "a"));
            println(sol.findAnagrams("ba", "a"));
            println(sol.findAnagrams("ab", "a"));

            var sb1 = new StringBuilder();
            var sb2 = new StringBuilder();
            for (int i = 0; i < 10000; i++) {
                sb1.append('a');
                sb2.append('a');
            }
            println(sol.findAnagrams(sb2.toString(), "a")); // true
            println(sol.findAnagrams(sb2.toString(), "b")); // false

        }
    }

    public static void println(List<Integer> list) {
        if (list.isEmpty()) {
            System.out.println("<empty>");
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            builder.append(", ");
            builder.append(list.get(i));
        }
        System.out.println(builder.toString());
    }
}

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> results = new ArrayList<>();

        if (s.length() < p.length())
            return results;

        int[] charCounts1 = new int['z' - 'a' + 1];
        int[] charCounts2 = new int['z' - 'a' + 1];
        for (int i = 0; i < p.length(); i++) {
            char c1 = p.charAt(i);
            charCounts1[c1 - 'a']++;

            char c2 = s.charAt(i);
            charCounts2[c2 - 'a']++;
        }

        if (allEqual(charCounts1, charCounts2)) {
            results.add(0);
        }

        for (int i = p.length(); i < s.length(); i++) {
            char cBefore = s.charAt(i - p.length());
            char cCurr = s.charAt(i);

            charCounts2[cBefore - 'a']--;
            charCounts2[cCurr - 'a']++;

            if (allEqual(charCounts1, charCounts2)) {
                results.add(i - p.length() + 1);
            }
        }
        return results;
    }

    private static boolean allEqual(int[] charCounts, int[] charCounts2) {
        boolean allEqual = true;
        for (char c = 'a'; c <= 'z'; c++) {
            if (charCounts[c - 'a'] != charCounts2[c - 'a']) {
                allEqual = false;
                break;
            }
        }
        if (allEqual) {
            return true;
        }
        return false;
    }
}