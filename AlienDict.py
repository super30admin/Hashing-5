"""
TimeComplexity:O(v+e) ->o(n)
SpaceComplexity:O(1)
Approach:
When you try to pint char you can see a graph structure
you can haev n degreee like course scheduel problem
There are some edge cases like 
[apple ,app]
[app apple]

"""


class Solution:
    def alienOrder(self, words: List[str]) -> str:
        hmap=dict()
        indegree=[0 for _ in range(27)]
        def buildGraph(words):
            #add to in degreee and add to hashmap
            for word in words:
                for ch in word:
                    if ch not in hmap:
                        hmap[ch]=set()
            for i in range(1,len(words)):
                l1,l2=0,0
                w1,w2=words[i-1],words[i]
                if w1.startswith(w2) and len(w1)!=len(w2):
                    hmap.clear()
                    break
                    return False
                while(l1<len(w1) and l2<len(w2)):
                    if w1[l1]!=w2[l2] :
                        if w2[l2] not in hmap[w1[l1]]:
                            indegree[ord(w2[l2])-ord('a')]+=1
                            hmap[w1[l1]].add(w2[l2])
                            # print(w2[l2],w1[l1])
                        break
                    else:
                        l1+=1
                        l2+=1


        q=deque([])
        buildGraph(words) #hmap and indegree will get populated
        # print(hmap)
        # print(indegree)
        for key in hmap.keys():
            if indegree[ord(key)-ord('a')]==0:
                q.append(key)
                
        ans=""
        ansL=0
        while(len(q)):
            
            ch=q.popleft()
            ans+=ch
            ansL+=1
            # print(ans,ch)
            st=hmap[ch]
            for i in st:
                indegree[ord(i)-ord('a')]-=1
                if indegree[ord(i)-ord('a')]==0:
                    q.append(i)
            if ansL==len(hmap.keys()):
                return ans
        
        return ""



