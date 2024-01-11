# Time: O(1)
# Space: O(1)
# Did it run on Leetcode: yes

# O(V+E) = O(1), O(1)
class Solution:
    """
    @param words: a list of words
    @return: a string which is correct order
    """
    def alien_order(self, words: List[str]) -> str:
        def buildgraph(words):
            for word in words:
                for i in range(len(word)):
                    c=word[i]
                    if c not in hmap:
                        hmap[c]=[]
            for i in range(len(words)-1):
                out=words[i]
                inc=words[i+1]
                if(len(out)>len(inc) and (inc in outc)):
                    hmap.clear()
                    return
                # 2 pointer to get the graph or order
                for j, (elem_out, elem_in) in enumerate(zip(out, inc)):
                    if(elem_out!=elem_in):
                        indeg[ord(elem_in)]+=1
                        hmap[elem_out].append(elem_in)
                        break
                    

        hmap={}
        indeg=[0]*26
        buildgraph(words)
        # topological sort
        sb=[]
        count=0
        q=Queue()
        for c in hmap:
            if(indeg[ord(c)]==0):
                sb.append(c)
                q.put(c)
                count+=1
        if(count==len(hmap)):
            return str(sb)
        if(len(q)==0):
            return ""
        
        while not q.empty():
            curr=q.get()
            for baby in hmap[curr]:
                indeg[baby]-=1
                if(indeg[ord(baby)]==0):
                    sb.append(baby)
                    q.put(baby)
                    count+=1
                    if(count==len(hmap)):
                        return str(sb)
        return ""