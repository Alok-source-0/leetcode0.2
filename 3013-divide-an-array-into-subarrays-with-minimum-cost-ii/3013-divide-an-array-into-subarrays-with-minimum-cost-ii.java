class Solution {
    private TreeMap<Integer, Integer> low = new TreeMap<>();
    private TreeMap<Integer, Integer> high = new TreeMap<>();
    private int lowCount = 0;
    private long lowSum = 0;

    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        int required = k - 1;
        
        // Initialize with first window
        for (int i = 1; i <= dist + 1; i++) {
            add(nums[i]);
        }
        
        balance(required);
        long minLowSum = lowSum;

        for (int i = dist + 2; i < n; i++) {
            // Remove the element sliding out of the window
            remove(nums[i - dist - 1]);
            // Add the new element sliding into the window
            add(nums[i]);
            // Re-stabilize the sizes
            balance(required);
            minLowSum = Math.min(minLowSum, lowSum);
        }

        return (long)nums[0] + minLowSum;
    }

    private void add(int val) {
        // Always add to low first, then let balance sort it out
        low.put(val, low.getOrDefault(val, 0) + 1);
        lowSum += val;
        lowCount++;
    }

    private void remove(int val) {
        if (low.containsKey(val)) {
            lowSum -= val;
            lowCount--;
            updateMap(low, val);
        } else {
            // Safety check: only remove from high if it exists there
            if (high.containsKey(val)) {
                updateMap(high, val);
            }
        }
    }

    private void updateMap(TreeMap<Integer, Integer> map, int val) {
        int count = map.get(val);
        if (count == 1) map.remove(val);
        else map.put(val, count - 1);
    }

    private void balance(int required) {
        // 1. If low is too small, pull from high
        while (lowCount < required && !high.isEmpty()) {
            int first = high.firstKey();
            add(first);
            updateMap(high, first);
        }
        
        // 2. If low is too big, push to high
        while (lowCount > required) {
            int last = low.lastKey();
            lowSum -= last;
            lowCount--;
            updateMap(low, last);
            high.put(last, high.getOrDefault(last, 0) + 1);
        }

        // 3. Ensure the largest of low <= smallest of high
        while (!low.isEmpty() && !high.isEmpty() && low.lastKey() > high.firstKey()) {
            int lMax = low.lastKey();
            int hMin = high.firstKey();
            
            // Swap them
            lowSum = lowSum - lMax + hMin;
            updateMap(low, lMax);
            updateMap(high, hMin);
            
            low.put(hMin, low.getOrDefault(hMin, 0) + 1);
            high.put(lMax, high.getOrDefault(lMax, 0) + 1);
        }
    }
}