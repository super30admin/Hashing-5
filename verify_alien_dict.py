# Time Complexity : O(n * l)
# Space Complexity : O(1)
# Did this code successfully run on Leetcode : YES
# Any problem you faced while coding this : NO

class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        map = {}

        for i in range(len(order)):
            map[order[i]] = i
        
        for i in range(len(words) - 1):
            word_a = words[i]
            word_b = words[i+1]

            if not self.is_lexicographical(word_a, word_b, map):
                return False
        
        return True
    
    def is_lexicographical(self, word_a, word_b, map):
        i = 0
        while i < min(len(word_a), len(word_b)):
            if word_a[i] == word_b[i]:
                i += 1
            else: 
                if map[word_a[i]] <= map[word_b[i]]:
                    return True
                else:
                    return False

        if i < len(word_a):
            return False
        
        return True
