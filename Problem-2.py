# Time Complexity : O(N*M)
# Space Complexity : O(26)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Explanation:
# Map each character to its position in alien order.
# Compare each adjacent word pair lexicographically.

class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        
        pos = {c:i for i,c in enumerate(order)}
        
        for i in range(len(words)-1):
            w1 = words[i]
            w2 = words[i+1]
            
            for c1, c2 in zip(w1, w2):
                if c1 != c2:
                    if pos[c1] > pos[c2]:
                        return False
                    break
            else:
                if len(w1) > len(w2):
                    return False
        
        return True