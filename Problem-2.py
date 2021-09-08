"""
TC: O(n)
SC: O(1)
"""
class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        self.orders = {} # to easily access the order
        for i in range(len(order)):
            self.orders[order[i]] = i
            
        for i in range(1, len(words)):
            if not self.check_order(words[i-1], words[i]): # if not in order
                return False
        return True
    
    def check_order(self, first, second):
        m = len(first)
        n = len(second)
        for i in range(min(m,n)):
            if first[i] != second[i]: # check only the first char that differs
                return self.orders[first[i]] < self.orders[second[i]]
        return m<=n # example: app, apple