# Time complexity : O(n*l) --> n = number of words, l = average length of words
# Space complexity : O(1) 
# Leetcode : Solved and submitted

class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
      
        # create hashmap for the order of letters so that we have a constant lookup time
        hashmap = {}
        for i in range(len(order)):
            hashmap[order[i]] = i
        
        # traverse over all the words in the words list
        for i in range(len(words)-1):
            first = words[i]
            second = words[i+1]
            
            # check if the two words are sorted or not
            if self.notSorted(first, second, hashmap):
                # return false if they are not
                return False
        
        # return True if the entire word list is sorted
        return True
    
    def notSorted(self, first, second, hashmap):
        # get the lenght of both the words
        fl = len(first)
        sl = len(second)
        i = 0
        
        # traverse until either of the words is done traversing
        while i < fl and i < sl:
            # if the character is not same for both the words, then check for the occurence in the order hashmap
            # if the value of first word letter is greater than value of second word letter then return True
            if first[i] != second[i]:
                return hashmap[first[i]] > hashmap[second[i]]
            i += 1
        
        # if we have come out of the while loop which means either one of the word's length is over the letter are still common, then we check for length
        # of the word
        # first word should be smaller in len as compared to the second, if they are to be in the lexographically order
        return fl > sl
