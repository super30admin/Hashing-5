from collections import deque

class Solution:
    def alienOrder(self, words: List[str]) -> str:
        #Approach: Graph // BFS
        #Time Complexity: O(n * l)
        #Space Complexity: O(1)
        #where, n is the number of words, and l is the length of an average word
        
        edgeMap, inDegree = self.buildGraph(words)
        
        de = deque()
        for char in inDegree:
            if inDegree[char] == 0:
                de.append(char)
                
        sb = []
        while de:
            char = de.popleft()
            sb.append(char)
            
            for edgeChar in edgeMap[char]:
                inDegree[edgeChar] -= 1
                if inDegree[edgeChar] == 0:
                    de.append(edgeChar)
                    
        if len(sb) != len(edgeMap):
            return ""
        return "".join(sb)
    
    def buildGraph(self, words):
        edgeMap, inDegree = {}, {}
        
        for word in words:
            for char in word:
                edgeMap[char] = set()
                inDegree[char] = 0
                
        for i in range(len(words) - 1):
            word1 = words[i]
            word2 = words[i + 1]
            
            j = 0
            while j < len(word1) and j < len(word2):
                if word1[j] != word2[j]:
                    if word2[j] not in edgeMap[word1[j]]:
                        edgeMap[word1[j]].add(word2[j])
                        inDegree[word2[j]] += 1
                    break
                j += 1
                
            if j == len(word2) and j < len(word1):
                return {}, {}
        
        return edgeMap, inDegree