
#Approach
# create a hashmap with key as character and value as indices in the order
# for every 2 words find wheather it follows the allien dictionary by finding the non first equal characters indices
# once we find the words simultaneously add the indices in the graoph and find whetrt there is any cycle in the graph using the toposort


#Complexity
#Time: O(n+E)
#Space: O(n)



from typing import List


class Solution:
    def alienOrder(self, words: List[str]) -> str:
        hashMap = dict()
        ndegree = [0]*26
        result =""
        for word in words:
            for char in word:
                if char not in hashMap:
                    hashMap[char]=set()


        for i in range(len(words)-1):
            if not self.issorted(words[i],words[i+1],hashMap,ndegree):
                return ""
        queue = []
        for keys in hashMap:
            if ndegree[ord(keys)-ord("a")]==0:
                queue.append(keys)
                result+=keys

        while queue:
            node = queue.pop(0)

            for neighbours in hashMap[node]:
                ndegree[ord(neighbours)-ord("a")]-=1
                if ndegree[ord(neighbours)-ord("a")]==0:
                    queue.append(neighbours)
                    result+=neighbours

        if len(result)!=len(hashMap):
            return ""

        return result


    def issorted(self,firstWord,secondWord,hashMap,ndegree):

        for i in range(min(len(firstWord),len(secondWord))):
            if firstWord[i]!=secondWord[i]:
                if secondWord[i] not in hashMap[firstWord[i]]:
                    hashMap[firstWord[i]].add(secondWord[i])
                    ndegree[ord(secondWord[i])-ord("a")] +=1
                    return True
        return len(firstWord)<=len(secondWord)


s = Solution()
#print("result: ",s.alienOrder(["z","x","z"]))
print("result: ",s.alienOrder(["wrt","wrf","er","ett","rftt"]))


