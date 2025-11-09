# 🌟 Shortest Common Supersequence (SCS) — Dynamic Programming (Java)

---

## 🧩 Problem Statement

Given two strings `s1` and `s2`,  
return the **shortest common supersequence (SCS)**.

A **supersequence** contains both strings as subsequences.

Example:
- s1 = "abac"
- s2 = "cab"

One shortest common supersequence = **"cabac"**

---

## 🔹 Why SCS?
SCS is used in:
- File merging
- Text diff tools
- DNA sequence alignment
- Minimizing merged path from two strings

---

## 🔹 Relationship to LCS

SCS length formula:
SCS = len(s1) + len(s2) - LCS(s1, s2)

csharp
Copy code

To **print** SCS, we:
1. Build full **LCS dp table**
2. Backtrack while building the SCS string

---

## 🔹 Intuition

While backtracking from the dp table:
- If characters match → include the char once
- If not match → take the direction of the larger dp value
- When one string finishes → append the remainder of the other string

This ensures:
- Both strings appear as subsequences
- Extra characters are minimized

---

## 💻 Java Code — Print the Shortest Common Supersequence

```java
public class ShortestCommonSupersequence {

    static String shortestCommonSupersequence(String s1, String s2) {
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

    public static void main(String[] args) {
        String s1 = "abac";
        String s2 = "cab";
        System.out.println(shortestCommonSupersequence(s1, s2)); // Output: cabac
    }
}
🔹 Key Points to Remember
✔️ SCS uses LCS as the backbone
✔️ Merge two strings with minimal duplication
✔️ Print version is built by backtracking LCS table
✔️ Time Complexity → O(n × m)
✔️ Space Complexity → O(n × m)

🔥 Pattern Recognition
If a problem asks:

“Merge two strings with minimal repetition”

“Combine sequences keeping order”

“Find smallest string that contains both as subsequences”

→ It’s an SCS problem using LCS DP.

🔹 Common Variants
Variant	Description
Length only	Use formula: n + m – LCS
Print SCS	Build via LCS backtracking
Merge sequences	Same logic
Find minimal combined path	SCS-based

🧾 Quick Recap
Concept	Meaning
DP table	Same as LCS
Backtracking	Merge characters intelligently
Match	Include char once (move diagonal)
Mismatch	Take larger dp direction
Finish	Append remainder

🧠 Interview Tip
When two strings must be combined with order preserved and duplication minimized,
always think: SCS = LCS + Backtracking.
