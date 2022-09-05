""""// Time Complexity : O(n.k); where n is number of words and k is avg length of a word.
// Space Complexity : O(1) as map will at most have 26 chars
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :
"""

class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        if len(words) == 1:
            return True
        d = {}

        for i in range(len(order)):
            c = order[i]
            if c not in d:
                d[c] = i

        for i in range(len(words) - 1):
            p1 = 0
            p2 = 0
            while p1 < len(words[i]) and p2 < len(words[i + 1]):
                if d[words[i][p1]] < d[words[i + 1][p2]]:
                    break
                elif d[words[i][p1]] == d[words[i + 1][p2]]:
                    p1 += 1
                    p2 += 1
                else:
                    return False
            if p1 < len(words[i]) and p2 == len(words[i + 1]):
                return False
        return True
