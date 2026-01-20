class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int target = nums.get(i);
            ans[i] = -1; // Default if no value is found
            
            // Brute force search for the smallest x
            // Since nums[i] is small (1000), this is very efficient
            for (int x = 0; x <= target; x++) {
                if ((x | (x + 1)) == target) {
                    ans[i] = x;
                    break; // Found the minimum, move to next num
                }
            }
        }
        
        return ans;
    }
}
