""""// Time Complexity : O(n.k); where n is number of words and k is avg length of a word.
// Space Complexity : O(1) as map will at most have 26 chars
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :
"""
from collections import deque
class Solution:
    def alienDictionary(self, words):
        h={}
        indegree=[0]*26

        n=len(words)
        # print(n)
        for i in range(n):
            word=words[i]
            for j in range(len(word)):
                if word[j] not in h:
                    h[word[j]]=set()
        for i in range(n-1):
            first=words[i]
            second=words[i+1]
            fl=len(first)
            sl=len(second)

            if fl>sl and first[:sl]==second:
                h=[]
                break

            p1=0
            p2=0

            while p1<fl and p2<sl:
                out=first[p1]
                inn =second[p2]
                if out!=inn:
                    sets=h[out]
                    if inn not in sets:
                        indegree[ord(inn)-ord('a')]+=1
                        sets.add(inn)
                    break
                p1+=1
                p2+=1

        q=deque()
        result=[]

        for c in h:
            if indegree[ord(c)-ord('a')]==0:
                q.append(c)
                result.append(c)

        if len(q)==0:
            return ""
        while q:
            c=q.popleft()
            edges=h[c]
            for edge in edges:
                indegree[ord(edge)-ord('a')]-=1
                if indegree[ord(edge)-ord('a')]==0:
                    q.append(edge)
                    result.append(edge)

        if len(result)!=len(h):
            return ""

        return ''.join(result)

Obj=Solution()
print(Obj.alienDictionary(["abc", "ab"]))
# print(Obj.alienDictionary(["wrt", "wrf", "er", "ett", "rftt"]))








