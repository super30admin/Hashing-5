# Time complexity : O(V+E)
# Space complexity : O(1)
# Leetcode : Solved and submitted

from collections import deque
class Solution:
    def alienOrder(self, words: List[str]) -> str:
        # create a hashmap to store the edges from one letter to another and indegress array to store the number of incoming edges
        self.hashmap = {}
        self.indeg = [0]*26
        
        # call the build graph to populate the hasmap and indegree
        self.buildGraph(words)
        res = ''
        
        # build a queue with the nodes having 0 incoming edges
        q = deque([])
        count = 0
        
        # add all the independent nodes into the queue and add them to the res, increment the count
        for key in self.hashmap:
            if self.indeg[ord(key) - ord('a')] == 0:
                q.append(key)
                res += key
                count += 1
           
        # if after addding to the q, if there is no element in the queue, return empty string
        if not q:
            return res
        
        # traverse over the queue
        while q:
            # pop the element from the quque
            ch = q.popleft()
            
            # if any point we have reached the end, which means all letters are covered, then return the res
            if count == len(self.hashmap):
                return res
            
            # go over the edges for the current char
            for edge in self.hashmap[ch]:
               # decrement their indegree and add to queu if they have 0 now
                self.indeg[ord(edge) - ord('a')] -= 1
                if self.indeg[ord(edge) - ord('a')] == 0:
                    # append the current char to queue and also to res, increment count and check if we are done
                    q.append(edge)
                    res += edge
                    count += 1
                    if count == len(self.hashmap):
                        return res
        
        # if the count is still not the amount of chars in hashmap, return emppty sting
        if count != len(self.hashmap):
            return ""
        
        # else return res
        return res
    
    def buildGraph(self, words):
        # add each letter from all words into hashmap
        for word in words:
            for letter in word:
                self.hashmap[letter] = set()
        
        # traverse over all the words comparing 2 words at a time
        for i in range(len(words)-1):
            out_going_word = words[i]
            incoming_word = words[i+1]
            
            fl = len(incoming_word)
            sl = len(out_going_word)
            
            # if we have same subtring and lengths are not same, then we cannot determine the order, so clear the hashmap and break
            if out_going_word.startswith(incoming_word) and fl != sl:
                self.hashmap = {}
                break
                
            j = 0
            while j < fl and j < sl:
                # compare the words, the out going letter would come before the incoming letter, so add to the hashmap and increment the indegree
                # break when the chars don't match
                if incoming_word[j] != out_going_word[j]:
                    if incoming_word[j] not in self.hashmap[out_going_word[j]]:
                        self.hashmap[out_going_word[j]].add(incoming_word[j])
                        self.indeg[ord(incoming_word[j]) - ord('a')] += 1
                    break
                j += 1
            
