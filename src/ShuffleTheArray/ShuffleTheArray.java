package ShuffleTheArray;

class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length / 2; i++) {
            result[i * 2] = nums[i];
            result[i * 2 + 1] = nums[i + nums.length / 2];
        }
        return result;
    }
}