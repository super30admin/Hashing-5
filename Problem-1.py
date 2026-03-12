# Time Complexity : O(C)  # C = total characters across all words
# Space Complexity : O(26 + E)  # Graph edges and nodes
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Explanation:
# Build a graph of character ordering by comparing adjacent words.
# For the first differing character between two words,
# create a directed edge.
# Then perform topological sort using BFS (Kahn’s algorithm).

from collections import defaultdict, deque

class Solution:
    def alienOrder(self, words: List[str]) -> str:
        
        graph = defaultdict(set)
        indegree = {c:0 for word in words for c in word}
        
        for i in range(len(words)-1):
            w1, w2 = words[i], words[i+1]
            
            if len(w1) > len(w2) and w1.startswith(w2):
                return ""
            
            for c1, c2 in zip(w1, w2):
                if c1 != c2:
                    if c2 not in graph[c1]:
                        graph[c1].add(c2)
                        indegree[c2] += 1
                    break
        
        queue = deque([c for c in indegree if indegree[c]==0])
        res = []
        
        while queue:
            c = queue.popleft()
            res.append(c)
            
            for nei in graph[c]:
                indegree[nei] -= 1
                if indegree[nei] == 0:
                    queue.append(nei)
        
        return "".join(res) if len(res)==len(indegree) else ""