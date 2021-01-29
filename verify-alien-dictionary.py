# TIME COMPLEXITY: O(number of words * average length of word) ~ O(n^2)
# SPACE COMPLEXITY: O(size of dictionary)
import collections

class Solution(object):
    def isAlienSorted(self, words, order):
        """
        :type words: List[str]
        :type order: str
        :rtype: bool
        """
        # Create a mapping of dictionary characters
        # EG: hlabc... -> 0,1,2,3,4...
        mapping = collections.defaultdict(int)
        for i, c in enumerate(order):
            mapping[c] = i

        # Transform every character in the words list to a corresponding mapping - List of lists
        # ["hello","leetcode"] -> [[0, 6, 1, 1, 14], [1, 6, 6, 19, 4, 14, 5, 6]]
        words = [[mapping[c] for c in word] for word in words]

        # Now compare every consequetive list
        for i in range(len(words) - 1):
            word, next_word = words[i], words[i + 1]
            if not self.check(word, next_word):
                return False
        return True

    # Function to check if two words are in the right order
    def check(self, word, next_word):
        ordered = True
        # Two pointers, one for each word
        j = 0
        while j < len(word) and j < len(next_word):
            # Return True if the number at j is less for first word
            if word[j] < next_word[j]:
                return True
            # Advance if number is equal
            elif word[j] == next_word[j]:
                j += 1
            else:  # Greater, return False
                return False
        # If first word is longer, return False
        if j != len(word):
            ordered = False

        return ordered
