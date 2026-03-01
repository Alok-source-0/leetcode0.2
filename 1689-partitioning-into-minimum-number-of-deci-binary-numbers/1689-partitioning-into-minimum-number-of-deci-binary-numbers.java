class Solution {
    public int minPartitions(String n) {
        int maxDigit = 0;
        
        for (int i = 0; i < n.length(); i++) {
            // Convert char to int ('0' becomes 0, '9' becomes 9)
            int currentDigit = n.charAt(i) - '0';
            
            // Update maxDigit if the current one is larger
            if (currentDigit > maxDigit) {
                maxDigit = currentDigit;
            }
            
            // Optimization: Since 9 is the highest possible digit, 
            // we can return immediately if we find it.
            if (maxDigit == 9) return 9;
        }
        
        return maxDigit;
    }
}