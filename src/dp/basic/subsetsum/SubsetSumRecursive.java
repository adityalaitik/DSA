void main() {
    int[] arr = {2, 3, 7, 8, 10};
    int target = 11;
    System.out.println(subsetSum(arr.length - 1, target, arr)); // true
}

private static boolean subsetSum(int i, int target, int[] arr) {
    if (target == 0) return true;
    if (i == 0) return arr[0] == target;

    boolean notTake = subsetSum(i - 1, target, arr);
    boolean take = false;
    if (arr[i] <= target)
        take = subsetSum(i - 1, target - arr[i], arr);

    return take || notTake;
}