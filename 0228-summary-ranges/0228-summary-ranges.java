import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        
        // Use .length for arrays
        for (int i = 0; i < nums.length; i++) {
            int start = nums[i];
            
            // Check consecutive numbers
            while (i + 1 < nums.length && nums[i + 1] == nums[i] + 1) {
                i++;
            }
            
            if (start == nums[i]) {
                result.add(String.valueOf(start));
            } else {
                result.add(start + "->" + nums[i]);
            }
        }
        
        return result;
    }
}