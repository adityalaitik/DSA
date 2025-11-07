void main() {
    int[] wt = {1, 2, 4, 5};
    int[] val = {5, 4, 8, 6};
    int W = 5;
    IO.println(knapsack(wt, val, W));
}

static int knapsack(int[] w, int[] v, int W){
    int n = w.length;

    int [][]dp = new int[n][W+1];


    //base case
    for(int wt= w[0]; wt<=W;wt++){
        dp[0][wt] = v[0];
    }

    for (int i = 1; i <n ; i++) {
        for(int wt= 0;wt<=W; wt++){
            int notTake = dp[i-1][wt];
            int take = (w[i]<=wt)? v[i] + dp[i-1][W-w[i]] : Integer.MIN_VALUE;
            dp[i][wt] = Math.max(notTake,take);
        }
    }

    for(int[] r: dp){
        IO.println(Arrays.toString(r));
    }
    return dp[n-1][W];
}