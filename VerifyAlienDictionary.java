// Time Complexity: O(n * k)
// Space Complexity: O(1)

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            map.put(c, i);
        }

        for (int i = 0; i < words.length - 1; i++) {
            String firstWord = words[i];
            String secondWord = words[i + 1];
            if (notOrdered(firstWord, secondWord, map)) {
                return false;
            }
        }

        return true;
    }

    public boolean notOrdered(String first, String second, HashMap<Character, Integer> map) {
        for (int i = 0; i < first.length() && i < second.length(); i++) {
            char fChar = first.charAt(i);
            char sChar = second.charAt(i);
            if (fChar != sChar)
                return map.get(fChar) > map.get(sChar);
        }

        return first.length() > second.length();
    }
}