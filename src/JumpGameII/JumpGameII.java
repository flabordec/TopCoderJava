package JumpGameII;

class Scratch {
    public static void main(String[] args) {
        Solution sol = new Solution();
        {
            System.out.println(sol.jump(new int[]{2, 3, 1, 1, 4}));
            System.out.println(sol.jump(new int[]{2, 3, 0, 1, 4}));
        }
    }
}

class Solution {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = nums.length + 1;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= nums[i] && i + j < nums.length; j++) {
                dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
            }
        }
        return dp[dp.length - 1];
    }
}