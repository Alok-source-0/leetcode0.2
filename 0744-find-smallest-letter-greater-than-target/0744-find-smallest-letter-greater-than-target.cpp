class Solution {
public:
    char nextGreatestLetter(vector<char>& letters, char target) {
        int left = 0;
        int right = letters.size() - 1;
        
        // If the target is outside the range of the array, 
        // the wrap-around rule applies immediately.
        if (target >= letters[right]) {
            return letters[0];
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (letters[mid] <= target) {
                // If mid is less than or equal to target, 
                // we need to look in the right half.
                left = mid + 1;
            } else {
                // If mid is greater than target, 
                // this could be our answer, but there might be a smaller one to the left.
                right = mid - 1;
            }
        }

        // After the loop, 'left' will point to the smallest character 
        // that is strictly greater than the target.
        return letters[left];
    }
};