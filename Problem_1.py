"""
Time Complexity : O(nk) where n is total number of words and k is average length of a word 
Space Complexity : O(1)  
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

Firstly, we make a hashmap which contains the order of all characters.Here, we need to compare 2 words at a time. Whenever we 
find a mismatch in 2 words, those characters are the point where we return. Also, if second word is prefix of the first word,
then also there is a mismatch and we return False.
"""


class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        charMap = {}
        for i in range(26):
            charMap[order[i]] = i
        l = len(words)
        for i in range(l-1):
            word1 = words[i]
            word2 = words[i+1]
            m = len(word1)
            n = len(word2)
            if m > n and word1[:n] == word2:
                return False
            for k in range(min(m, n)):
                if word1[k] != word2[k]:
                    if charMap[word1[k]] > charMap[word2[k]]:
                        return False
                    break

        return True
