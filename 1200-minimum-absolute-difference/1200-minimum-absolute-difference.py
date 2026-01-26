class Solution:
    def minimumAbsDifference(self, arr):
        arr.sort()
        
        min_diff = float('inf')
        result = []
        
        for i in range(len(arr) - 1):
            current_diff = arr[i+1] - arr[i]
            
            if current_diff < min_diff:
                min_diff = current_diff
                result = [[arr[i], arr[i+1]]]
            
            elif current_diff == min_diff:
                result.append([arr[i], arr[i+1]])
                
        return result