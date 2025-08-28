class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        #similar to longest common prefix code logic in terms of comparing strings
        if len(words) == 1:
            return True
        
        index = {}
        for i in range(len(order)):
            index[order[i]] = i
            
        for i in range(1, len(words)):
            j = 0
            while j < len(words[i - 1]) and j < len(words[i]) and words[i - 1][j] == words[i][j]:
                j += 1
            
            if (j < len(words[i - 1]) and j == len(words[i])):
                return False
            
            if j < min(len(words[i - 1]), len(words[i])) and index[words[i - 1][j]] > index[words[i][j]]:
                return False
        return True