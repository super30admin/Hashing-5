'''
time complexity: O(nk)
k is avg len of words
space complexity: O(1)
'''
class Solution:
    def alienOrder(self, words: List[str]) -> str:
        if(len(words)==0): return ""
        self.graphMap = {}
        self.inorder = [0 for i in range(26)]
        self.buildGraph(words)
        q = deque([])
        for ch in self.graphMap:
            if(self.inorder[ord(ch)-ord('a')]==0):
                q.append(ch)
        res = []
        while(len(q)!=0): #v + e
            curr = q.popleft()
            res.append(curr)
            if len(res) == len(self.graphMap): break
            currSet = self.graphMap[curr]
            for ch in currSet:
                self.inorder[ord(ch) - ord('a')]-=1
                if(self.inorder[ord(ch) - ord('a')] == 0):
                    q.append(ch)
        if len(res) != len(self.graphMap): return ""
        return "".join(res)
    
    def buildGraph(self,words): #nk
        for w in words:
            for l in w:
                self.graphMap[l] = set()
        
        for i in range(len(words)-1):
            fw = words[i]
            sw = words[i+1]
            
            n = len(fw)
            m = len(sw)
            i = 0
            j = 0
            if(len(fw)!=len(sw) and fw.startswith(sw)):
                self.graphMap = {}
                break
            while(i<n and j<m):
                
                if(fw[i]!=sw[j]):
                    if sw[j] not in self.graphMap[fw[i]]:
                        self.graphMap[fw[i]].add(sw[i])
                        self.inorder[ord(sw[i]) - ord('a')]+=1
                    break
                i+=1
                j+=1
            