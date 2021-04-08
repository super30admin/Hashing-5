# Time Complexity : O(NK)
# Space Complexity : O(1)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach

class Solution:
    def alienOrder(self, words: List[str]) -> str:
        self.hm = {}
        self.indegree = [0 for i in range(26)] #before buildGraph 
        self.hm = self.buildGraph(words)
        queue = deque([])
        #if res < len(hm) return false
        res = []
        for c in self.hm.keys():
            if self.indegree[ord('a') - ord(c)] == 0:
                queue.append(c)
        
        while queue:
            c = queue.popleft()
            res.append(c)
            for edge in self.hm[c]:
                self.indegree[ord('a') - ord(edge)] -= 1
                if self.indegree[ord('a')-ord(edge)] == 0:
                    queue.append(edge)
        if len(res) != len(self.hm):
            return ""
        
        return ''.join(res)
    
    def buildGraph(self, words):
        #get unique letters
        for word in words:
            for i in range(len(word)):
                c = word[i]
                if c not in self.hm:
                    self.hm[c] = set()
                    
        for i in range(len(words)-1):
            word1 = words[i]
            word2 = words[i+1]
            
            #edge
            if word1.startswith(word2) and len(word1) > len(word2):
                return {} #clear the map
            
            j = 0
            while j < len(word1) and j < len(word2):
                if word1[j] != word2[j]:
                    if word2[j] not in self.hm[word1[j]]:
                        self.hm[word1[j]].add(word2[j])
                        self.indegree[ord('a') - ord(word2[j])] += 1
                    break
                j += 1
                
        return self.hm
                
        