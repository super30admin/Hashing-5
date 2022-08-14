//Time: O(NK) | Space: O(1)

class Solution {
    Map<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        map = new HashMap<>();
        for(int i=0;i<order.length();i++) {
            map.put(order.charAt(i), i);
        }
        for(int i=1;i<words.length;i++) {
            if(isNotValid(words[i-1], words[i])) return false;
        }
        return true;
    }
    private boolean isNotValid(String word1, String word2) {
        int i = 0;
        int j = 0;
        char c1 = word1.charAt(i);
        char c2 = word2.charAt(j);
        int m = word1.length();
        int n = word2.length();
        while(i<m && j<n) {
            c1 = word1.charAt(i);
            c2 = word2.charAt(j);
            if(map.get(c1) > map.get(c2)) return true;
            if(map.get(c1) < map.get(c2)) return false;
            i++;
            j++;
        }
        if(m > n) return true;
        return false;
    }
}