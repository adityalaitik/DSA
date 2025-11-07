# 🎯 Subset Sum Problem — Dynamic Programming (Java)

---

## 🧩 Problem Statement

You are given an array of `n` integers and a target sum `S`.  
Determine if there exists a **subset** of the array that sums up to `S`.

---

## 🔹 Example
Input: arr = {2, 3, 7, 8, 10}, S = 11
Output: true
Explanation: Subset {3, 8} = 11

yaml
Copy code

---

## 🔹 Pattern Type
- **Type:** 0/1 Knapsack Variant
- **Decision:** *Include or Exclude each element*
- **Goal:** Check if a sum can be formed (boolean DP).

---

## 🔹 Recurrence Relation
Let:
- `arr[i]` = current element
- `S` = target sum

Then:
f(i, S) = f(i-1, S) OR f(i-1, S - arr[i]) if arr[i] <= S
f(i, S) = f(i-1, S) otherwise

markdown
Copy code

**Base Cases:**
f(0, S) = true if arr[0] == S
f(i, 0) = true (empty subset)
f(0, S) = false otherwise

yaml
Copy code

---

## 🔹 Intuition

At each element, we decide:
- 🟢 **Include it** (if it fits in the remaining sum `S - arr[i]`)
- 🔴 **Exclude it** (move to next element)

If any of these paths leads to forming the sum, return `true`.

---

## 🔹 Approaches Summary

| Approach | Description | Time | Space |
|-----------|--------------|------|-------|
| **Recursion** | Try all subsets | O(2ⁿ) ❌ | O(n) |
| **Memoization** | Cache overlapping subproblems | O(n × S) ✅ | O(n × S) |
| **Tabulation** | Iterative DP | O(n × S) ✅ | O(n × S) |
| **Space Optimized** | 1D DP | O(n × S) ✅ | O(S) ✅✅ |

---

## 💻 Java Implementations

### 1️⃣ Recursive
```java
public class SubsetSumRecursive {
    static boolean subsetSum(int i, int target, int[] arr) {
        if (target == 0) return true;
        if (i == 0) return arr[0] == target;

        boolean notTake = subsetSum(i - 1, target, arr);
        boolean take = false;
        if (arr[i] <= target)
            take = subsetSum(i - 1, target - arr[i], arr);

        return take || notTake;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 7, 8, 10};
        int target = 11;
        System.out.println(subsetSum(arr.length - 1, target, arr)); // true
    }
}
2️⃣ Memoization (Top-Down)
java
Copy code
import java.util.Arrays;

public class SubsetSumMemo {
    static boolean subsetSum(int i, int target, int[] arr, int[][] dp) {
        if (target == 0) return true;
        if (i == 0) return arr[0] == target;
        if (dp[i][target] != -1) return dp[i][target] == 1;

        boolean notTake = subsetSum(i - 1, target, arr, dp);
        boolean take = false;
        if (arr[i] <= target)
            take = subsetSum(i - 1, target - arr[i], arr, dp);

        dp[i][target] = (take || notTake) ? 1 : 0;
        return take || notTake;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 7, 8, 10};
        int target = 11;
        int[][] dp = new int[arr.length][target + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        System.out.println(subsetSum(arr.length - 1, target, arr, dp)); // true
    }
}
3️⃣ Tabulation (Bottom-Up)
java
Copy code
public class SubsetSumTab {
    static boolean subsetSum(int[] arr, int target) {
        int n = arr.length;
        boolean[][] dp = new boolean[n][target + 1];

        // Base case
        for (int i = 0; i < n; i++) dp[i][0] = true;
        if (arr[0] <= target) dp[0][arr[0]] = true;

        for (int i = 1; i < n; i++) {
            for (int t = 1; t <= target; t++) {
                boolean notTake = dp[i - 1][t];
                boolean take = false;
                if (arr[i] <= t)
                    take = dp[i - 1][t - arr[i]];
                dp[i][t] = take || notTake;
            }
        }

        return dp[n - 1][target];
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 7, 8, 10};
        int target = 11;
        System.out.println(subsetSum(arr, target)); // true
    }
}
4️⃣ Space Optimized (1D DP)
java
Copy code
public class SubsetSumSpaceOpt {
    static boolean subsetSum(int[] arr, int target) {
        int n = arr.length;
        boolean[] prev = new boolean[target + 1];
        prev[0] = true;

        if (arr[0] <= target) prev[arr[0]] = true;

        for (int i = 1; i < n; i++) {
            boolean[] curr = new boolean[target + 1];
            curr[0] = true;
            for (int t = 1; t <= target; t++) {
                boolean notTake = prev[t];
                boolean take = false;
                if (arr[i] <= t)
                    take = prev[t - arr[i]];
                curr[t] = take || notTake;
            }
            prev = curr;
        }

        return prev[target];
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 7, 8, 10};
        int target = 11;
        System.out.println(subsetSum(arr, target)); // true
    }
}
🔹 Key Points to Remember
✅ Decision type: Include / Exclude
✅ Goal: Return true if any subset matches the target sum
✅ Base cases:

target == 0 → always true

i == 0 → true only if arr[0] == target

✅ DP size: n × target
✅ Time Complexity: O(n × target)
✅ Space Complexity: O(n × target) → O(target) optimized

🧠 Pattern Recognition
Whenever a problem asks “is there a subset / combination that adds up to X?”,
it’s a Subset Sum (boolean) variation of 0/1 Knapsack.

🔹 Common Variations
Problem	Description	Key Difference
Equal Partition	Split array into 2 equal-sum subsets	Target = totalSum / 2
Target Sum	Assign + or - to reach target	Transform into Subset Sum
Count of Subsets with Sum S	Count possible subsets	Use integer dp count

🧾 Quick Recap
Concept	Formula / Meaning
Recurrence	`f(i, S) = f(i-1, S)
Base	f(i, 0) = true, f(0, S) = arr[0]==S
Time	O(n × S)
Space	O(n × S) → O(S)
Pattern	0/1 Knapsack (Boolean)
Output	true / false

🧠 Interview Tip
If the question says “Can we achieve this sum?” or “Is this possible?”,
always start with the Subset Sum DP pattern.

