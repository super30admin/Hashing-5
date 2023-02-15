class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        if len(words) == 0:
            return False

        d = dict()

        for index in range(len(order)):
            char = order[index]
            d[char] = index
        # print(d)
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

# Two Pointers
# Time Complexity: O(n*l)
# Space Complexity: O(1). Size of dictionary will be constant as only 26 letters
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
