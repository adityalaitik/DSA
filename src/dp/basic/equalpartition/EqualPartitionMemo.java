void main(){
    int[] nums = {1, 5, 11, 5};
    int sum = Arrays.stream(nums).sum();

    if (sum % 2 != 0) {
        System.out.println(false);
        return;
    }

    int target = sum / 2;
    int[][] dp = new int[nums.length][target + 1];
    for (int[] row : dp) Arrays.fill(row, -1);

    System.out.println(canPartition(nums.length - 1, target, nums, dp));
}

private boolean canPartition(int i, int target, int[] nums, int[][] dp) {
    if (target == 0) return true;
    if (i == 0) return nums[0] == target;
    if (dp[i][target] != -1) return dp[i][target] == 1;

    boolean notTake = canPartition(i - 1, target, nums, dp);
    boolean take = false;
    if (nums[i] <= target)
        take = canPartition(i - 1, target - nums[i], nums, dp);

    dp[i][target] = (take || notTake) ? 1 : 0;
    return take || notTake;
}