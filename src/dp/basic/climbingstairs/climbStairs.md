# 🧗‍♂️ Climbing Stairs — Fibonacci Variant (DP in Java)

---

## 🧩 Problem Statement
You are climbing a staircase with `n` steps.  
You can climb **1** or **2** steps at a time.  
Find **how many distinct ways** you can reach the top.

---

## 🔹 Pattern Type
- **Type:** 1D Dynamic Programming
- **Relation:** Fibonacci Pattern
- **Concept:** Each step depends on the previous two steps.

---

## 🔹 Recurrence Relation
f(0) = 1 // one way (do nothing)
f(1) = 1 // one step
f(n) = f(n-1) + f(n-2) for n >= 2

yaml


---

## 🔹 Intuition
To reach step `n`, you can:
- Come from `n-1` (take 1 step), or
- Come from `n-2` (take 2 steps).

So:
> Total ways = ways to reach (n-1) + ways to reach (n-2)

---

## 🔹 Approaches Summary

| Approach | Description | Time | Space |
|-----------|--------------|------|-------|
| **Recursion** | Brute force tree | O(2ⁿ) ❌ | O(n) |
| **Memoization (Top-Down)** | Cache results | O(n) ✅ | O(n) |
| **Tabulation (Bottom-Up)** | Iterative DP | O(n) ✅ | O(n) |
| **Space Optimized** | Keep only 2 variables | O(n) ✅ | O(1) ✅✅ |

---

## 🔹 Java Implementations

### 1️⃣ Recursive
```java
int climbStairs(int n) {
    if (n <= 1) return 1;
    return climbStairs(n-1) + climbStairs(n-2);
}
2️⃣ Memoization (Top-Down)
java

int climbStairs(int n, int[] dp) {
    if (n <= 1) return 1;
    if (dp[n] != -1) return dp[n];
    return dp[n] = climbStairs(n-1, dp) + climbStairs(n-2, dp);
}

public static void main(String[] args) {
    int n = 5;
    int[] dp = new int[n+1];
    Arrays.fill(dp, -1);
    System.out.println(climbStairs(n, dp)); // Output: 8
}
3️⃣ Tabulation (Bottom-Up)
java

int climbStairs(int n) {
    int[] dp = new int[n+1];
    dp[0] = 1; dp[1] = 1;
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i-1] + dp[i-2];
    }
    return dp[n];
}
4️⃣ Space Optimized
java

int climbStairs(int n) {
    if (n <= 1) return 1;
    int prev2 = 1, prev = 1;
    for (int i = 2; i <= n; i++) {
        int curr = prev + prev2;
        prev2 = prev;
        prev = curr;
    }
    return prev;
}
🔹 Key Takeaways
✅ Same as Fibonacci sequence
✅ Classic count ways type DP
✅ First problem to learn bottom-up DP
✅ Space optimization trick: use two rolling variables

🧠 Similar Problems
🪜 Min Cost Climbing Stairs

🧱 Tiling Problem (2×N tiles)

🪶 Frog Jump (Energy Minimization)

🏁 Quick Recap
f(n) = f(n-1) + f(n-2)
Base: f(0)=1, f(1)=1
DP Type: 1D
Space Optimization: O(1)

🧠 Pattern Keyword:

“To reach n, you can come from (n-1) or (n-2)” → Fibonacci-type DP