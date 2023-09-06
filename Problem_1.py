"""
Problem : 1

Time Complexity : O(n*l) /n=no. of words, l=average length of each words
Space Complexity : O(n)

Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

"""

# Alien Dictionary

class Solution(object):
    def __init__(self):
        self.hmap={}
        self.indegrees=[0 for _ in range(26)]
    def alienOrder(self, words):
        """
        :type words: List[str]
        :rtype: str
        """

        self.buildGraph(words)
        result=""
        q=collections.deque()
        for ch in self.hmap.keys():
            if self.indegrees[ord(ch)-ord('a')]==0:
                q.append(ch)        
        
        while q:
            curr=q.popleft()
            result+=curr
            sett=self.hmap[curr]
            for ch in sett:
                self.indegrees[ord(ch)-ord('a')]-=1
                if self.indegrees[ord(ch)-ord('a')]==0:
                    q.append(ch)
        # print(result)
        if len(result)==len(self.hmap):
            return result
        
        return ""


    
    def buildGraph(self,words):
        for word in words:
            for i in range(len(word)):
                c=word[i]
                if c not in self.hmap:
                    self.hmap[c]=set()
        for k in range(len(words)-1):
            
            out=words[k]
            ol=len(out)
            inn=words[k+1]
            il=len(inn)
            # placeholder
            if il!=ol and out.startswith(inn):
                self.hmap={}
                break


            for i in range(min(ol,il)):
                inChar=inn[i]
                outChar=out[i]
                if inChar!=outChar:
                    if inChar not in self.hmap[outChar]:
                        self.indegrees[ord(inChar)-ord('a')]+=1
                        self.hmap[outChar].add(inChar)
                    break