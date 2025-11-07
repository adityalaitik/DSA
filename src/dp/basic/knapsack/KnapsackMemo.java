void main() {
    int[] wt = {1, 2, 4, 5};
    int[] val = {5, 4, 8, 6};
    int W = 5;
    int n = wt.length;

    int[][] dp = new int[n][W+1];
    for (int[] row: dp) Arrays.fill(row,-1);
    IO.println(knapsack(n - 1, W, wt, val, dp));
}

static int knapsack(int i,int W, int[] w, int[] v, int[][] dp){
    if(i ==0) return w[0]<=W ? v[0]: 0;

    if(dp[i][W]!= -1) return dp[i][W];

    int notTake = knapsack(i-1, W, w,v, dp);
    int take = Integer.MIN_VALUE;
    if(w[i]<W){
        take = v[i]+ knapsack(i-1, W-w[i],w,v,dp);
    }
    return dp[i][W] = Math.max(take,notTake);
}