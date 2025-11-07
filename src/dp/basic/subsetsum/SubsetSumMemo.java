void main() {
    int[] arr = {2, 3, 7, 8, 10};
    int target = 11;
    int[][] dp = new int[arr.length][target + 1];
    for (int[] row : dp) Arrays.fill(row, -1);

    IO.println(subsetSum(arr.length - 1, target, arr, dp));// true
}

private boolean subsetSum(int i, int target, int[] arr, int[][] dp) {
    if (target == 0) return true;
    if (i == 0) return arr[0] == target;
    if (dp[i][target] != -1) return dp[i][target] == 1;

    boolean notTake = subsetSum(i - 1, target, arr, dp);
    boolean take = false;
    if (arr[i] <= target)
        take = subsetSum(i - 1, target - arr[i], arr, dp);

    dp[i][target] = (take || notTake) ? 1 : 0;
    return take || notTake;
}
