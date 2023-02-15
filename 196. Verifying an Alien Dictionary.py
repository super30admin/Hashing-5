class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        if len(words) == 0:
            return False

        hashMap = dict()

        for index in range(len(order)):
            char = order[index]
            hashMap[char] = index

        for i in range(len(words) - 1):
            first = words[i]
            second = words[i + 1]

            if self.notSorted(first, second, hashMap):
                return False
        return True

    def notSorted(self, first, second, hashMap):
        fl = len(first)
        sl = len(second)
        i = 0
        while i < fl and i < sl:
            if first[i] != second[i]:
                return hashMap[first[i]] > hashMap[second[i]]
            i += 1
        return fl > sl

# Hashing
# Time Complexity: O(n*l)
# Space Complexity: O(1). Size of dictionary will be constant as only 26 letters
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
