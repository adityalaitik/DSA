void main() {
    IO.println(climbStairs(6));
}

static int climbStairs(int n) {
    int[] dp = new int[n+1];
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i-1] + dp[i-2];
    }
    return dp[n];
}
/*
✅ DP type: 1D
✅ Base: Fibonacci pattern
✅ Use case: Count ways
 */