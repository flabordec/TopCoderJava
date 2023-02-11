package PermutationInString;

class Scratch {
    public static void main(String[] args) {
        Solution sol = new Solution();
        {
            System.out.println(sol.checkInclusion("ab", "eidbaooo")); // true
            System.out.println(sol.checkInclusion("ab", "eidboaoo")); // false
            System.out.println(sol.checkInclusion("abc", "ab")); // false
            System.out.println(sol.checkInclusion("a", "ab")); // true


            var sb1 = new StringBuilder();
            var sb2 = new StringBuilder();
            for (int i = 0; i < 10000; i++) {
                sb1.append('a');
                sb2.append('a');
            }
            System.out.println(sol.checkInclusion("a", sb2.toString())); // true
            System.out.println(sol.checkInclusion("b", sb2.toString())); // false
        }
    }
}

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length())
            return false;

        int[] charCounts1 = new int['z' - 'a' + 1];
        int[] charCounts2 = new int['z' - 'a' + 1];
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            charCounts1[c1 - 'a']++;

            char c2 = s2.charAt(i);
            charCounts2[c2 - 'a']++;
        }

        if (allEqual(charCounts1, charCounts2))
            return true;

        for (int i = s1.length(); i < s2.length(); i++) {
            char cBefore = s2.charAt(i - s1.length());
            char cCurr = s2.charAt(i);

            charCounts2[cBefore - 'a']--;
            charCounts2[cCurr - 'a']++;

            if (allEqual(charCounts1, charCounts2))
                return true;
        }
        return false;
    }

    private static boolean allEqual(int[] charCounts1, int[] charCounts2) {
        boolean allEqual = true;
        for (char c = 'a'; c <= 'z'; c++) {
            if (charCounts1[c - 'a'] != charCounts2[c - 'a']) {
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