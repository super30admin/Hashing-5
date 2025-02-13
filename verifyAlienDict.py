# // Time Complexity :O(n*l)
# // Space Complexity :O(1)
# // Did this code successfully run on Leetcode : Yes
# // Any problem you faced while coding this : No

class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        
        hmap = {}

        for i in range(len(order)):         # put letters in a hashmap
            hmap[order[i]] = i

        def notSorted(first, second):                       # func for all elements not sorted or size issue
            
            for i in range(min(len(first), len(second))):
                if first[i]  != second[i]:
                    return hmap[first[i]] > hmap[second[i]]
            
            return len(first) > len(second)

        for i in range(len(words)-1):                   # process 2 words at a time
            first = words[i]
            second = words[i+1]

            if notSorted(first,second): return False        # at any point func fails, return false

        return True

        


        
