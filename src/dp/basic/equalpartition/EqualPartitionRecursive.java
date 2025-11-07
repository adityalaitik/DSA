void main(){
    int[] nums = {1, 5, 11, 5};
    int sum = 0;
    for (int num : nums) sum += num;

    if (sum % 2 != 0) {
        IO.println(false);
        return;
    }

    System.out.println(canPartition(nums.length - 1, sum / 2, nums)); // true
}

private boolean canPartition(int i, int target, int[] nums) {
    if(target ==0) return true;

    if(i==0) return nums[0] == target;

    boolean notTaken = canPartition(i-1, target, nums);

    boolean taken = false;

    if(nums[i]< target)
        taken = canPartition(i-1, target- nums[i], nums);

    return taken || notTaken;
}