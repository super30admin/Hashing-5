class Solution:
    def alienOrder(self, words: List[str]) -> str:
        adj_list = collections.defaultdict(list)
        indegree = collections.defaultdict(int)

        #initalizing the in-degree and adj_list

        for w in words:
            for char in w:
                if char in adj_list:
                    continue
                adj_list[char]

                if char in indegree:
                    continue
                indegree[char]=0

        #build the acutal graph

        for w in range(len(words)-1):
            found = False
            w1,w2 = words[w],words[w+1]
            min_len = min(len(w1),len(w2))

            for i in range(min_len):

                if w1[i]!=w2[i]:
                    adj_list[w1[i]].append(w2[i])
                    indegree[w2[i]]+=1
                    found = True
                    break

            if found == False and len(w1)>len(w2):
                return  ""


        #topological sort to get the alienOrder
        queue = []
        for key in indegree:
            if indegree[key]==0:
                queue.append(key)
        ans = ""
        while queue:
            node = queue.pop(0)

            ans = ans + node

            for neighbour in adj_list[node]:
                indegree[neighbour]-=1

                if indegree[neighbour]==0:
                    queue.append(neighbour)

        if len(ans)< len(indegree):
            return ""

        return ans            










        


                  

        