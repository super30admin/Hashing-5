import java.util.HashMap;

public class VarifyingAnAlienDictionary {
    /**
     iterate thtough the order and add each character to depict which index it falls on in to the map.
     iterate through both strings.
     if the character at given indexis different for bot hthe strings and
     Time complexity - o(nK)- n -number of words, k avg length of each Word
     space complexity -o(1) - 26 characters



     */
    HashMap<Character, Integer> map;

    public boolean isAlienSorted(String[] words, String order) {
        if (words == null || words.length == 0) {
            return true;
        }
        // campture all the chars and their indices
        map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            map.put(c, i);
        }
        for (int i = 0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i + 1];
            if (isNotSorted(first, second)) {
                return false;
            }
        }
        return true;
    }

    private boolean isNotSorted(String first, String second) {
        // get the length of fist and second strings
        int m = first.length();
        int n = second.length();
        for (int i = 0; i < m && i < n; i++) {
            char firstchar = first.charAt(i);
            char secondchar = second.charAt(i);
            if (firstchar != secondchar) {
                return map.get(firstchar) > map.get(secondchar);

            }
        }
        return (m > n);
    }
}
