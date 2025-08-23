from typing import collections, List
# class Solution:
#     def isAlienSorted(self, words: List[str], order: str) -> bool:
#         hashmap = collections.defaultdict(int)
#         for i,c in enumerate(order):
#             hashmap[c] = i
#         for i in range(len(words)-1):
#             first = words[i]
#             second = words[i+1]
#             for j in range(min(len(first), len(second))):
#                 fchar = first[j]
#                 schar = second[j]
#                 if fchar!=schar:
#                     if hashmap[fchar]>hashmap[schar]:
#                         return False
#                     break
#             else:  
#                 if len(first) >len(second): return False
#         return True
class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        self.map = {}
        for i in range(len(order)):
            c = order[i]
            self.map[c] = i

        for i in range(len(words)-1):
            first = words[i]
            second = words[i+1]
            if self.notsorted(first,second): return False
        return True

    def notsorted(self,first,second):
        for i in range(min(len(first), len(second))):
            fchar = first[i]
            schar = second[i]
            if fchar!=schar:
                if self.map[fchar]>self.map[schar]:
                    return True
                else:
                    return False
        if len(first)>len(second):
            return True
        else:
            return False
        
        