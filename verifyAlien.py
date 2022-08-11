'''
time complexity: O(nk)
k is avg len of words
space complexity: O(1)
'''
class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        self.count = {}
        c = 0
        for o in order:
            self.count[o] = c
            c+=1
        for i in range(len(words)-1):
            fw = words[i]
            sw = words[i+1]
            if(self.isNotSmaller(fw,sw)): return False
        return True
    
    def isNotSmaller(self,fw,sw):
        i = 0
        j = 0
        while(i<len(fw) and j<len(sw)):
            if(fw[i]!=sw[j]):
                if(self.count[fw[i]] > self.count[sw[j]]): return True
                return False
            i+=1
            j+=1
        if(len(fw) > len(sw)): return True
        return False
            