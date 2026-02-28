class Solution {
    public int concatenatedBinary(int n) {
        long res = 0;
        int MOD = 1_000_000_007;
        int bitLength = 0;

        for (int i = 1; i <= n; i++) {
            // If i is a power of 2, the number of bits increases
            // (i & (i - 1)) == 0 is a classic check for power of 2
            if ((i & (i - 1)) == 0) {
                bitLength++;
            }

            // Shift current result by the bitLength of i, then add i
            res = ((res << bitLength) | i) % MOD;
        }

        return (int) res;
    }
}