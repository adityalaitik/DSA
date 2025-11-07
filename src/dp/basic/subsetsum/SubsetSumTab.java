void main() {
    int[] arr = {2, 3, 7, 8, 10};
    int target = 11;
    System.out.println(subsetSum(arr, target));
}

private static boolean subsetSum(int[] arr, int target) {
    int n = arr.length;
    boolean[][] dp = new boolean[n][target + 1];

    // Base case
    for(int i=0 ;i<n ; i++){
        dp[i][0] = true;
    }
    if(arr[0] < target){
        dp[0][arr[0]] = true;
    }

    for(int i=1;i< n; i++){
        for(int t = 1; t<= target; t++){
            boolean notTake = dp[i-1][t];
            boolean take = false;
            if(arr[i]<= t){
                take = dp[i-1][t-arr[i]];
            }
            dp[i][t] = notTake || take;
        }
    }
    return dp[n - 1][target];
}