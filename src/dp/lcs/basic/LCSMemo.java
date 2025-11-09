void main() {
    String s1 = "abcde", s2 = "ace";
    int[][] dp = new int[s1.length() + 1][s2.length() + 1];
    for (int[] row : dp) Arrays.fill(row, -1);

    System.out.println(lcs(s1, s2, s1.length(), s2.length(), dp)); // Output: 3
}

private int lcs(String s1, String s2, int i, int j, int[][] dp) {
    if(i==0 || j ==0) return 0;

    if(dp[i][j]!= -1) return dp[i][j];

    if(s1.charAt(i-1) == s2.charAt(j-1))
        return dp[i][j] = 1+lcs(s1, s2, i-1, j-1,dp);
    else
        return dp[i][j] = Math.max(lcs(s1,s2,i-1,j,dp),lcs(s1,s2,i,j-1,dp));

}
