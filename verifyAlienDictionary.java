/*
Problem: https://leetcode.com/problems/verifying-an-alien-dictionary/
TC: O(n * m) where n = #words and m = avg length of each word
SC: O(1)
*/
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int charIndexMap[] = new int[26];
        
        for (int i = 0; i < order.length(); ++i) {
            char ch = order.charAt(i);
            charIndexMap[ch - 'a'] = i;
        }
        
        for (int i = 1; i < words.length; ++i) {
            String word1 = words[i-1];
            String word2 = words[i];
            int w1 = 0, w2 = 0;
            
            while (w1 < word1.length() && w2 < word2.length()) {
                if (word1.charAt(w1) != word2.charAt(w2)) {
                    if (charIndexMap[word1.charAt(w1) - 'a'] > charIndexMap[word2.charAt(w2) - 'a']) {
                        return false;
                    } else {
                        break;
                    }
                }
                ++w1; ++w2;
            }
            
            if (w2 == word2.length() && word1.length() > word2.length()) {
                return false;
            }
        }
        
        return true;
    }
}