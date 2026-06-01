

class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        self.map = {}
        for i in range(len(order)):
            c = order[i]
            self.map[c] = i 

        for i in range(len(words)-1):
            first_word = words[i]
            second_word = words[i+1]
            if self.checkSorted(first_word,second_word):
                return False 
        return True 


    def checkSorted(self,first,second):
        for i in range(min(len(first),len(second))):
            f_char = first[i]
            s_char = second[i]
            if f_char != s_char:
                if self.map[f_char] > self.map[s_char]:
                    return True 
                else:
                    return False 
        if len(first) > len(second):
            return True 
        else:
            return False