# 🧵 Longest Common Subsequence (LCS) — Dynamic Programming (Java)

---

## 🧩 Problem Statement

Given two strings `text1` and `text2`,  
find the **length of their longest common subsequence (LCS)**.

A **subsequence** is a sequence derived from another sequence by deleting some or no characters without changing the order of the remaining characters.

---

## 🔹 Example
Input:
text1 = "abcde"
text2 = "ace"

Output: 3

Explanation:
The LCS is "ace", length = 3

yaml


---

## 🔹 Pattern Type
- **Type:** 2D String DP
- **Core Idea:** Compare characters → move backward in both strings
- **Relation:** LCS → foundation of all String-based DPs

---

## 🔹 Recurrence Relation

Let `f(i, j)` = LCS length for `text1[0..i]` and `text2[0..j]`.

Then:
if (text1[i] == text2[j])
f(i, j) = 1 + f(i-1, j-1)
else
f(i, j) = max(f(i-1, j), f(i, j-1))

arduino


**Base case:**
if (i == 0 or j == 0) → f(i, j) = 0

yaml


---

## 🔹 Intuition

At each step:
- 🟢 If characters match → include that character and move diagonally left-up.
- 🔴 If not match → skip one character (either from `text1` or `text2`) and take max of both paths.

---

## 🔹 Approaches Summary

| Approach | Description | Time | Space |
|-----------|--------------|------|-------|
| **Recursion** | Try all subsequence combinations | O(2ⁿ) ❌ | O(n+m) |
| **Memoization** | Cache subproblems | O(n × m) ✅ | O(n × m) |
| **Tabulation** | Bottom-up | O(n × m) ✅ | O(n × m) |
| **Space Optimized** | 1D DP | O(n × m) ✅ | O(m) ✅✅ |

---

## 💻 Java Implementations

### 1️⃣ Recursive
```java
public class LCSRecursive {
    static int lcs(String s1, String s2, int i, int j) {
        if (i < 0 || j < 0) return 0;

        if (s1.charAt(i) == s2.charAt(j))
            return 1 + lcs(s1, s2, i - 1, j - 1);
        else
            return Math.max(lcs(s1, s2, i - 1, j), lcs(s1, s2, i, j - 1));
    }

    public static void main(String[] args) {
        String s1 = "abcde", s2 = "ace";
        System.out.println(lcs(s1, s2, s1.length() - 1, s2.length() - 1)); // Output: 3
    }
}
2️⃣ Memoization (Top-Down)
java

import java.util.Arrays;

public class LCSMemo {
    static int lcs(String s1, String s2, int i, int j, int[][] dp) {
        if (i == 0 || j == 0) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        if (s1.charAt(i - 1) == s2.charAt(j - 1))
            return dp[i][j] = 1 + lcs(s1, s2, i - 1, j - 1, dp);
        else
            return dp[i][j] = Math.max(lcs(s1, s2, i - 1, j, dp),
                                       lcs(s1, s2, i, j - 1, dp));
    }

    public static void main(String[] args) {
        String s1 = "abcde", s2 = "ace";
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        System.out.println(lcs(s1, s2, s1.length(), s2.length(), dp)); // Output: 3
    }
}
3️⃣ Tabulation (Bottom-Up)
java

public class LCSTab {
    static int lcs(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        String s1 = "abcde", s2 = "ace";
        System.out.println(lcs(s1, s2)); // Output: 3
    }
}
4️⃣ Space Optimized (1D DP)
java

public class LCSSpaceOpt {
    static int lcs(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[] prev = new int[m + 1];
        int[] curr = new int[m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    curr[j] = 1 + prev[j - 1];
                else
                    curr[j] = Math.max(prev[j], curr[j - 1]);
            }
            prev = curr.clone();
        }

        return prev[m];
    }

    public static void main(String[] args) {
        String s1 = "abcde", s2 = "ace";
        System.out.println(lcs(s1, s2)); // Output: 3
    }
}
🔹 Key Points to Remember
✅ dp[i][j] → LCS length between prefixes s1[0..i-1] and s2[0..j-1]
✅ If characters match → add 1 and move diagonally
✅ Else → take max() of top or left cell
✅ Base row and column → all zeros (empty string comparison)
✅ Time: O(n × m), Space: O(n × m) → O(m) optimized

🧠 Pattern Recognition
When comparing two sequences (strings/arrays)
and you need to find common / similar patterns,
it’s a Longest Common Subsequence (LCS) DP.

🔹 Common Variants
Problem	Description	Key Difference
Longest Common Substring	Must be contiguous	Reset to 0 when mismatch
Shortest Common Supersequence	Combine both strings with minimal overlap	Derived from LCS
Edit Distance	Min insert/delete/replace to match	Uses LCS concept
Longest Palindromic Subsequence	LCS between string and its reverse	Palindrome-based
Print LCS	Retrieve actual sequence, not just length	Traceback from dp table

🧾 Quick Recap
Concept	Formula / Meaning
Recurrence	f(i, j) = 1 + f(i-1, j-1) if match else max(f(i-1, j), f(i, j-1))
Base	f(0, j) = 0, f(i, 0) = 0
Time	O(n × m)
Space	O(n × m) → O(m)
Type	2D String DP
Output	LCS length

🧠 Interview Tip
LCS is the parent pattern for most string DP problems.
Master it once → you can solve:

Edit Distance

Palindromic Subsequence

Shortest Supersequence

Common Substring problems
instantly by tweaking the recurrence.


