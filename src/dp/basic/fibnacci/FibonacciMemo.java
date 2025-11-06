void main() {
    int n=6;
    int[] dp = new int[n+1];
    Arrays.fill(dp, -1);
    IO.println(fib(n,dp));
}

static int fib(int n,int[] dp){
    if(n<=1) return n;
    if(dp[n]!=-1) return dp[n];
    return dp[n] = fib(n-1,dp) + fib(n-2, dp);
}

/*
Time: O(n) ✅
Space: O(n)
 */
