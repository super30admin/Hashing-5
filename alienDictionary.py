class Solution:
    def alienOrder(self, words: List[str]) -> str:
        # T: O(n + c), S: O(c)
        # Step 1: Initialize data structures
        graph = defaultdict(set)
        in_degree = {char: 0 for word in words for char in word}

        # Step 2: Build the graph
        for i in range(len(words) - 1):
            first, second = words[i], words[i + 1]
            min_len = min(len(first), len(second))
            if first[:min_len] == second[:min_len] and len(first) > len(second):
                return ""
            for c1, c2 in zip(first, second):
                if c1 != c2:
                    if c2 not in graph[c1]:
                        graph[c1].add(c2)
                        in_degree[c2] += 1
                    break

        # Step 3: Topological Sort
        queue = deque([char for char in in_degree if in_degree[char] == 0])
        result = []

        while queue:
            char = queue.popleft()
            result.append(char)
            for neighbor in graph[char]:
                in_degree[neighbor] -= 1
                if in_degree[neighbor] == 0:
                    queue.append(neighbor)

        if len(result) < len(in_degree):
            return ""

        return "".join(result)
