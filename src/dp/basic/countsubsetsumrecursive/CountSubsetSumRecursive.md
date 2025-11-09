# 🔢 Count of Subsets with Given Sum — Dynamic Programming (Java)

---

## 🧩 Problem Statement

You are given an array `arr[]` of size `n` and an integer `target`.  
Find the **number of subsets** whose **sum equals `target`**.

---

## 🔹 Example
Input: arr = [1, 2, 3], target = 3
Output: 2
Explanation: Subsets are [1, 2] and [3].

yaml


---

## 🔹 Pattern Type
- **Type:** 0/1 Knapsack (Counting Variation)
- **Derived From:** Subset Sum Problem
- **Key Change:** Instead of `true/false`, count all possible subsets.

---

## 🔹 Recurrence Relation

Let `f(i, target)` = number of subsets from first `i` items that sum to `target`.

Then:
f(i, target) = f(i-1, target) + f(i-1, target - arr[i]) if arr[i] <= target
f(i, target) = f(i-1, target) otherwise

markdown


**Base cases:**
f(0, 0) = 1 (empty subset)
f(0, target) = 1 if arr[0] == target else 0

yaml


---

## 🔹 Intuition

At each element `i`:
- 🔸 **Include it** → add `f(i-1, target - arr[i])`
- 🔹 **Exclude it** → add `f(i-1, target)`
- Total ways = sum of both choices

---

## 🔹 Approaches Summary

| Approach | Description | Time | Space |
|-----------|--------------|------|-------|
| **Recursion** | Explore all subsets | O(2ⁿ) ❌ | O(n) |
| **Memoization** | Cache overlapping states | O(n × target) ✅ | O(n × target) |
| **Tabulation** | Iterative DP | O(n × target) ✅ | O(n × target) |
| **Space Optimized** | 1D DP | O(n × target) ✅ | O(target) ✅✅ |

---

## 💻 Java Implementations

### 1️⃣ Recursive
```java
public class CountSubsetSumRecursive {
    static int countSubsets(int i, int target, int[] arr) {
        if (target == 0) return 1;
        if (i == 0) return arr[0] == target ? 1 : 0;

        int notTake = countSubsets(i - 1, target, arr);
        int take = 0;
        if (arr[i] <= target)
            take = countSubsets(i - 1, target - arr[i], arr);

        return take + notTake;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int target = 3;
        System.out.println(countSubsets(arr.length - 1, target, arr)); // Output: 2
    }
}
2️⃣ Memoization (Top-Down)
java

import java.util.Arrays;

public class CountSubsetSumMemo {
    static int countSubsets(int i, int target, int[] arr, int[][] dp) {
        if (target == 0) return 1;
        if (i == 0) return arr[0] == target ? 1 : 0;
        if (dp[i][target] != -1) return dp[i][target];

        int notTake = countSubsets(i - 1, target, arr, dp);
        int take = 0;
        if (arr[i] <= target)
            take = countSubsets(i - 1, target - arr[i], arr, dp);

        return dp[i][target] = take + notTake;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int target = 3;
        int[][] dp = new int[arr.length][target + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        System.out.println(countSubsets(arr.length - 1, target, arr, dp)); // Output: 2
    }
}
3️⃣ Tabulation (Bottom-Up)
java

public class CountSubsetSumTab {
    static int countSubsets(int[] arr, int target) {
        int n = arr.length;
        int[][] dp = new int[n][target + 1];

        // Base cases
        for (int i = 0; i < n; i++) dp[i][0] = 1;
        if (arr[0] <= target) dp[0][arr[0]] = 1;

        for (int i = 1; i < n; i++) {
            for (int t = 0; t <= target; t++) {
                int notTake = dp[i - 1][t];
                int take = 0;
                if (arr[i] <= t)
                    take = dp[i - 1][t - arr[i]];
                dp[i][t] = take + notTake;
            }
        }

        return dp[n - 1][target];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int target = 3;
        System.out.println(countSubsets(arr, target)); // Output: 2
    }
}
4️⃣ Space Optimized (1D DP)
java

public class CountSubsetSumSpaceOpt {
    static int countSubsets(int[] arr, int target) {
        int n = arr.length;
        int[] prev = new int[target + 1];
        prev[0] = 1;

        if (arr[0] <= target) prev[arr[0]] = 1;

        for (int i = 1; i < n; i++) {
            int[] curr = new int[target + 1];
            curr[0] = 1;
            for (int t = 0; t <= target; t++) {
                int notTake = prev[t];
                int take = 0;
                if (arr[i] <= t)
                    take = prev[t - arr[i]];
                curr[t] = take + notTake;
            }
            prev = curr;
        }

        return prev[target];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int target = 3;
        System.out.println(countSubsets(arr, target)); // Output: 2
    }
}
🔹 Key Points to Remember
✅ Decision type: Include / Exclude
✅ Goal: Count total subsets, not just existence
✅ Base cases:

target == 0 → 1 (empty subset)

i == 0 → 1 if arr[0] == target, else 0

✅ DP dimensions: n × target
✅ Time Complexity: O(n × target)
✅ Space Complexity: O(n × target) → O(target)

🧠 Pattern Recognition
Whenever a problem asks “how many subsets/combinations can achieve a given sum?”,
it’s the counting version of Subset Sum, hence Knapsack (count) DP.

🔹 Common Variants
Problem	Description
Target Sum (Leetcode 494)	Count ways to assign +/− signs to reach a target sum
Number of Dice Rolls With Target Sum	Similar counting pattern
Perfect Sum Problem (GFG)	Count subsets with given sum (handles zeros)

🧾 Quick Recap
Concept	Formula / Meaning
Relation	f(i, t) = f(i-1, t) + f(i-1, t - arr[i])
Base	f(i, 0)=1, f(0, t)=(arr[0]==t)
Time	O(n × target)
Space	O(n × target) → O(target)
Type	Counting DP
Output	Integer count

🧠 Interview Tip
If you need the number of ways / count of subsets,
use addition (+) instead of OR (||) in Subset Sum DP.

