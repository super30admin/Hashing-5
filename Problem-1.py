"""
TC: O(v+e) where v-> characters, e-> max neighbor count for a char
SC: O(1) since there can be only 26 chars
"""
class Solution:
    def alienOrder(self, words: List[str]) -> str:
        self.result = []
        # create adjacency list
        adj = {}
        for word in words:
            for char in word:
                adj[char] = set()
        

        # fill adj list with neighbors
        for i in range(1, len(words)):
            first = words[i-1]
            second = words[i]
            prefix_match_len = min(len(first), len(second))
            if first[:prefix_match_len] == second[:prefix_match_len] and len(first) > len(second):
                return ""
            for j in range(prefix_match_len):
                if first[j] != second[j]:
                    adj[first[j]].add(second[j])
                    break
        
        # visted map 
        visited = {} # True = visited and in current path, False = visited but not in current path

        # traverse
        for char in adj:
            if self.check_cycle(char, visited, adj):
                return ""
        self.result.reverse()
        return "".join(self.result)
    
    def check_cycle(self, char, visited, adj):
        """
        a POST order DFS approach to check if there is a cycle
        """
        # base
        if char in visited:
            return visited[char]
        
        #logic
        visited[char] = True
        for next_char in adj[char]:
            if self.check_cycle(next_char, visited, adj): # if next char i.e the char that we will visit now is already in the current path
                return True # there is a cycle
        
        visited[char] = False # marking that the char is visited and not in current path
        self.result.append(char) # if all dependencies are taken care of, add to result