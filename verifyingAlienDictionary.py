class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        # T: O(n * m), S: O(1)
        # Step 1: Create a mapping from each character to its index in the alien order
        order_index = {char: idx for idx, char in enumerate(order)}

        # Step 2: Compare each pair of adjacent words
        for i in range(len(words) - 1):
            word1, word2 = words[i], words[i + 1]

            # Compare characters of both words
            for c1, c2 in zip(word1, word2):
                if order_index[c1] < order_index[c2]:
                    break  # Correct order
                elif order_index[c1] > order_index[c2]:
                    return False  # Incorrect order
            else:
                # Words are identical up to the length of the shorter word
                if len(word1) > len(word2):
                    return False  # Longer word comes before its prefix

        return True  # All words are in correct order
