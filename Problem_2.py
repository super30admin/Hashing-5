class Solution:
    def alienOrder(self, words: List[str]) -> str:
        if words == None or len(words) == 0:
            return ''
        self.hashmap = {}
        self.indegrees = [0] * 26
        self.buildGraph(words)
        
        q = deque()
        result = ''
        for char in self.hashmap:
            if self.indegrees[ord(char)-ord('a')] == 0:
                q.append(char)
                result += char
        if len(q) == 0:
            return ""
        while len(q):
            c = q.popleft()
            hashset = self.hashmap[c]
            for cSet in hashset:
                self.indegrees[ord(cSet)-ord('a')] -= 1
                if self.indegrees[ord(cSet)-ord('a')] == 0:
                    q.append(cSet)
                    result += cSet
                    
        if len(self.hashmap) != len(result):
            return ""
        return result
    
    def buildGraph(self, words):
        for word in words:
            for w in word:
                if w not in self.hashmap:
                    self.hashmap[w] = set()
        for i in range(len(words)-1):
            out, ind = words[i], words[i+1]
            m, n = len(out), len(ind)
            
             # ["abc", "ab"]
            if out.startswith(ind) and len(out) > len(ind):
                self.hashmap.clear()
                return
            
            for j in range(min(m, n)):
                outChar, inChar = out[j], ind[j]
                if outChar != inChar:
                    hashset = self.hashmap[outChar]
                    if hashset == None:
                        continue
                    if inChar not in hashset:
                        self.hashmap[outChar].add(inChar)
                        self.indegrees[ord(inChar)-ord('a')] += 1
                    break
            
        
# Time Complexity: O(V+E)      
# Space Complexity: O(V+E)       
            
        
        