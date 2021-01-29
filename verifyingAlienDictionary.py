#Time Complexity : O(nk) where n is the number of words and k is the average length of each word
#Space Complexity : O(1) 
#Did this code successfully run on Leetcode : Yes

class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        alphabet = {char:i for i, char in enumerate(order)}

        for i in range(1, len(words)):
            w1, w2 = words[i-1], words[i]
            if len(w1) > len(w2) and w1[:len(w2)] == w2:
                return False
            for j in range(min(len(w1), len(w2))):
                if w2[j] != w1[j]:
                    if alphabet[w2[j]] <  alphabet[w1[j]]:
                        return False
                    break

        return True
