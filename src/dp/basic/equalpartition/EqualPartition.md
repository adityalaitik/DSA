# ⚖️ Partition Equal Subset Sum — Dynamic Programming (Java)

---

## 🧩 Problem Statement

You are given an array of positive integers `nums`.  
Determine if it can be **partitioned into two subsets** such that the **sum of both subsets is equal**.

---

## 🔹 Example
Input: nums = [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].




---

## 🔹 Pattern Type
- **Type:** 0/1 Knapsack (Boolean variant)
- **Derived From:** Subset Sum Problem
- **Goal:** Check if a subset exists with sum = totalSum / 2

---

## 🔹 Intuition

If the total sum of the array is **odd**, it can never be divided equally → return `false`.

Otherwise, the task becomes:
> “Can we find a subset with sum = totalSum / 2 ?”

This is exactly the **Subset Sum problem**.

---

## 🔹 Steps to Solve

1️⃣ Compute `totalSum` of all elements.  
2️⃣ If `totalSum` is odd → return `false`.  
3️⃣ Otherwise, set `target = totalSum / 2`.  
4️⃣ Check if there exists a subset with sum = `target`.

---

## 🔹 Recurrence Relation

f(i, target) = f(i-1, target) OR f(i-1, target - nums[i])

markdown


**Base cases:**
f(i, 0) = true
f(0, target) = (nums[0] == target)

pgsql


---

## 🔹 Approaches Summary

| Approach | Description | Time | Space |
|-----------|--------------|------|-------|
| **Recursion** | Try all subsets | O(2ⁿ) ❌ | O(n) |
| **Memoization** | Cache results | O(n × target) ✅ | O(n × target) |
| **Tabulation** | Bottom-up DP | O(n × target) ✅ | O(n × target) |
| **Space Optimized** | 1D boolean DP | O(n × target) ✅ | O(target) ✅✅ |

---

## 💻 Java Implementations

### 1️⃣ Recursive
```java
public class EqualPartitionRecursive {
    static boolean canPartition(int i, int target, int[] nums) {
        if (target == 0) return true;
        if (i == 0) return nums[0] == target;

        boolean notTake = canPartition(i - 1, target, nums);
        boolean take = false;
        if (nums[i] <= target)
            take = canPartition(i - 1, target - nums[i], nums);

        return take || notTake;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        int sum = 0;
        for (int num : nums) sum += num;

        if (sum % 2 != 0) {
            System.out.println(false);
            return;
        }

        System.out.println(canPartition(nums.length - 1, sum / 2, nums)); // true
    }
}
2️⃣ Memoization (Top-Down)
java

import java.util.Arrays;

public class EqualPartitionMemo {
    static boolean canPartition(int i, int target, int[] nums, int[][] dp) {
        if (target == 0) return true;
        if (i == 0) return nums[0] == target;
        if (dp[i][target] != -1) return dp[i][target] == 1;

        boolean notTake = canPartition(i - 1, target, nums, dp);
        boolean take = false;
        if (nums[i] <= target)
            take = canPartition(i - 1, target - nums[i], nums, dp);

        dp[i][target] = (take || notTake) ? 1 : 0;
        return take || notTake;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        int sum = Arrays.stream(nums).sum();

        if (sum % 2 != 0) {
            System.out.println(false);
            return;
        }

        int target = sum / 2;
        int[][] dp = new int[nums.length][target + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        System.out.println(canPartition(nums.length - 1, target, nums, dp)); // true
    }
}
3️⃣ Tabulation (Bottom-Up)
java

public class EqualPartitionTab {
    static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;

        if (sum % 2 != 0) return false;

        int target = sum / 2;
        int n = nums.length;
        boolean[][] dp = new boolean[n][target + 1];

        for (int i = 0; i < n; i++) dp[i][0] = true;
        if (nums[0] <= target) dp[0][nums[0]] = true;

        for (int i = 1; i < n; i++) {
            for (int t = 1; t <= target; t++) {
                boolean notTake = dp[i - 1][t];
                boolean take = false;
                if (nums[i] <= t)
                    take = dp[i - 1][t - nums[i]];
                dp[i][t] = take || notTake;
            }
        }
        return dp[n - 1][target];
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition(nums)); // true
    }
}
4️⃣ Space Optimized (1D DP)
java

public class EqualPartitionSpaceOpt {
    static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;

        if (sum % 2 != 0) return false;

        int target = sum / 2;
        boolean[] prev = new boolean[target + 1];
        prev[0] = true;

        if (nums[0] <= target) prev[nums[0]] = true;

        for (int i = 1; i < nums.length; i++) {
            boolean[] curr = new boolean[target + 1];
            curr[0] = true;
            for (int t = 1; t <= target; t++) {
                boolean notTake = prev[t];
                boolean take = false;
                if (nums[i] <= t)
                    take = prev[t - nums[i]];
                curr[t] = take || notTake;
            }
            prev = curr;
        }

        return prev[target];
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition(nums)); // true
    }
}
🔹 Key Points to Remember
✅ If total sum is odd → return false
✅ Reduce to Subset Sum problem with target = totalSum / 2
✅ Base cases:

target == 0 → true

i == 0 → nums[0] == target

✅ DP size: n × target
✅ Time Complexity: O(n × target)
✅ Space Complexity: O(n × target) → O(target)

🧠 Pattern Recognition
If the question involves dividing an array into two equal-sum subsets,
it’s always a Subset Sum → Equal Partition DP pattern.

🔹 Related Problems
Problem	Variation
Subset Sum	Base problem
Target Sum	Transform to Subset Sum
Count of Subsets with Given Sum	Count instead of boolean
Minimum Subset Sum Difference	Optimize the partition difference

🧾 Quick Recap
Concept	Formula / Meaning
Relation	`f(i, t) = f(i-1, t)
Base	f(i, 0) = true, f(0, t) = nums[0]==t
Time	O(n × target)
Space	O(n × target) → O(target)
Type	Boolean (Subset Sum)
Pattern	“Include or Exclude”
Output	true / false

🧠 Interview Tip
Whenever you see “partition array equally” or “divide array into two equal halves”,
think Subset Sum (target = totalSum / 2) immediately.