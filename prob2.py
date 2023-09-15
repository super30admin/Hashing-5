# Time Complexity : O(n)
# Space Complexity :O(1)
# Passed on Leetcode: yes

class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        char_order = {char: i for i, char in enumerate(order)}
        
        def compare_words(word1, word2):
            i, j = 0, 0
            
            while i < len(word1) and j < len(word2):
                if char_order[word1[i]] < char_order[word2[j]]:
                    return True
                elif char_order[word1[i]] > char_order[word2[j]]:
                    return False
                else:
                    i += 1
                    j += 1
            
            return i == len(word1)
        
        
        for i in range(len(words) - 1):
            if not compare_words(words[i], words[i + 1]):
                return False
        
        return True
