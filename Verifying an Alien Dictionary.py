class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        #Approach: Comparing adjacent words
        #Time Complexity: O(n * l)
        #Space Complexity: O(1)
        #where, n is the number of words, and l is the length of an average word
        
        self.orderMap = {}
        for i in range(len(order)):
            self.orderMap[order[i]] = i
            
        for i in range(len(words) - 1):
            word1 = words[i]
            word2 = words[i + 1]
            
            if self.isNotSorted(word1, word2):
                return False
        return True
    
    def isNotSorted(self, word1, word2):
        i = 0
        while i < len(word1) and i < len(word2):
            if word1[i] != word2[i]:
                return self.orderMap[word1[i]] > self.orderMap[word2[i]]
            i += 1
        
        return len(word1) > len(word2)