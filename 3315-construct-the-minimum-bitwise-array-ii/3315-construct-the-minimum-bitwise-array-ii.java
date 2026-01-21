class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int target = nums.get(i);
            if (target == 2) {
                ans[i] = -1;
                continue;
            }
           
            int firstZeroBit = 0;
            for (int b = 0; b <= 30; b++) {
             
                if (((target >> b) & 1) == 0) {
                    firstZeroBit = b;
                    break;
                }
            }
            
            ans[i] = target ^ (1 << (firstZeroBit - 1));
        }
        
        return ans;
    }
}