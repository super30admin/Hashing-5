#Time O(n*k), space O(1)
class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        h={}
        
        for i in range(len(order)):
            if order[i] not in h:
                h[order[i]]=i
        print(h)
        
        for j in range(len(words)-1):
            f=words[j]
            s=words[j+1]
            flag=False
            k=0
            while k<len(f) and k<len(s):
                c1=f[k]
                c2=s[k]
                if c1!=c2:
                    if h[c1]>h[c2]:
                        return False
                    else:
                        flag=True
                        break
                k+=1
                
            if not flag and len(f)>len(s):
                return False
            
        return True
