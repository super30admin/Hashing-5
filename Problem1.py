# Problem 1 : Alien Dictionary
# Time Complexity : O(V + E) where V is the number of nodes(characters) and E is the edge in the graph
# Space Complexity : O(V + E) where V is the number of nodes(characters) and E is the edge in the graph
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this :
'''
None
'''

# Your code here along with comments explaining your approach
from typing import List
from collections import defaultdict, deque

class Solution:
    def alienOrder(self, words: List[str]) -> str:
        # define graph which will be dependency graph ie it will store which characters must follow others
        graph = defaultdict(list)
        # define indegrees array which will track how many characters must come before each one
        indegrees = [0] * 26
        # call buildGraph function to create a graph and fill indegrees array for the words list
        self.buildGraph(words, graph, indegrees)
        
        # define result array which will store result
        result = []
        # define q deque which will store the character whose indegrees value is 0
        q = deque()
        
        # for each character in graph
        for ch in graph:
            # check if the value of the character of indegrees array is 0 and if it is then add the character to queue and result 
            if indegrees[ord(ch) - ord('a')] == 0:
                q.append(ch)
                result.append(ch)
        
        # check if the length of the result is equal to length of the graph and if it is then convert the result to string and return the result
        if len(result) == len(graph):
            return ''.join(result)
        # check if queue is empty and if it is then return empty string
        if not q:
            return ""
        
        # loop till queue is not empty
        while q:
            # pop the top character from the queue
            curr = q.popleft()
            # loop through each neighbour of the current character in the graph
            for neighbor in graph[curr]:
                # decrement the value of indegrees for the neighbour character
                indegrees[ord(neighbor) -ord('a')] -= 1
                # check if the value of indegrees for the neighbour character is 0
                if indegrees[ord(neighbor) - ord('a')] == 0:
                    # if it is then append neighbour character in the queue and result
                    q.append(neighbor)
                    result.append(neighbor)
                    # check if the length of result is equal to length of graph and if it is then convert the result to string then return 
                    if len(result) == len(graph):
                        return ''.join(result)
        # return empty string
        return ""
    
    # buildGraph function to build graph for words list
    def buildGraph(self, words, graph, indegrees):
        # loop through each word in words list
        for word in words:
            # loop through each character for the word
            for ch in word:
                # check if the character is not in the graph and if it is true then create an empty entry in the graph 
                if ch not in graph:
                    graph[ch] = []

        # loop from 0 to length of words
        for i in range(len(words)-1):
            # store the ith word in the first variable
            first = words[i]
            # store (i+1)th word in the second variable
            second = words[i+1]
            # check if the first word startswith second word and the length of the first word is greater than length of second word
            if first.startswith(second) and len(first) > len(second):
                # if it is then clear graph and return 
                graph.cear()
                return
            # loop from 0 to minimum between length of first and second word
            for j in range(min(len(first), len(second))):
                # get the jth character from first word
                fChar = first[j]
                # get the jth character from second word
                sChar = second[j]
                # check of the first character is not equal to second character
                if fChar != sChar:
                    # if it then append second character to list as value and first character as key
                    graph[fChar].append(sChar)
                    # increment the indegrees value of second character
                    indegrees[ord(sChar) - ord('a')] += 1
                    # break
                    break
