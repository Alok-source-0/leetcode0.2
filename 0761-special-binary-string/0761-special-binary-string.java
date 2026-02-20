class Solution {
    public String makeLargestSpecial(String s) {
        int count = 0, i = 0;
        List<String> res = new ArrayList<>();
        
        for (int j = 0; j < s.length(); ++j) {
            if (s.charAt(j) == '1') count++;
            else count--;
            
            // When count hits 0, we've found a complete special substring
            if (count == 0) {
                // We strip the outer '1' and '0', recurse on the inside, 
                // then put them back.
                res.add("1" + makeLargestSpecial(s.substring(i + 1, j)) + "0");
                i = j + 1;
            }
        }
        
        // Sort substrings descending to get the lexicographically largest string
        Collections.sort(res, Collections.reverseOrder());
        return String.join("", res);
    }
}