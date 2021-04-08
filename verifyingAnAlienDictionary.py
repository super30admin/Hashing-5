# Time Complexity : O(NK)
# Space Complexity : O(1)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach

class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        if not words:
            return True
        
        hm = {}
        #put order in hm and store indices
        for i in range(len(order)):
            c = order[i]
            hm[c] = i
        #go over each pair in words list
        for i in range(len(words)-1):
            word1 = words[i]
            word2 = words[i+1]
            #check if sorted. if not return False
            if self.isNotSorted(word1,word2, hm):
                return False
        return True
    
    def isNotSorted(self, word1, word2, hm):
        #can use while loop oopsies
        mx = max(len(word1),len(word2))
        mn = min(len(word1), len(word2))
        for i in range(mx):
            if i >= mn:
                break
            #get two characters and check ONLYIF two char arent the same
            fc = word1[i]
            sc = word2[i]
            
            if fc != sc:
                return hm[fc] > hm[sc]
        return len(word1) > len(word2) #return true cuz its not sorted
        