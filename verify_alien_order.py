# Approach - Topological sort similar to course schedule
# Space - O(1)
# Time - O(N * L) takes precedence over O(V + E)
class Solution:
    def alienOrder(self, words: List[str]) -> str:
        
        edges_map, indegrees = self.buildGraphs(words)
        
        queue = deque()
        
        result = []
        
        for char in indegrees:
            if indegrees[char] == 0:
                queue.append(char)
                
        # O(V + E)       
        while queue:
            
            current = queue.popleft()
            result.append(current)
            
            edges = edges_map[current]
            for edge in edges:
                indegrees[edge] -= 1
                
                if indegrees[edge] == 0:
                    queue.append(edge)
                    
        if len(result) == len(edges_map):
            return "".join(result)
        
        else:
            return ""
        
        

    def buildGraphs(self, words):
        
        edges = {}
        indegrees = {}
        
        for word in words:
            for char in word:
                edges[char] = set() # in order to remove duplicates getting added
                indegrees[char] = 0
                
                
        for i in range(len(words) - 1):
            word1 = words[i]
            word2 = words[i+1]
            
            j = 0
            
            while j < len(word1) and j < len(word2):
                char1 = word1[j]
                char2 = word2[j]
                
                if char1 != char2:
                    # prev we checked for order, but here we know they are sorted
                    # just add char 2 as an edge to char1 as it appears after
                    if char2 not in edges[char1]:
                        edges[char1].add(char2)
                        indegrees[char2] += 1
                        
                    break
                j += 1
                
            if j == len(word2) and j < len(word1):
                return {}, {}
                    
        return edges, indegrees
        