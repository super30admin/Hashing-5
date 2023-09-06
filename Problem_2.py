"""
Problem : 2

Time Complexity : O(nk)
Space Complexity : O(1)

Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

"""

# Verify Alien Dictionary

class Solution(object):
    def isAlienSorted(self, words, order):
        """
        :type words: List[str]
        :type order: str
        :rtype: bool
        """
        hmap={}

        for i in range(len(order)):
            hmap[order[i]]=i
        
        for i in range(len(words)-1):
            first=words[i]
            second=words[i+1]
            if self.notSorted(first,second,hmap):
                return False
        return True

    def notSorted(self,first,second,hmap):
        fl=len(first)
        sl=len(second)

        for i in range(min(sl,fl)):
            fChar=first[i]
            sChar=second[i]
            if fChar!=sChar:
                return hmap[fChar]>hmap[sChar]
        
        return fl>sl