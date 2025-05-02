// Time Complexity : O(n*l) where n is number of words and l is the average length of words in the dictionary.
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

import java.util.*;
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<order.length(); i++)
            map.put(order.charAt(i), i);
        
        for(int i=1; i<words.length; i++)
        {
            String word1 = words[i-1];
            String word2 = words[i];

            if(notSorted(word1, word2, map))
                return false;

        }
        return true;
    }
            private boolean notSorted(String word1, String word2, Map<Character, Integer> map)
            {
                int index = 0;
                while(index < word1.length() && index < word2.length())
                {
                    if(word1.charAt(index) == word2.charAt(index)) index++;
                    else
                        return map.get(word1.charAt(index)) > map.get(word2.charAt(index));

                    
                }
                return word1.length() > word2.length();
            }
    }
