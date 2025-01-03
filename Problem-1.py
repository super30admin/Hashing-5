#Approach
# create a hashmap with key as character and value as indices in the order
# for every 2 words find wheather it follows the allien dictionary by finding the non first equal characters indices


#Complexity
#Time: O(n*L)
#Space: O(n)


from typing import List


class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        hashMap = dict()

        for i in range(len(order)):
            hashMap[order[i]] = i

        for i in range(len(words) - 1):
            if not self.issorted(words[i], words[i + 1], hashMap):
                return False
        return True

    def issorted(self, firstWord, secondWord, hashMap):

        for i in range(min(len(firstWord), len(secondWord))):
            if hashMap[firstWord[i]] < hashMap[secondWord[i]]:
                return True
            elif hashMap[firstWord[i]] > hashMap[secondWord[i]]:
                return False

        return len(firstWord) <= len(secondWord)
