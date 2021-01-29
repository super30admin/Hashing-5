"""
Time Complexity : O(nk) where n is total number of words and k is average length of a word 
Space Complexity : O(1)  
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

Just like the previous question, we need to iterate through 2 words at a time. But here, we make a graph of what char is greater
than what char. Along with that, we make an indegree array to find out independent nodes. Once done, we add those independent
nodes to our queue and we perform BFS on the graph and alongside add the popped out chars to the result.
"""
from collections import defaultdict
from collections import deque


class Solution:

    def alienOrder(self, words: List[str]) -> str:
        self.charMap = defaultdict(set)
        self.countArray = [0]*26
        n = len(words)
        for i in range(n):
            for char in words[i]:
                self.charMap[char] = set()

        for i in range(n-1):
            word1, word2 = words[i], words[i+1]
            m, n = len(word1), len(word2)
            if m > n and word1[:n] == word2:
                return ""

            for k in range(min(m, n)):
                if word1[k] != word2[k]:
                    out, inw = word1[k], word2[k]
                    tempSet = self.charMap[out]
                    if inw not in tempSet:
                        self.charMap[out].add(inw)
                        self.countArray[ord(inw)-ord('a')] += 1
                    break

        q = deque()
        result = []
        for key in self.charMap.keys():
            alph = ord(key)-ord('a')
            if not self.countArray[alph]:
                q.append(key)

        while q:
            curr = q.popleft()
            result.append(curr)
            tempList = list(self.charMap[curr])
            for t in tempList:
                alph = ord(t)-ord('a')
                self.countArray[alph] -= 1
                if not self.countArray[alph]:
                    q.append(t)

        return "".join(result) if len(result) == len(self.charMap) else ""
