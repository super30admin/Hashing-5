# // Time Complexity :O(V*E + n*l)
# // Space Complexity :O(n*l)
# // Did this code successfully run on Leetcode : Yes
# // Any problem you faced while coding this : No

from collections import defaultdict, deque
class Solution:
    def alienOrder(self, words: List[str]) -> str:
        uniq = set("".join(words))
        self.hmap = defaultdict(set)
        self.indegrees = {} #can't use [0]* 26// unique words 
        for w in words:
            for c in w:
                self.indegrees[c] = 0       # incoming edges

        self.buildGraph(words)                          # build graph
        
        sb = ""       
        q = deque()                                     # build queue of words
        for key in self.hmap.keys():
            if self.indegrees[key] == 0:
                q.append(key)
                sb += key

        while q:
            curr = q.popleft()

            for c in self.hmap[curr]:
                self.indegrees[c] -= 1
                if self.indegrees[c] == 0:
                    q.append(c)
                    sb += c
        
        if len(sb) != len(self.hmap) :          
            return ""
        return sb

            
    def buildGraph(self, words):
        for w in words:
            for c in w:
                self.hmap[c] = set()

        for i in range(len(words)-1):
            first = words[i]
            second = words[i+1]

            if first.startswith(second) and len(first) > len(second):   # app, apple
                self.hmap = {}
                return

            for j in range(min(len(first),len(second))):
                f = first[j]
                s = second[j]

                if f != s:
                    tempset = self.hmap[f]
                    if s not in tempset:
                        tempset.add(s)
                        self.indegrees[s] += 1
                    break 
