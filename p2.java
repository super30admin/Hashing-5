// Time Complexity :O(n*l)
// Space Complexity :O(1)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no


// Your code here along with comments explaining your approach

class Solution {
    HashMap<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        //put the characters in hashmap with thier order
        map = new HashMap<>();
        for(int i=0; i<26; i++){
            map.put(order.charAt(i), i);
        }//Go through all the words
        for(int i=0; i<words.length-1; i++){
            if(!check(words[i], words[i+1]))return false;
        }
        return true;
    }

//Check if two words are sorted or not
    private boolean check(String word1, String word2){
        for(int i=0; i<word1.length() && i<word2.length(); i++){
            char x = word1.charAt(i);
            char y = word2.charAt(i);
            if(x != y){
                return map.get(x) < map.get(y);
            }
        }
        return word1.length() <= word2.length();
    }
}