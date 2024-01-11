# Time: O(nk)
# Space: O(1)
# Did it run on Leetcode: yes

class Solution(object):
    def isAlienSorted(self, words, order):
        """
        :type words: List[str]
        :type order: str
        :rtype: bool
        """
        # O(nk), O(1)
        hmap={}
        def notinorder(first,second,hmap):
            for i, (elem_first, elem_second) in enumerate(zip(first, second)):
                
                if(elem_first!=elem_second):
                    return hmap[elem_first]>hmap[elem_second]
            return len(first)>len(second)
        for i in range(len(order)):
            c=order[i]
            hmap[c]=i
        for i in range(len(words)-1):
            first=words[i]
            second=words[i+1]
            if(notinorder(first,second,hmap)):
                return False
        return True