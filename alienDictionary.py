#Time Complexity : O(nk) where n is the number of words and k is the average length of each word
#Space Complexity : O(1)
#Did this code successfully run on Leetcode : Yes

class Solution:
    def alienOrder(self, words: List[str]) -> str:
        if not words:
            return ""
        mapping = {}
        incoming = [0 for _ in range(26)]

        #BUILD GRAPH
        for i in range(len(words)):
            for char in words[i]:
                mapping[char] = set()

        for i in range(len(words) - 1):
            w1, w2 = words[i], words[i+1]
            if len(w1) > len(w2) and w1[:len(w2)] == w2:
                return ""
            for j in range(len(min(w1, w2))):
                if w1[j] != w2[j]:
                    if w2[j] not in mapping[w1[j]]:
                        mapping[w1[j]].add(w2[j])
                        incoming[ord(w2[j])-ord("a")] += 1
                    break

        q = deque()
        result = []


        #APPEND ALL 0 INCOMING NODES TO THE QUEUE
        for key in mapping.keys():
            letter = ord(key) - ord("a")
            if incoming[letter] == 0:
                q.append(key)

        #PERFORM BFS ON THE QUEUE
        while q:
            char = q.popleft()
            result.append(char)
            edges = list(mapping[char])
            for edge in edges:
                incoming[ord(edge)-ord("a")] -= 1
                if incoming[ord(edge)-ord("a")] == 0:
                    q.append(edge)

        return "".join(result) if len(result) == len(mapping) else ""
