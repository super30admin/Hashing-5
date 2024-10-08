//TC :- O(n*l) - where n is total number of words and l is average length of word
//SC :- O(1) - Map contains all the characters which is 26
class VerifyAlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            map.put(c, i + 1);
        }

        for (int i = 0; i < words.length - 1; i++) {
            if (!sorted(words[i], words[i + 1], map))
                return false;
        }
        return true;
    }

    private boolean sorted(String word1, String word2, HashMap<Character, Integer> map) {

        int m = word1.length();
        int n = word2.length();

        for (int i = 0; i < m && i < n; i++) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);

            if (c1 != c2) {
                return map.get(c1) < map.get(c2);
            }
        }
        return m <= n;
    }
}