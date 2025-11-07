void main() {
    int[] wt = {1, 2, 4, 5};
    int[] val = {5, 4, 8, 6};
    int W = 5;
    int n = wt.length;
    IO.println(knapsack(n - 1, W, wt, val));
}

static int knapsack(int i, int w, int[] wt, int[] val) {
    if(i==0) return (wt[0] <=w)? val[0] : 0;

    int notTake = knapsack(i-1,w, wt,val);

    int take = Integer.MIN_VALUE;
    if(wt[i]<= w)
        take = val[i]+ knapsack(i-1,w-wt[i],wt,val);

    return Math.max(notTake,take);
}