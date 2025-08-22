"""
Time Complexity: O(N*L) to build graph and O(V + E ) to traverse the graph.
Space Complexity: O(V+E)
"""
class Solution:
    def alienOrder(self, words):
        self.map = defaultdict(set)
        self.indegrees = [0] * 26

        self.buildGraph(words)
        if len(self.map) == 0:
            return ""

        sb = []
        q = deque()

        for c in self.map.keys():
            if self.indegrees[ord(c) - ord('a')] == 0:
                q.append(c)
                sb.append(c)

        while q:
            c = q.popleft()
            for ne in self.map[c]:
                self.indegrees[ord(ne) - ord('a')] -= 1
                if self.indegrees[ord(ne) - ord('a')] == 0:
                    q.append(ne)
                    sb.append(ne)

        if len(sb) == len(self.map):
            return ''.join(sb)
        return ""

    def buildGraph(self, words):
        for word in words:
            for c in word:
                self.map[c] = set()

        for i in range(len(words) - 1):
            first = words[i]
            second = words[i + 1]

            if first.startswith(second) and len(first) > len(second):
                self.map.clear()
                return

            for j in range(min(len(first), len(second))):
                fChar = first[j]
                sChar = second[j]

                if fChar != sChar:
                    if sChar not in self.map[fChar]:
                        self.map[fChar].add(sChar)
                        self.indegrees[ord(sChar) - ord('a')] += 1
                    break