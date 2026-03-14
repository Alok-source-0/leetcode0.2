class Solution {
    int count = 0;
    String result = "";

    public String getHappyString(int n, int k) {
        backtrack(n, k, new StringBuilder());
        return result;
    }

    private boolean backtrack(int n, int k, StringBuilder sb) {
        if (sb.length() == n) {
            count++;
            if (count == k) {
                result = sb.toString();
                return true; // Found the k-th string
            }
            return false;
        }

        for (char c : new char[]{'a', 'b', 'c'}) {
            // Check the "happy" condition: current char != last char
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == c) {
                continue;
            }

            sb.append(c);
            if (backtrack(n, k, sb)) return true; // Optimization: stop early
            sb.deleteCharAt(sb.length() - 1); // Backtrack
        }

        return false;
    }
}