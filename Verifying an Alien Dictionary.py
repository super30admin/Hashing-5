class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        #Approach: Compare adjacent numbers
        #Time Complexity: O(n * l)
        #Space Complexity: O(1)
        #where, n is the number of words, and l is the length of an average word
        
        orderMap = {}
        for i in range(len(order)):
            orderMap[order[i]] = i
            
        for i in range(len(words) - 1):
            word1 = words[i]
            word2 = words[i + 1]
            
            j = 0
            while j < min(len(word1), len(word2)):
                if orderMap[word1[j]] < orderMap[word2[j]]:
                    break
                if orderMap[word1[j]] > orderMap[word2[j]]:
                    return False
                j += 1
                
            if len(word2) == j and len(word1) > j:
                return False
                
        return True