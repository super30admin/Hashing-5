import java.util.HashMap;


// TIme Complexity: O(n*l)
// Space Complexity: O(1)
public class problem1 {

    HashMap<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        this.map = new HashMap<>();
        for (int i = 0; i < order.length() - 1; i++) {
            this.map.put(order.charAt(i), i);
        }
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            if (notSorted(word1, word2))
            {
                return false;
            }
        }
        return true;
    }
    private boolean notSorted(String word1, String word2) {
        for (int i = 0; i < word1.length() && i<word2.length(); i++) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);
            if (c1 != c2)
            {
                return map.get(c1) > map.get(c2);
            }
        }
        return word1.length() > word2.length();
    }
}