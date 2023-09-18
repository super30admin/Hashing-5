// Approach: Custom compare function on the basis of order. orderMap<char, int> to store char to index mapping.
// Time: O(nl) where l = length of order and n = no. of chars in words
// Space: O(1)

import java.util.*;

class Verifying_an_Alien_Dictionary {
    public boolean isAlienSorted(String[] words, String order) {

        Map<Character, Integer> orderMap = new HashMap<>();
        for (int i = 0; i<order.length(); i++) {
            orderMap.put(order.charAt(i), i);
        }

        for (int i=1; i<words.length; i++) {
            int diff = compare(words[i-1], words[i], orderMap);
            if(diff > 0) return false;
        }

        return true;
    }

    private int compare(String a, String b, Map<Character, Integer> orderMap) {
        int i = 0, diff = 0;

        while (i<a.length() && i<b.length() && diff == 0) {
            diff = orderMap.get(a.charAt(i)) - orderMap.get(b.charAt(i));
            i++;
        }

        if (diff == 0) {
            diff = a.length() - b.length();
        }

        return diff;
    }
}