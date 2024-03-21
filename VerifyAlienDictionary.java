/*
* Did this code successfully run on Leetcode : YES
* 
* Any problem you faced while coding this : NO
* 
* Time Complexity: O(n*k)
    n = total words
    k = avg length of each word
* 
* Space Complexity: O(1)
    hashmap keys will be max of 26 characters
* 
*/

import java.util.HashMap;

public class VerifyAlienDictionary {
    private boolean notInOrder(String word1, String word2, HashMap<Character, Integer> hmap) {
        int length = Math.min(word1.length(), word2.length());

        for (int index = 0; index < length; index++) {
            char word1Char = word1.charAt(index);

            char word2Char = word2.charAt(index);

            if (word1Char != word2Char) {
                if (hmap.get(word1Char) > hmap.get(word2Char)) {
                    return true;
                }
                return false;
            }
        }

        if (word1.length() > word2.length()) {
            return true;
        }

        return false;
    }

    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> hmap = new HashMap<>();

        for (int index = 0; index < order.length(); index++) {
            hmap.put(order.charAt(index), index);
        }

        for (int index = 0; index < words.length - 1; index++) {
            if (notInOrder(words[index], words[index + 1], hmap)) {
                return false;
            }
        }

        return true;
    }
}
