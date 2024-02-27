// Time Complexity : O(n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : No
class Solution {
    Map<Character,Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        this.map = new HashMap<>();
        for(int i = 0; i < order.length(); i++){
            char ch = order.charAt(i);
            map.put(ch,i);
        }
        for(int i = 0; i < words.length - 1; i++){
            String word1 = words[i];
            String word2 = words[i+1];
            if(notOrder(word1,word2))
                return false;
        }
        return true;
    }
    public boolean notOrder(String word1, String word2){
        int i = 0;
       
        for(i = 0; i < word1.length() && i < word2.length(); i++){
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);
            if(c1 != c2){
                if(map.get(c1) > map.get(c2))
                    return true;
                    break;
            }
        }
        if(i!=word1.length() && i == word2.length())
            return true;
        return false;
    }
}