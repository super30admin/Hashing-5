class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        '''
        Time Complexity: O(C), where C is the total number of characters in all words
        Space Complexity: O(1), as the alien_order dictionary has a fixed size (26 characters)
        '''

        # Create a dictionary to map characters to their corresponding alien order
        alien_order = {char: i for i, char in enumerate(order)}

        # Define a helper function to compare two words
        def compare_words(word1, word2):
            n1, n2 = len(word1), len(word2)
            i = 0

            # Compare the characters at the same position in both words
            while i < n1 and i < n2:
                char1, char2 = word1[i], word2[i]

                # If the characters are different, compare their alien order
                if alien_order[char1] < alien_order[char2]:
                    return True
                elif alien_order[char1] > alien_order[char2]:
                    return False

                i += 1

            # If all characters in word1 match word2 so far, word1 should be shorter
            return i == n1

        # Iterate through the list of words and compare adjacent pairs
        for i in range(len(words) - 1):
            if not compare_words(words[i], words[i + 1]):
                return False  # If an inversion is found, return False

        return True  # All words are sorted according to the alien order
