# Approach - Build a hashmap with all characters and the indices at which they occur
# Next, iterate over adjacent words and check that they are sorted, at any point if the pointers of characters dont match
    # check if the first character's order is lesser than that of second word's character
    # if not move pointers to next characters if equal

# If not return False
# If they are valid but at one point one word is shorter and pointer ends traversing the word
    # return False if the first word's length > second word as it is not sorted
    # else continue to check other words

# Time - O(N * L) where N is the number of words and L is its length
# Space - O(1) constant hashmap with 26 characters



class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        
        self.hashmap = {}
        for i, char in enumerate(order):
            self.hashmap[char] = i
        
        for i in range(len(words) - 1):
            word1 = words[i]
            word2 = words[i+1]
            
            if self.isNotSorted(word1, word2): # if it returns true then words were not sorted
                return False
            
        return True
    
    
    def isNotSorted(self, word1, word2):
        
        p1 = 0
        p2 = 0
        while p1 < len(word1) and p2 < len(word2):
                char1 = word1[p1]
                char2 = word2[p2]
                
                if char1 != char2:
                    return self.hashmap[char1] > self.hashmap[char2]
                
                p1 += 1
                p2 += 1
      

        return len(word1) > len(word2)
            
       
            
            
            
        
            

