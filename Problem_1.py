class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        if words == None or len(words) == 0:
            return True
        self.hashmap = {}
        for i in range(len(order)):
            self.hashmap[order[i]] = i + 1
            
        for i in range(len(words)-1):
            first = words[i]
            second = words[i+1]
            if self.isNotSorted(first, second):
                return False
        return True
    
    def isNotSorted(self, first, second):
        m, n = len(first), len(second)
        for i in range(min(m, n)):
            if first[i] != second[i]:
                if self.hashmap[first[i]] > self.hashmap[second[i]]:
                    return True
                else:
                    return False
        if m > n:
            return True
        else:
            return False

# Time Complexity: O(n*l) + O(o)
# Space Complexity: O(1)
         
        
                
        