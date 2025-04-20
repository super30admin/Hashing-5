#time:O(number of words*average size of words)
#space:O(1)
class Solution:
    def alienOrder(self, words: List[str]) -> str:
        hmap= defaultdict(list)
        word_list_size= len(words)
        #iterate all the words to create the adjacency list
        indegree=[0]*26
        for i in range(1,word_list_size):
            word1=words[i-1]
            word2=words[i]
            first_word_size= len(word1)
            second_word_size= len(word2)
            m,n=0,0
            if first_word_size > second_word_size and word1.startswith(word2):
                return ''
            while m < first_word_size and n < second_word_size:
                if word1[m] != word2[n]:
                    hmap[word1[m]].append(word2[n])
                    indegree[ord(word2[n])-ord('a')]+=1
                    break
                m+=1
                n+=1
        #iterate all the words to build the outdegree matrix
        for word in words:
            for c in word:
                if c not in hmap:
                    hmap[c]=[]
        #create queue for single occurences
        q=deque()
        for key in hmap.keys():
            if indegree[ord(key)-ord('a')]==0:
                q.append(key)        
        #iterate the graph and note down visited nodes
        s=''
        while q:
            curr=q.popleft()
            s=s+curr
            for nei in hmap[curr]:
                indegree[ord(nei)- ord('a')]-=1
                if indegree[ord(nei)- ord('a')] ==0:
                    q.append(nei)
        if len(s) != len(hmap):
            return ''
        return s
        
