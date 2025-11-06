# 🧠 Fibonacci DP — Quick Revision Sheet (Java)

---

## 🔹 Problem Definition

Find the `n`th Fibonacci number.

**Formula:**
f(n) = f(n-1) + f(n-2)

markdown
Copy code
**Base cases:**
f(0) = 0
f(1) = 1

yaml
Copy code

---

## 🔹 Pattern Type

➡️ **1D Dynamic Programming**
➡️ **Overlapping Subproblems + Optimal Substructure**
➡️ **Foundation of all DP problems**

---

## 🔹 Approaches

| Approach | Idea | Time Complexity | Space Complexity |
|-----------|------|-----------------|------------------|
| **Recursion** | Directly follow definition | O(2ⁿ) ❌ | O(n) |
| **Memoization (Top-Down)** | Cache results of recursive calls | O(n) ✅ | O(n) |
| **Tabulation (Bottom-Up)** | Fill `dp[]` iteratively | O(n) ✅ | O(n) |
| **Space Optimized** | Only store last two values | O(n) ✅ | O(1) ✅✅ |

---

## 🔹 Recurrence Relation

f(0) = 0
f(1) = 1
f(n) = f(n-1) + f(n-2) for n ≥ 2

yaml
Copy code

---

## 🔹 Java Code Snippets

### 1️⃣ Recursion
```java
int fib(int n) {
    if (n <= 1) return n;
    return fib(n-1) + fib(n-2);
}
2️⃣ Memoization (Top-Down)
java
Copy code
int fib(int n, int[] dp) {
    if (n <= 1) return n;
    if (dp[n] != -1) return dp[n];
    return dp[n] = fib(n-1, dp) + fib(n-2, dp);
}
3️⃣ Tabulation (Bottom-Up)
java
Copy code
int fib(int n) {
    int[] dp = new int[n+1];
    dp[0] = 0;
    dp[1] = 1;
    for (int i = 2; i <= n; i++)
        dp[i] = dp[i-1] + dp[i-2];
    return dp[n];
}
4️⃣ Space Optimized
java
Copy code
int fib(int n) {
    if (n <= 1) return n;
    int prev2 = 0, prev = 1;
    for (int i = 2; i <= n; i++) {
        int curr = prev + prev2;
        prev2 = prev;
        prev = curr;
    }
    return prev;
}
🔹 Common Interview Points
✅ Fibonacci is the base pattern of Dynamic Programming.
✅ Learn to convert Recursion → Memoization → Tabulation → Space Optimization.
✅ Used to explain Overlapping Subproblems & Optimal Substructure.
✅ Space optimization = “use only what you need” (rolling variables).

🧩 Mini Tip
When you see:

"To reach n, you can come from n-1 or n-2"

It’s a Fibonacci-type DP problem
👉 Examples: Climbing Stairs, Tiling Problem, Min Cost Climbing Stairs, etc.

