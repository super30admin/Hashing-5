// Time Complexity: O(n)+ O(n * k) where n is the lenght of words array and k is the average lenght of each word
// Space Complexity: O(1) since the hashmap cannot exceed 26 chars
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        if (words == null || words.length == 0) return true;

        HashMap<Character, Integer> map = new HashMap<>();

        // Store the orders string to a hashmap for reference
        for (int i = 0; i< order.length(); i++) {
            char c = order.charAt(i);
            map.put(c, i);
        }

        for (int i=0; i < words.length - 1; i++) {
            String firstWord = words[i];
            String secondWord = words[i+1];

            if (isNotSorted(firstWord, secondWord, map)) {
                return false;
            }
        }

        return true;
    }

    private boolean isNotSorted(String firstWord, String secondWord, HashMap<Character, Integer> map) {
        int m = firstWord.length();
        int n = secondWord.length();

        for (int i=0; i < m && i < n; i++) {
            char firstChar = firstWord.charAt(i);
            char secondChar = secondWord.charAt(i);

            if (firstChar != secondChar) {
                return map.get(firstChar) > map.get(secondChar);
            }
        }

        return m > n;
    }
}