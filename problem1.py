# problem 1 

class Solution:
    def __init__(self):
        self.map = {}
        self.path = [False] * 26
        self.visited = [False] * 26
        self.sb = []


    def hasCycle(self, c):
        if self.path[ord(c) - ord('a')]:
            return True
        if self.visited[ord(c) - ord('a')]:
            return False

        self.path[ord(c) - ord('a')] = True

        for ne in self.map[c]:
            if self.hasCycle(ne):
                return False

        self.path[ord(c) - ord('a')] = False
        self.visited[ord(c) - ord('a')] = True

        self.sb.append(c)

        return False


    def buildGraph(self,words):
        for word in words:
            for ch in word:
                self.map[ch] = set()
        
        for i in range(len(words)-1):
            first_word = words[i]
            second_word = words[i+1]

            if first_word.startswith(second_word) and len(first_word) > len(second_word):
                self.map.clear()
                return 
            
            for j in range(min(len(first_word),len(second_word))):
                fchar = first_word[j]
                schar = second_word[j]
                if fchar != schar:
                    if schar not in self.map[fchar]:
                        self.map[fchar].add(schar)
                    break


    def alienOrder(self, words: List[str]) -> str:
        self.buildGraph(words)
        if len(self.map) == 0:
            return ""
        
        for c in self.map.keys():
            if self.hasCycle(c):
                return ""
        
        self.sb.reverse()
        return "".join(self.sb)