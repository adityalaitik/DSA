# 🎒 0/1 Knapsack — Dynamic Programming (Java)

---

## 🧩 Problem Statement

You are given:
- `n` items, each with a **weight** and a **value**.
- A **bag capacity** `W`.

You must find the **maximum total value** that can be obtained  
by selecting items **without exceeding** the capacity.  
Each item can be taken **at most once** → hence the name **0/1 Knapsack**.

---

## 🔹 Pattern Type
- **DP Type:** Choice-based (0/1 Decision)
- **Structure:** 2D DP → `dp[n][W]`
- **Core Idea:** *Include or Exclude* each item.

---

## 🔹 Recurrence Relation

Let:
- `wt[]` = weights of items
- `val[]` = values of items
- `n` = number of items
- `W` = bag capacity

Then:
f(i, W) = max(
val[i] + f(i-1, W - wt[i]), // include item i
f(i-1, W) // exclude item i
)

markdown
Copy code

**Base Case:**
f(0, W) = val[0] if wt[0] <= W else 0

pgsql
Copy code

---

## 🔹 Intuition

At each item `i`, you have **two choices**:
- 🟢 **Include it** if it fits → add its value and reduce capacity.
- 🔴 **Exclude it** → move to the next item.

Choose the option that gives **maximum total value**.

---

## 🔹 Approaches Summary

| Approach | Description | Time | Space |
|-----------|--------------|------|-------|
| **Recursion** | Try all combinations | O(2ⁿ) ❌ | O(n) |
| **Memoization (Top-Down)** | Cache results | O(n × W) ✅ | O(n × W) |
| **Tabulation (Bottom-Up)** | Iterative DP | O(n × W) ✅ | O(n × W) |
| **Space Optimized** | Use 1D array | O(n × W) ✅ | O(W) ✅✅ |

---

## 💻 Java Implementations

### 1️⃣ Recursive
```java
public class KnapsackRecursive {
    static int knapsack(int i, int W, int[] wt, int[] val) {
        if (i == 0) {
            return (wt[0] <= W) ? val[0] : 0;
        }

        int notTake = knapsack(i - 1, W, wt, val);
        int take = Integer.MIN_VALUE;
        if (wt[i] <= W)
            take = val[i] + knapsack(i - 1, W - wt[i], wt, val);

        return Math.max(take, notTake);
    }

    public static void main(String[] args) {
        int[] wt = {1, 2, 4, 5};
        int[] val = {5, 4, 8, 6};
        int W = 5;
        int n = wt.length;
        System.out.println(knapsack(n - 1, W, wt, val)); // Output: 13
    }
}
2️⃣ Memoization (Top-Down)
java
Copy code
import java.util.Arrays;

public class KnapsackMemo {
    static int knapsack(int i, int W, int[] wt, int[] val, int[][] dp) {
        if (i == 0) return (wt[0] <= W) ? val[0] : 0;
        if (dp[i][W] != -1) return dp[i][W];

        int notTake = knapsack(i - 1, W, wt, val, dp);
        int take = Integer.MIN_VALUE;
        if (wt[i] <= W)
            take = val[i] + knapsack(i - 1, W - wt[i], wt, val, dp);

        return dp[i][W] = Math.max(take, notTake);
    }

    public static void main(String[] args) {
        int[] wt = {1, 2, 4, 5};
        int[] val = {5, 4, 8, 6};
        int W = 5;
        int n = wt.length;
        int[][] dp = new int[n][W + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        System.out.println(knapsack(n - 1, W, wt, val, dp)); // Output: 13
    }
}
3️⃣ Tabulation (Bottom-Up)
java
Copy code
public class KnapsackTab {
    static int knapsack(int[] wt, int[] val, int W) {
        int n = wt.length;
        int[][] dp = new int[n][W + 1];

        for (int w = wt[0]; w <= W; w++) dp[0][w] = val[0];

        for (int i = 1; i < n; i++) {
            for (int w = 0; w <= W; w++) {
                int notTake = dp[i - 1][w];
                int take = (wt[i] <= w) ? val[i] + dp[i - 1][w - wt[i]] : Integer.MIN_VALUE;
                dp[i][w] = Math.max(take, notTake);
            }
        }
        return dp[n - 1][W];
    }

    public static void main(String[] args) {
        int[] wt = {1, 2, 4, 5};
        int[] val = {5, 4, 8, 6};
        int W = 5;
        System.out.println(knapsack(wt, val, W)); // Output: 13
    }
}
4️⃣ Space Optimized
java
Copy code
public class KnapsackSpaceOpt {
    static int knapsack(int[] wt, int[] val, int W) {
        int n = wt.length;
        int[] prev = new int[W + 1];

        for (int w = wt[0]; w <= W; w++) prev[w] = val[0];

        for (int i = 1; i < n; i++) {
            for (int w = W; w >= 0; w--) {
                int notTake = prev[w];
                int take = (wt[i] <= w) ? val[i] + prev[w - wt[i]] : Integer.MIN_VALUE;
                prev[w] = Math.max(take, notTake);
            }
        }
        return prev[W];
    }

    public static void main(String[] args) {
        int[] wt = {1, 2, 4, 5};
        int[] val = {5, 4, 8, 6};
        int W = 5;
        System.out.println(knapsack(wt, val, W)); // Output: 13
    }
}
🔹 Key Points to Remember
✅ Decision pattern: "Include / Exclude"
✅ DP dimensions: dp[n][W]
✅ For space optimization: iterate capacity backward (for (w = W; w >= 0; w--))
✅ Base for multiple problems:

Subset Sum

Equal Partition

Target Sum

Count Subsets with Given Sum

🧠 Pattern Recognition
If the problem statement includes:

"You can either take an element or skip it under some constraint"

→ It's a 0/1 Knapsack type DP.

🔹 Common Variants
Problem	Description	Change
Subset Sum	Can we form a subset with sum = S?	Use boolean DP
Equal Partition	Split array into 2 subsets of equal sum	Target = totalSum/2
Target Sum	Assign +/− signs to reach target	Convert to Subset Sum
Count of Subsets with Sum S	Count number of subsets	Use counting DP

🏁 Quick Recap
Concept	Formula / Meaning
Recurrence	f(i, W) = max(val[i] + f(i-1, W - wt[i]), f(i-1, W))
Base Case	if (i==0) return val[0] if wt[0] ≤ W else 0
Time Complexity	O(n × W)
Space Complexity	O(n × W) → O(W) optimized
DP Type	0/1 Choice-based
Pattern Keyword	"Include or Exclude"
Example Output	13 for wt={1,2,4,5}, val={5,4,8,6}, W=5

🧠 Interview Tip
If a problem involves subset-like choices with a limit/constraint (sum or capacity) → always think 0/1 Knapsack first.

