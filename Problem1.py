from collections import defaultdict, deque
from typing import List


class Solution:
    def alienOrder(self, words: List[str]) -> str:
        '''
        Time Complexity: O(C), Building the graph and calculating indegrees, also Topological sort (BFS): O(C), where C is the total number of characters.
        Space Complexity: O(C), Space used by graph and indegrees: O(C) also for result and queue: O(C)
        '''

        # Step 1: Initialize a directed graph as a defaultdict of sets.
        # Each character is a node, and edges represent lexicographical order.
        graph = defaultdict(set)

        # Create a dictionary to store the indegree of each character.
        indegrees = {char: 0 for word in words for char in word}

        # Step 2: Build the graph and calculate indegrees.
        for i in range(1, len(words)):
            word1, word2 = words[i - 1], words[i]

            # Compare characters in word1 and word2 to find ordering relationships.
            found_order = False
            for j in range(min(len(word1), len(word2))):
                if word1[j] != word2[j]:
                    if word2[j] not in graph[word1[j]]:
                        graph[word1[j]].add(word2[j])
                        indegrees[word2[j]] += 1
                    found_order = True
                    break

            # Handle the case where one word is a prefix of the other.
            if not found_order and len(word1) > len(word2):
                return ""

        # Step 3: Perform topological sort using BFS.
        result = []
        queue = deque([char for char in indegrees if indegrees[char] == 0])

        while queue:
            char = queue.popleft()
            result.append(char)
            for neighbor in graph[char]:
                indegrees[neighbor] -= 1
                if indegrees[neighbor] == 0:
                    queue.append(neighbor)

        # Step 4: Check if the result is a valid lexicographical order.
        if len(result) != len(indegrees):
            return ""

        # Step 5: Return the characters in topological order as the result.
        return "".join(result)
