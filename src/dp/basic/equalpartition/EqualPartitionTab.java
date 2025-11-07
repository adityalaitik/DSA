void main(){
    int[] nums = {1, 5, 11, 5};
    System.out.println(canPartition(nums)); // true
}

private boolean canPartition(int[] nums) {
    int sum =0;

    for(int num:nums) sum+= num;

    if(sum % 2 != 0) return false;

    int target = sum/2;

    int n = nums.length;

    boolean dp[][] = new boolean[n][target+1];

    for (int i = 0; i < n; i++) dp[i][0] = true;
    if(nums[0] == target) dp[0][nums[0]] = true;

    for (int i = 1; i < n; i++) {
        for(int t =1;t <= target;t++){
            boolean notTaken = dp[i-1][t];
            boolean taken = false;
            if(nums[i]<=t)
                taken = dp[i-1][t-nums[i]];

            dp[i][t] = taken || notTaken;
        }
    }
    return dp[n-1][target];
}
