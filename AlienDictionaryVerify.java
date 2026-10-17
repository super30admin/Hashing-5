/**
 * Time Complexity:  O(nk)  where n is the number of words, k is the avergae length of words
 * Space Complexity: O(1) as there are only 26 alphabets and the map contains only these as keys.
 */
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> positions = new HashMap<>();
        int x = 1;
        for (char ch : order.toCharArray()) {
            positions.put(ch, x);
            x++;
        }

        for (int i = 0; i < words.length - 1; i++) {
            String fWord = words[i];
            String sWord = words[i + 1];

            for (int j = 0; j < fWord.length() && j < sWord.length(); j++) {
                if (positions.get(fWord.charAt(j)) > positions.get(sWord.charAt(j))) {
                    return false;
                } else if (positions.get(fWord.charAt(j)) < positions.get(sWord.charAt(j))) {
                    break;
                }
            }
            if (!fWord.equals(sWord) && fWord.startsWith(sWord)) {
                return false;
            }
        }
        return true;

    }
}