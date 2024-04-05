import java.util.*;

class Solution {
    // Tc: O(M+N) Sc: O(1)
    HashMap<Character, Integer> map;

    public boolean isAlienSorted(String[] words, String order) {
        map = new HashMap<>();

        for (int i = 0; i < order.length(); i++) {
            char ch = order.charAt(i);
            map.put(ch, i);
        }

        for (int i = 0; i < words.length - 1; i++) {
            if (!isSorted(words[i], words[i + 1]))
                return false;
        }
        return true;
    }

    public boolean isSorted(String first, String sec) {
        for (int i = 0; i < first.length() && i < sec.length(); i++) {
            if (first.charAt(i) != sec.charAt(i)) {
                return map.get(first.charAt(i)) < map.get(sec.charAt(i));
            }
        }

        return first.length() < sec.length();
    }
}