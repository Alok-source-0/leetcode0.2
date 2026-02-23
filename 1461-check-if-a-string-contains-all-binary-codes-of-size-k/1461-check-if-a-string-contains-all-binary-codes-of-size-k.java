class Solution {
    public boolean hasAllCodes(String s, int k) {
        // Total number of distinct binary codes of length k
        int requiredCount = 1 << k; 
        Set<String> seen = new HashSet<>();
        
        // Iterate through s to find all substrings of length k
        for (int i = 0; i <= s.length() - k; i++) {
            String sub = s.substring(i, i + k);
            seen.add(sub);
            
            // Optimization: if we've found them all, stop early
            if (seen.size() == requiredCount) {
                return true;
            }
        }
        
        return false;
    }
}