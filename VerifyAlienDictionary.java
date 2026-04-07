// Time Complexity : O(n*l)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// We add the relative index of each character in a hashmap
// we iterate over a pair of two words and check if they satisfy the order one char at a time
// we also need to check if length of the first word is shorter -> app < apple

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> map = new HashMap();
        for (int i = 0; i < order.length(); i++)
            map.put(order.charAt(i), i);

        for (int i = 1; i < words.length; i++) {
            String a = words[i - 1];
            String b = words[i];
            if (!inOrder(a, b, map))
                return false;
        }

        return true;
    }

    private boolean inOrder(String first, String second,
            HashMap<Character, Integer> map) {

        for (int i = 0; i < first.length() && i < second.length(); i++) {
            if (first.charAt(i) != second.charAt(i)) {
                return map.get(first.charAt(i)) < map.get(second.charAt(i));
            }
        }

        if (first.length() > second.length())
            return false;
        return true;
    }
}