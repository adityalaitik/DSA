void main() {
    String s1 = "abac";
    String s2 = "cab";
    System.out.println(shortestCommonSupersequence(s1, s2)); // Output: cabac
}

private String shortestCommonSupersequence(String s1, String s2) {
    int n = s1.length(), m = s2.length();
    int[][] dp = new int[n + 1][m + 1];

    // Step 1: Build LCS DP table
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1))
                dp[i][j] = 1 + dp[i - 1][j - 1];
            else
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
    }

    // Step 2: Backtrack to build SCS
    int i = n, j = m;
    StringBuilder sb = new StringBuilder();

    while (i > 0 && j > 0) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            sb.append(s1.charAt(i - 1));
            i--; j--;
        } else if (dp[i - 1][j] > dp[i][j - 1]) {
            sb.append(s1.charAt(i - 1));
            i--;
        } else {
            sb.append(s2.charAt(j - 1));
            j--;
        }
    }

    // Add remaining characters
    while (i > 0) sb.append(s1.charAt(i-- - 1));
    while (j > 0) sb.append(s2.charAt(j-- - 1));

    return sb.reverse().toString();
}



