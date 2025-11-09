void main() {
    String s1 = "abcde", s2 = "ace";
    System.out.println(lcs(s1, s2, s1.length() - 1, s2.length() - 1)); // Output: 3
}

private int lcs(String s1, String s2, int i, int j) {
    if(i<0 || j< 0) return 0;

    if(s1.charAt(i) == s2.charAt(j)){
        return 1 + lcs(s1,s2,i-1,i-2);
    }else{
        return Math.max(lcs(s1, s2, i-1, j),lcs(s1,s2,i,j-1));
    }
}