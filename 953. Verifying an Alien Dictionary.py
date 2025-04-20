#time:O(number of words*average size of words)
#space:O(1)
class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:

        #create map for ordering
        n= len(order)
        hmap=dict()
        for i in range(n):
            hmap[order[i]]=i
        word_list_size= len(words)

        for i in range(1,word_list_size):
            word1=words[i-1]
            word2=words[i]
            first_word_size= len(word1)
            second_word_size= len(word2)
            m,n=0,0
            while m < first_word_size and n < second_word_size:
                if word1[m] != word2[n]:
                    if hmap[word1[m]] > hmap[word2[n]]:
                        return False
                    break
                
                m+=1
                n+=1
                if n== second_word_size and m!= first_word_size:
                    return False
        return True

