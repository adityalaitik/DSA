# ✏️ Edit Distance (Levenshtein Distance) — Dynamic Programming (Java)

---

## 🧩 Problem Statement

Given two strings `s1` and `s2`,  
return the **minimum number of operations** needed to convert `s1` into `s2`.

Allowed operations:
1. **Insert** a character
2. **Delete** a character
3. **Replace** a character

---

## 🔹 Example
Input:
s1 = "horse"
s2 = "ros"

Output: 3

Explanation:
horse → rorse (replace h with r)
rorse → rose (delete r)
rose → ros (delete e)

yaml
Copy code

---

## 🔹 Pattern Type
- **Type:** 2D DP
- **Parent Pattern:** LCS
- **Why important:** Used in NLP, diff tools, DNA sequence alignment, spell-check.

---

## 🔹 DP State Definition

Let  
dp[i][j] = minimum operations to convert s1[0..i-1] → s2[0..j-1]

yaml
Copy code

---

## 🔹 Base Cases

dp[0][j] = j // need j inserts
dp[i][0] = i // need i deletes

yaml
Copy code

---

## 🔹 Recurrence Relation

### If characters match:
dp[i][j] = dp[i-1][j-1]

less
Copy code

### If mismatch: consider all 3 operations:

| Operation | Meaning | Transition |
|-----------|----------|-------------|
| Insert | Match s2[j-1] by inserting | `1 + dp[i][j-1]` |
| Delete | Delete s1[i-1] | `1 + dp[i-1][j]` |
| Replace | Replace s1[i-1] with s2[j-1] | `1 + dp[i-1][j-1]` |

So:
dp[i][j] = 1 + min(
dp[i-1][j], // delete
dp[i][j-1], // insert
dp[i-1][j-1] // replace
)

arduino
Copy code

---

## 💻 Java Implementation — Tabulation

```java
public class EditDistance {
    static int minDistance(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        // Base cases
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

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros")); // Output: 3
    }
}
💻 Space Optimized Version — O(m)
java
Copy code
public class EditDistanceSpaceOpt {
    static int minDistance(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[] prev = new int[m + 1];

        for (int j = 0; j <= m; j++) prev[j] = j;

        for (int i = 1; i <= n; i++) {
            int[] curr = new int[m + 1];
            curr[0] = i;

            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    curr[j] = prev[j - 1];
                } else {
                    curr[j] = 1 + Math.min(prev[j], 
                                   Math.min(curr[j - 1], prev[j - 1]));
                }
            }
            prev = curr;
        }

        return prev[m];
    }
}
🔹 Key Points to Remember
✔️ 3 operations → insert, delete, replace
✔️ Uses min(), not max
✔️ Very similar to LCS, but allows modifications
✔️ Base rows/cols represent inserting/deleting all characters
✔️ Time: O(n × m)
✔️ Space: O(n × m) → O(m) optimized

🔥 Pattern Recognition
If the problem asks:

“Minimum operations to transform string A → B”

“Insert/Delete/Replace allowed”

“Edit distance / Levenshtein distance”

→ It is this exact DP pattern.

🔹 Common Variants
Problem	How related
LCS	Edit distance = (n + m - 2×LCS) when only insert/delete
Convert A to B with cost	Add cost weights to ops
Print actual edit operations	Backtrack dp table
Damerau–Levenshtein	Add swap operation

🧾 Quick Recap
Concept	Meaning
dp[i][j]	Edit distance between prefixes
Match	dp[i][j] = dp[i-1][j-1]
Mismatch	insert/delete/replace
Base	dp[i][0] = i, dp[0][j] = j
Time	O(n × m)
Space	O(n × m) → O(m)

🧠 Interview Tip
If characters match → no edit needed.

If mismatch → choose the operation that leads to the cheapest previous state + 1.

Always think in terms of transforming prefixes.
