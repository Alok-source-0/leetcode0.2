import java.util.*;

class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        // Step 1: Prepare full fence lists including boundaries
        long[] h = prepareFences(m, hFences);
        long[] v = prepareFences(n, vFences);
        
        // Step 2: Store all possible horizontal gaps in a Set
        Set<Long> hGaps = new HashSet<>();
        for (int i = 0; i < h.length; i++) {
            for (int j = i + 1; j < h.length; j++) {
                hGaps.add(Math.abs(h[i] - h[j]));
            }
        }
        
        // Step 3: Check vertical gaps and track the maximum match
        long maxSide = -1;
        for (int i = 0; i < v.length; i++) {
            for (int j = i + 1; j < v.length; j++) {
                long gap = Math.abs(v[i] - v[j]);
                if (hGaps.contains(gap)) {
                    maxSide = Math.max(maxSide, gap);
                }
            }
        }
        
        if (maxSide == -1) return -1;
        
        // Step 4: Return area modulo 10^9 + 7
        long mod = 1_000_000_007;
        return (int) ((maxSide * maxSide) % mod);
    }
    
    private long[] prepareFences(int boundary, int[] fences) {
        long[] all = new long[fences.length + 2];
        for (int i = 0; i < fences.length; i++) all[i] = fences[i];
        all[fences.length] = 1;
        all[fences.length + 1] = boundary;
        return all;
    }
}