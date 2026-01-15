import java.util.HashMap;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // Map to store: Key = number, Value = last seen index
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            // If we've seen this number before...
            if (map.containsKey(nums[i])) {
                // Check if the distance is within k
                if (i - map.get(nums[i]) <= k) {
                    return true;
                }
            }
            // Update the map with the latest index for this number
            map.put(nums[i], i);
        }
        
        return false;
    }
}