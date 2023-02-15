from typing import (
    List,
)
from collections import deque


class Solution:
    """
    @param words: a list of words
    @return: a string which is correct order
    """
    hashMap = dict()
    indegrees = list()

    def alien_order(self, words: List[str]) -> str:
        # Write your code here
        if words is None:
            return ""
        self.hashMap = dict()
        self.indegrees = [0] * 26
        result = list()
        self.buildGraph(words)
        q = deque()

        for key in self.hashMap.keys():
            if self.indegrees[ord(key) - ord("a")] == 0:
                q.append(key)
                result.append(key)

        while q:
            char = q.popleft()
            # we check all the dependent nodes on the current node
            # and reduce their dependency
            children = self.hashMap.get(char)
            if children:
                for child in children:
                    self.indegrees[ord(child) - ord("a")] -= 1

                    if self.indegrees[ord(child) - ord("a")] == 0:
                        q.append(child)
                        result.append(child)
                        print(result)
        # print(result, self.hashMap)
        if len(result) == len(self.hashMap):
            return "".join(result)

        return ""

    def buildGraph(self, words):
        for word in words:
            for i in range(len(word)):
                c = word[i]
                self.hashMap[c] = set()

        for i in range(len(words) - 1):
            outWord = words[i]
            # in_word will have indegree, it's the word which will have dependency
            inWord = words[i + 1]
            # if the inWord is the substring of the first word
            m = len(outWord)
            n = len(inWord)
            if m != n and outWord.startswith(inWord):
                self.hashMap.clear()
                break
            # iterate over the words

            j = 0
            while j < m and j < n:
                outChar = outWord[j]
                inChar = inWord[j]
                if outChar != inChar:
                    # print(self.indegrees)
                    self.indegrees[ord(inChar) - ord("a")] += 1
                    self.hashMap.get(outChar).add(inChar)
                    break
                j += 1


# Graph
# Time Complexity: O(nk)
# Space Complexity: O(n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

