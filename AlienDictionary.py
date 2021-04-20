# Time Complexity : O(V + E)
# Space Complexity : O(1)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach
# Using topological sort. Combination of verifying alien dictionary and course schedule

from collections import deque
from collections import defaultdict


class Solution:
    def alienOrder(self, words: List[str]) -> str:
        if not words:
            return None
        res = ""
        dict = defaultdict(set)
        indegrees = [0] * 26
        for word in words:
            for i in word:
                if i not in dict:
                    dict[i] = set()
        for i in range(len(words) - 1):
            if words[i] > words[i + 1]:
                return ""
            for j in range(min(len(words[i]), len(words[i + 1]))):
                ch1 = words[i][j]
                ch2 = words[i + 1][j]
                if ch1 != ch2:
                    if ch2 not in dict[ch1]:
                        indegrees[(ord(ch2) - ord('a'))] += 1
                        dict[ch1].add(ch2)
                    break
        queue = deque()
        for key in dict:
            if indegrees[(ord(key) - ord('a'))] == 0:
                queue.append(key)
        while queue:
            node = queue.popleft()
            res += node
            if node in dict:
                for i in dict[node]:
                    indegrees[(ord(i) - ord('a'))] -= 1
                    if indegrees[(ord(i) - ord('a'))] == 0:
                        queue.append(i)
        if len(res) != len(dict):
            return ""
        return res
