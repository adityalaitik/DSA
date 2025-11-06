void main() {
    IO.println(fib(6));
}

static int fib(int n) {
    if(n<=1) return n;
    int prev2 =0, prev = 1;

    for (int i = 2; i <= n; i++) {
        int cur = prev + prev2;
        prev2 = prev;
        prev = cur;
    }
    return prev;

}

/*
Time: O(n), Space: O(1) ✅✅
 */

