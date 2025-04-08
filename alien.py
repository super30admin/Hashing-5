# alienOrder:
# - Build graph from adjacent word pairs by comparing characters.
# - Use Kahn’s algorithm (BFS Topo Sort) to get order.
# - Detect cycle by comparing result length to unique letters.

# TC: O(N + A), N = total chars, A = unique letter edges
# SC: O(N + A)


class Solution:
    def alienOrder(self, words: List[str]) -> str:
        #building graph
        graph = defaultdict(list)
        for word1,word2 in zip(words,words[1:]):
            for i in range(min(len(word1),len(word2))):
                if word1[i] != word2[i]:
                    graph[word1[i]].append(word2[i])
                    break
            else:
                if len(word1) > len(word2):
                    return ""

        #building indegree map
        string = set(''.join(words))
        indegree = defaultdict(int)
        for node in string:
            for v in graph[node]:
                indegree[v] += 1

        #kahn's algo
        res = ""
        q = deque()
        for i in graph:
            if indegree[i] == 0:
                q.append(i)
        while q:
            node = q.popleft()
            res += node
            for nei_node in graph[node]:
                indegree[nei_node] -= 1
                if indegree[nei_node] == 0:
                    q.append(nei_node)
        if len(res) != len(string):
            return ""
        return res
        