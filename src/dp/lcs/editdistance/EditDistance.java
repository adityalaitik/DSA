void main() {
    System.out.println(minDistance("horse", "ros")); // Output: 3
}

static int minDistance(String s1, String s2) {
    int n = s1.length(), m = s2.length();
    int[][] dp = new int[n + 1][m + 1];

    // Base cases
    //Initialize the first row with values from 0 to n (representing the cost of inserting j characters into an empty string).
    //Initialize the first column with values from 0 to m (representing the cost of deleting i characters from an empty string).
    for (int i = 0; i <= n; i++) dp[i][0] = i;
    for (int j = 0; j <= m; j++) dp[0][j] = j;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1];
            } else {
                dp[i][j] = 1 + Math.min(
                        dp[i - 1][j],                  // delete
                        Math.min(dp[i][j - 1],         // insert
                                dp[i - 1][j - 1])      // replace
                );
            }
        }
    }
    return dp[n][m];
}

