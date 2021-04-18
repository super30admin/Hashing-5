/*
TC: O(nk), where n is number of words and k is length of word
SC:O(1), since constant space
*/
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        
        if(words.length == 0 || words == null){
            return false;
        }
        
        HashMap<Character, Integer> orderMap = new HashMap<>();
        for(int i = 0; i < order.length();i++){
            orderMap.put(order.charAt(i),i+1);
        }
        
        for(int i = 0; i < words.length-1;i++){
            String word1 = words[i]; 
            String word2 = words[i+1];
            if(isNotSorted(word1,word2,orderMap)){
                return false;
            }
        }
        return true;
    }
    
    private boolean isNotSorted(String word1, String word2, HashMap<Character, Integer> map){
        int m = word1.length();
        int n = word2.length();
        
        for(int i = 0; i < m && i < n; i++){
            if(word1.charAt(i) != word2.charAt(i)){
                return map.get(word1.charAt(i)) > map.get(word2.charAt(i));
            }
        }
        return m > n;
    }
}