# 🧵 Print Longest Common Subsequence (LCS) — Dynamic Programming (Java)

---

## 🧩 Problem Statement

Given two strings `text1` and `text2`,  
find **the actual longest common subsequence (LCS)** string.

---

## 🔹 Example
Input:
text1 = "abcde"
text2 = "ace"

Output:
"ace"


---

## 🔹 Pattern Type
- **Type:** String DP
- **Builds On:** Standard LCS dp table
- **Goal:** Backtrack dp table to reconstruct the subsequence

---

## 🔹 Intuition

1️⃣ First compute the **LCS dp table** (just like standard LCS).  
2️⃣ Then **start from dp[n][m]** and move backwards:
- If characters match → this char is part of LCS
    - Move diagonally `(i-1, j-1)`
- If not match → move in direction of **larger dp value**
    - `(i-1, j)` or `(i, j-1)`

3️⃣ Append matched characters and reverse at the end.

This backtracking gives the actual LCS string.

---

## 🔹 DP Table Definition

Let:
dp[i][j] = LCS length between text1[0..i-1] and text2[0..j-1]




---

## 🔹 Recurrence Relation

Same as LCS:

if (s1[i-1] == s2[j-1])
dp[i][j] = 1 + dp[i-1][j-1]
else
dp[i][j] = max(dp[i-1][j], dp[i][j-1])

pgsql


---

## 💻 Java Implementation (Print LCS)

### ✔️ Build DP table + Backtrack to retrieve LCS
```java
public class PrintLCS {
    static String printLCS(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        // Step 1: Fill dp table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        // Step 2: Backtrack to build LCS string
        int i = n, j = m;
        StringBuilder sb = new StringBuilder();

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                sb.append(s1.charAt(i - 1));  // match found
                i--;
                j--;
            } else {
                // move towards the larger dp value (greedy)
                if (dp[i - 1][j] > dp[i][j - 1])
                    i--;
                else
                    j--;
            }
        }

        return sb.reverse().toString(); // reverse the built string
    }

    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ace";

        System.out.println(printLCS(s1, s2)); // Output: ace
    }
}
🔹 Key Points to Remember
✔️ First fill the dp table (same as normal LCS).
✔️ Backtracking logic:

Match → take char + move diagonal

No match → move towards larger dp value
✔️ Build answer in reverse and then reverse at end.
✔️ Time Complexity = O(n × m)
✔️ Space Complexity = O(n × m)

🧠 Pattern Recognition
If a problem says:

“Print the actual subsequence / path / sequence, not just its length”

And uses:

Two strings

Comparisons

Matching characters

It is most likely based on LCS backtracking.

🔹 Common Problems Based on Print LCS
Problem	Description
Shortest Common Supersequence	Use LCS to merge two strings
Print All LCS	DFS based on LCS table
Print Longest Palindromic Subsequence	LCS between s and reverse(s)
Diff Tools (Git / code diff)	Based on LCS traceback
Longest Common Substring	Variation with contiguous constraint

🧾 Quick Recap
Concept	Formula / Meaning
Match	add char + move diagonal
Mismatch	move to side with larger dp value
Time	O(n × m)
Space	O(n × m)
Output	actual LCS string

🧠 Interview Tip
In DP problems where you compute a table and the output is a sequence,
you almost always need to perform a reverse backtracking through the DP table.

