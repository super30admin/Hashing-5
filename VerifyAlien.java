import java.util.HashMap;
import java.util.Map;

public class VerifyAlien {

    // TC : O(m * n) m - number of words n - average length of the word
    // SC : O(1)
    Map<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        if(words == null || words.length == 0) return false;

        map = new HashMap<>();

        for(int i=0; i < order.length(); i++) {
            char c = order.charAt(i);
            map.put(c, i);
        }

        for(int i=0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i + 1];
            if(isNotSorted(first,second)) {
                return false;
            }
        }
        return true;
    }

    private boolean isNotSorted(String first, String second) {
        int m = first.length();
        int n = second.length();

        for(int i=0; i < m && i < n; i++) {
            char firstChar = first.charAt(i);
            char secondChar = second.charAt(i);
            if(firstChar != secondChar){
                return map.get(firstChar) > map.get(secondChar);
            }
        }
        return m > n;   // this will handle the third sample example in the question

    }
}
