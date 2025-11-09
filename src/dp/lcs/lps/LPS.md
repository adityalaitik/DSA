# 💎 Longest Palindromic Subsequence (LPS) — Dynamic Programming (Java)

---

## 🧩 Problem Statement

Given a string `s`,  
return the **length of the longest subsequence** of `s` that is also a **palindrome**.

---

## 🔹 Example
Input: s = "bbbab"
Output: 4

Explanation:
One LPS is "bbbb"

yaml
Copy code

---

## 🔹 Key Insight (Core Trick)

A **palindromic subsequence** reads the same forward and backward.

So:

LPS(s) = LCS(s, reverse(s))

yaml
Copy code

This is the *entire trick*.

---

## 🔹 Why LCS?
If a subsequence appears in both:
- The original string
- Its reversed version

Then it must be a **palindrome**.

---

## 🔹 Pattern Type
- **Type:** String DP
- **Parent Pattern:** LCS
- **Trick:** Reverse the string and compute LCS

---

## 💻 Java Code — Longest Palindromic Subsequence

```java
public class LongestPalindromicSubsequence {

    static int longestPalindromeSubseq(String s) {
        String r = new StringBuilder(s).reverse().toString();
        int n = s.length();
        
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == r.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[n][n];
    }

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("bbbab")); // Output: 4
    }
}
💻 Space Optimized Version (1D DP)
java
Copy code
public class LPS_SpaceOptimized {

    static int longestPalindromeSubseq(String s) {
        String r = new StringBuilder(s).reverse().toString();
        int n = s.length();
        
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == r.charAt(j - 1))
                    curr[j] = 1 + prev[j - 1];
                else
                    curr[j] = Math.max(prev[j], curr[j - 1]);
            }
            prev = curr.clone();
        }

        return prev[n];
    }
}
🔹 Key Points to Remember
✔️ LPS is literally LCS of (s, reverse(s))
✔️ Works on subsequences, not substrings
✔️ Base cases same as LCS
✔️ Time complexity: O(n²)
✔️ Space complexity: O(n²) → O(n) optimized

🔎 LPS vs Longest Palindromic Substring (LPSUB)
Feature	LPS (This problem)	Longest Palindromic Substring
Type	Subsequence	Substring (continuous)
Method	LCS with reverse	Expand-around-center / DP
Order	Characters can skip	Must be contiguous
Difficulty	Medium	Easy-Medium

🔥 Classic Variants Based on LPS
Problem	Formula / Approach
Minimum Deletions to Make a Palindrome	n - LPS(s)
Minimum Insertions to Make Palindrome	n - LPS(s)
Print LPS	Backtrack LCS table on (s, reverse(s))
Count Palindromic Subsequences	Different DP (advanced)

🧠 Pattern Recognition
If problem asks:

"Longest palindromic subsequence"

"Minimum insertions/deletions to make palindrome"

"Find a subsequence palindrome"

→ Immediately switch to:
LCS(s, reverse(s))

🧾 Quick Recap
Concept	Meaning
Trick	LPS = LCS(s, reverse(s))
Base	Same as LCS
Match	1 + diagonal
Mismatch	max(up, left)
Time	O(n²)
Space	O(n²) → O(n)
Output	Length of LPS

🧠 Interview Tip
90% of palindrome subsequence questions are solved by reversing the string
and applying a known pattern like LCS.
