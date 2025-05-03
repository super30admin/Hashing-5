# Time Complexity : O(n * l)
# Space Complexity : O(n * l)
# Did this code successfully run on Leetcode : YES
# Any problem you faced while coding this : NO

class Solution:
    def alienOrder(self, words: List[str]) -> str:

        in_degrees = {}
        for word in words:
            for c in word:
                in_degrees[c] = 0   

        adj_list = {}

        for i in range(len(words) - 1):
            word_a, word_b = words[i], words[i+1]
            i = 0
            while i < max(len(word_a), len(word_b)):
                if i == len(word_b) and i < len(word_a):
                    return ""
                if i >= len(word_a):
                    break
                if word_a[i] == word_b[i]:
                    i += 1
                else:
                    # mismatch
                    if word_a[i] in adj_list:
                        adj_list[word_a[i]].append(word_b[i])
                    else:
                        adj_list[word_a[i]] = [word_b[i]]

                    in_degrees[word_b[i]] += 1
                    break
            
        queue = collections.deque()

        res = ""

        for c,indeg in in_degrees.items():
            if indeg == 0:
                queue.append(c)
        
        while queue:
            char = queue.popleft()
            res += char
            if len(res) == len(in_degrees):
                return res
            if char in adj_list:
                for neib in adj_list[char]:
                    in_degrees[neib] -= 1
                    if in_degrees[neib] == 0:
                        queue.append(neib)

        return ""