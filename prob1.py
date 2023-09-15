# Time Complexity : O(mn)
# Space Complexity :O(mn)
# Passed on Leetcode: yes

from collections import defaultdict, deque

class Solution:
    def alienOrder(self, words: List[str]) -> str:
        # Create a graph and indegree dictionary
        graph = defaultdict(list)
        indegree = {char: 0 for word in words for char in word}
        
        # Build the graph and indegree dictionary
        for i in range(len(words) - 1):
            word1, word2 = words[i], words[i + 1]
            min_len = min(len(word1), len(word2))
            
            for j in range(min_len):
                if word1[j] != word2[j]:
                    graph[word1[j]].append(word2[j])
                    indegree[word2[j]] += 1
                    break
        
        # Initialize a queue for topological sorting
        queue = deque([char for char in indegree if indegree[char] == 0])
        result = []
        
        # Perform topological sorting
        while queue:
            char = queue.popleft()
            result.append(char)
            
            for neighbor in graph[char]:
                indegree[neighbor] -= 1
                if indegree[neighbor] == 0:
                    queue.append(neighbor)
        
        # Check for cycles (invalid order)
        if len(result) < len(indegree):
            return ""
        
        return ''.join(result)
