class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        #O(NK) time and O(1) space solution.
        #N-number of words, K - average length of the word. O(1) space as hashmap has 26 characters.
        if len(words) == 0 or words == None:
            return True
        
        self.hashmap = {}
        for i in range(len(order)):
            self.hashmap[order[i]] = i
        
        for i in range(len(words) - 1):
            first = words[i]
            second = words[i + 1]
            if self.isNotSorted(first, second):
                return False
        return True
    
    def isNotSorted(self, first, second):
        a = len(first)
        b = len(second)
        for i in range(min(a, b)):
            if first[i] != second[i]:
                if self.hashmap[first[i]] > self.hashmap[second[i]]:
                    return True #because function is named isNotSorted
                else:
                    return False
        if a > b:
            return True
        else:
            return False