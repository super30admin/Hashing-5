# Problem 2 : Verifying an Alien Dictionary
# Time Complexity : O(n * k) where n is the number of words in words list and k is the average length of the word
# Space Complexity : O(1)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this :
'''
None
'''

# Your code here along with comments explaining your approach
from typing import List
from collections import defaultdict
class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        # define hashMap which will character as key and index as value
        hashMap = defaultdict(str)
        # get the length of the order string and words list
        lengthOrder = len(order)
        lengthWord = len(words)
        # loop from 0 to length of the order string
        for i in range(lengthOrder):
            # store the index as value at ith character in the hash map
            hashMap[order[i]] = i
        # loop from 1 to length of words list
        for i in range(1, lengthWord):
            # get the (i-1)th word in the words list
            firstW = words[i-1]
            # get the ith word in the words list
            secondW = words[i]
            # check if the the first and second word are not in order
            if self.notOrder(firstW, secondW, hashMap):
                # if the condition is true then return False
                return False
        # return True
        return True
    # function to check if the first word and second word is not in order
    def notOrder(self, firstW, secondW, hashMap) -> bool:
        # define variable i and set to 0
        i = 0
        # loop till i is less than length of the first word and length of the second word
        while i < len(firstW) and i < len(secondW):
            # get the ith character of first word and second word
            fChar = firstW[i]
            sChar = secondW[i]
            # check if the first character is not equal to second character
            if fChar != sChar:
                # return the result of the condition- if the value of hashmap of first character is greater than the value of hashmap of second character
                return hashMap[fChar] > hashMap[sChar]
            # increment the value of i
            i += 1
        # return the result of the condition - if the length of first word is greater than the length of the second word
        return len(firstW) > len(secondW)
        