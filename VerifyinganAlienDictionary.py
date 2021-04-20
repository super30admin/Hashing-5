# Time Complexity : O(NK)
# Space Complexity : O(1)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach
# Using HashMap to store the order with value as their respective index.
# Then iterate over the words list and compare each character with another character from the next word
# We will check if the first character of words1 != first character of word2
# Then check if the first character of word1 > first character of word2 return False else break
# Also if the length of word1 > length of word2 the return False
# Return True if it is in sorted order


class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        if not words:
            return True
        dict = {}
        for i in range(len(order)):
            dict[order[i]] = i

        for i in range(len(words) - 1):
            for j in range(len(words[i])):
                # If we do not find a mismatch letter between words[i] and words[i + 1],
                # we need to examine the case when words are like ("apple", "app").
                if j >= len(words[i + 1]):
                    return False
                if words[i][j] != words[i + 1][j]:
                    if dict[words[i][j]] > dict[words[i + 1][j]]:
                        return False
                    # if we find the first different character and they are sorted,
                    # then there's no need to check remaining letters
                    else:
                        break

        return True
