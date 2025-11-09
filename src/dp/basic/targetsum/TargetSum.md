# 🎯 Target Sum — Dynamic Programming (Java)

---

## 🧩 Problem Statement

You are given an integer array `nums` and an integer `target`.  
You must assign `+` or `−` signs to each element in `nums`  
such that the resulting expression **evaluates to `target`**.

Return the **number of ways** to assign signs to reach the target.

---

## 🔹 Example
Input: nums = [1, 1, 2, 3], target = 1
Output: 3
Explanation:

(+1) + (+1) + (−2) + (+3) = 3

(+1) + (−1) + (+2) + (−3) = -1 ❌

(+1) + (+1) + (+2) + (−3) = 1 ✅

(−1) + (+1) + (+2) + (+3) = 5 ❌
Total valid ways = 3




---

## 🔹 Pattern Type
- **Type:** 0/1 Knapsack — Counting Variant
- **Derived From:** Count of Subsets with Given Sum
- **Concept:** Equation transformation → subset sum counting.

---

## 🧠 Intuition — Equation Transformation

We split elements into two groups:
- `S1`: elements with `+` sign
- `S2`: elements with `−` sign

Then:
S1 - S2 = target
S1 + S2 = totalSum

css


From these equations:
2 * S1 = target + totalSum
S1 = (target + totalSum) / 2




So the problem reduces to:
> “Count the number of subsets whose sum = (target + totalSum) / 2”

---

## ⚠️ Important Conditions
- `(target + totalSum)` must be **even**
- `target` must not exceed `totalSum`  
  Otherwise, return `0`.

---

## 🔹 Recurrence Relation

Let `f(i, sum)` = number of subsets using first `i` elements with sum = `sum`.

f(i, sum) = f(i-1, sum) + f(i-1, sum - nums[i]) if nums[i] <= sum
f(i, sum) = f(i-1, sum) otherwise

markdown


**Base cases:**
f(0, 0) = 1
f(0, sum) = 1 if nums[0] == sum else 0

pgsql


---

## 🔹 Approaches Summary

| Approach | Description | Time | Space |
|-----------|--------------|------|-------|
| **Recursion** | Try all +/- sign combinations | O(2ⁿ) ❌ | O(n) |
| **Memoization** | Cache overlapping states | O(n × sum) ✅ | O(n × sum) |
| **Tabulation** | Bottom-up | O(n × sum) ✅ | O(n × sum) |
| **Space Optimized** | 1D DP | O(n × sum) ✅ | O(sum) ✅✅ |

---

## 💻 Java Implementations

### 1️⃣ Optimized Solution using Subset Sum Transformation
```java
import java.util.Arrays;

public class TargetSum {
    static int findTargetSumWays(int[] nums, int target) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        // Impossible cases
        if (target > totalSum || (target + totalSum) % 2 != 0) return 0;

        int subsetSum = (target + totalSum) / 2;
        return countSubsets(nums, subsetSum);
    }

    static int countSubsets(int[] nums, int sum) {
        int n = nums.length;
        int[][] dp = new int[n][sum + 1];

        // Base case
        for (int i = 0; i < n; i++) dp[i][0] = 1;
        if (nums[0] <= sum) dp[0][nums[0]] += 1;

        for (int i = 1; i < n; i++) {
            for (int t = 0; t <= sum; t++) {
                int notTake = dp[i - 1][t];
                int take = 0;
                if (nums[i] <= t)
                    take = dp[i - 1][t - nums[i]];
                dp[i][t] = take + notTake;
            }
        }

        return dp[n - 1][sum];
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3};
        int target = 1;
        System.out.println(findTargetSumWays(nums, target)); // Output: 3
    }
}
2️⃣ Space Optimized (1D DP)
java

public class TargetSumSpaceOpt {
    static int findTargetSumWays(int[] nums, int target) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        if (target > totalSum || (target + totalSum) % 2 != 0) return 0;

        int sum = (target + totalSum) / 2;
        int[] prev = new int[sum + 1];
        prev[0] = 1;

        if (nums[0] <= sum) prev[nums[0]] += 1;

        for (int i = 1; i < nums.length; i++) {
            int[] curr = new int[sum + 1];
            curr[0] = 1;
            for (int t = 0; t <= sum; t++) {
                int notTake = prev[t];
                int take = 0;
                if (nums[i] <= t)
                    take = prev[t - nums[i]];
                curr[t] = take + notTake;
            }
            prev = curr;
        }

        return prev[sum];
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3};
        int target = 1;
        System.out.println(findTargetSumWays(nums, target)); // Output: 3
    }
}
🔹 Key Points to Remember
✅ Transformation formula:

subsetSum = (target + totalSum) / 2

✅ Constraints:

(target + totalSum) must be even

target <= totalSum

✅ Base Case:

sum == 0 → 1 way (empty subset)

✅ Handles Zeros:
If there are zeros, they can be included/excluded freely, doubling combinations.
For accurate zero handling → initialize DP accordingly.

🧠 Pattern Recognition
When you see “assign + or − signs” or “partition into positive/negative groups to reach a target”,
think Target Sum → transform into Subset Sum (Counting).

🔹 Common Variants
Problem	Description
Count of Subsets with Given Sum	Base form of Target Sum
Partition with Given Difference	General form → difference = target
Target Sum (Leetcode 494)	Standard problem statement
Perfect Sum Problem (GFG)	Handles zeros explicitly

🧾 Quick Recap
Concept	Formula / Meaning
Transformation	S1 = (target + totalSum) / 2
Recurrence	f(i, t) = f(i-1, t) + f(i-1, t - nums[i])
Base Case	t == 0 → 1
Time Complexity	O(n × sum)
Space Complexity	O(n × sum) → O(sum)
Type	Counting Subset DP
Output	Number of ways

🧠 Interview Tip
If you see signs (+, −) or differences between subsets,
immediately think → Target Sum = Subset Sum with Transformation.



