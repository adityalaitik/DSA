# 🔥 Longest Common Substring — Dynamic Programming (Java)

---

## 🧩 Problem Statement

Given two strings `text1` and `text2`,  
return the **length of the longest common substring** (contiguous sequence).

---

## 🔹 Example
Input:
text1 = "abcjklp"
text2 = "acjkp"

Output: 3

Explanation:
The longest common substring is "jkp" or "cjk" (length = 3)




---

## 🔹 Difference: Subsequence vs Substring

| Feature | Subsequence (LCS) | Substring |
|---------|--------------------|-----------|
| Characters | Need not be adjacent | Must be **contiguous** |
| DP recurrence | Uses `max()` | **Reset to 0** on mismatch |
| Classic use | Matching with gaps | Finding exact matching blocks |

---

## 🔹 Pattern Type
- **Type:** 2D DP, String Matching
- **Key Insight:** Reset DP value to **0** on mismatch
- **Goal:** Track longest contiguous match

---

## 🔹 Recurrence Relation

Let `dp[i][j]` = length of longest common substring  
ending at `text1[i-1]` and `text2[j-1]`.

if (text1[i-1] == text2[j-1])
dp[i][j] = 1 + dp[i-1][j-1]
else
dp[i][j] = 0 // reset on mismatch




Keep track of:
maxLen = max(maxLen, dp[i][j])

arduino


---

## 💻 Java Implementation — Tabulation

```java
public class LongestCommonSubstring {
    static int longestCommonSubstring(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        int maxLen = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    maxLen = Math.max(maxLen, dp[i][j]);
                } else {
                    dp[i][j] = 0; // reset
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String s1 = "abcjklp";
        String s2 = "acjkp";
        System.out.println(longestCommonSubstring(s1, s2)); // Output: 3
    }
}
💻 Space Optimized Version (1D DP)
java

public class LongestCommonSubstringSpaceOpt {
    static int longestCommonSubstring(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[] prev = new int[m + 1];
        int maxLen = 0;

        for (int i = 1; i <= n; i++) {
            int[] curr = new int[m + 1];
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];
                    maxLen = Math.max(maxLen, curr[j]);
                } else {
                    curr[j] = 0;
                }
            }
            prev = curr;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubstring("abcjklp", "acjkp")); // Output: 3
    }
}
🔹 Key Points to Remember
✔️ Substring → contiguous → reset dp to 0 on mismatch
✔️ Keep a global maxLen
✔️ Same DP table size as LCS, completely different logic
✔️ Time: O(n × m)
✔️ Space: O(n × m) → O(m) optimized

🧠 Pattern Recognition
If the problem says:

“Longest matching block”

“Contiguous common segment”

“Longest exact matching substring”

→ It is Longest Common Substring DP.

🔹 Common Variants
Problem	Description
Longest Palindromic Substring	Same logic but on a single string
Longest Repeating Substring	LCS of string with itself (with index offset)
Longest Common Prefix in many strings	Prefix version of this DP
DNA sequence matching	Substring-level match

🧾 Quick Recap
Concept	Formula / Meaning
Match	dp[i][j] = dp[i-1][j-1] + 1
Mismatch	dp[i][j] = 0
Result	max of all dp[i][j]
Time	O(n × m)
Space	O(n × m) → O(m)
Type	Contiguous string DP

🧠 Interview Tip
If subsequence (not necessarily adjacent) → use LCS

If substring (must be adjacent) → use this DP with reset-to-zero

Many candidates mix these two — don’t! 😉
