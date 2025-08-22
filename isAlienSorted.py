"""
Time Complexity: O(n)
Space Compelxity:O(1) constant 26 chars in dict
Appraoch:
you can use 2 pointers with 3rd pointer for order
optimal would be have priority of all in chars on order in dict and use that to compare two adjacent words
"""





class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        hmap=dict()
        for i,ch in enumerate(order):
            hmap[ch]=i
        for i in range(1,len(words)):
            w2=words[i]
            w1=words[i-1]
            l1,l2=0,0
            check=True
            while(l1< len(w1) and l2<len(w2)):
                if hmap[w1[l1]]<hmap[w2[l2]]:
                    break
                if hmap[w1[l1]]==hmap[w2[l2]]:
                    l1+=1
                    l2+=1
                else:
                    check=False
                    break
            # print(l1,len(w1),l2,len(w2))
            if (l1!=len(w1) and l2==len(w2)) :return False
            if not check: return False
        return True
