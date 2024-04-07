import java.util.HashMap;
import java.util.Map;

public class VerifyAlienDictionary {
    // TC: O(N * K) where N is number of words and K is max length of word
    // SC: O(1)
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }
        for (int i = 0; i < words.length - 1; i++) {
            if (!isOrdered(words[i], words[i + 1], map)) {
                return false;
            }
        }
        return true;
    }

    private boolean isOrdered(String a, String b, Map<Character, Integer> map) {
        int minLength = Math.min(a.length(), b.length());
        for (int i = 0; i < minLength; i++) {
            int posA = map.get(a.charAt(i));
            int posB = map.get(b.charAt(i));
            if (posA == posB) {
                continue;
            } else if (posA < posB) {
                return true;
            } else {
                return false;
            }
        }
        return a.length() <= b.length();
    }
}
