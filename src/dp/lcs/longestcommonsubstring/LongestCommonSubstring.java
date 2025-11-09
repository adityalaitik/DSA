void main() {
    String s1 = "abcjklp";
    String s2 = "acjkp";
    System.out.println(longestCommonSubstring(s1, s2)); // Output: 3
}

private int longestCommonSubstring(String s1, String s2) {
    int n= s1.length(),m = s2.length();
    int[][] dp = new int[n+1][m+1];
    int maxlen = 0;

    for (int i = 1; i <=n ; i++) {
        for (int j = 1; j <=m ; j++) {
            if(s1.charAt(i-1) == s2.charAt(j-1)){
                dp[i][j] = 1 + dp[i-1][j-1];
                maxlen = Math.max(maxlen, dp[i][j]);
            }
            else{
                dp[i][j] = 0;
            }

        }

    }
    return maxlen;
}
