# TIME COMPLEXITY: O(V+E) -> V is total number of characters or vertices and E is number of rules
# SPACE COMPLEXITY: O(V+E)
class Solution(object):
    def alienOrder(self, words):
        """
        :type words: List[str]
        :rtype: str
        """
        # Edge Case
        if not words:
            return ""

        # Initialize maps to hold indegree and adjacency list
        indegree = collections.defaultdict(int)
        graph = collections.defaultdict(list)

        for word in words:
            for char in word:
                graph[char] = []
                indegree[char] = 0

        # Build the graph and adjacency list
        for i in range(len(words) - 1):
            w1, w2 = words[i], words[i+1]
            for j in range(min(len(w1), len(w2))):
                parent, child = w1[j], w2[j]
                if parent != child:
                    graph[parent].append(child)
                    indegree[child] += 1
                    break
            else:
                if len(w2) < len(w1):
                    return ""

        # Put all the sources in a queue - nodes with indegree = 0
        sources = collections.deque()
        for k in indegree:
            if indegree[k] == 0:
                sources.append(k)

        # Build a topological sort order of the graph
        sortedOrder = []
        while sources:
            v = sources.popleft()
            sortedOrder.append(v)
            for child in graph[v]:
                indegree[child] -= 1
                if indegree[child] == 0:
                    sources.append(child)

        # THe sorted ordering represents the dictionary if the sorted ordering == number of characters
        if len(sortedOrder) != len(indegree):
            return ""
        return ''.join(sortedOrder)
