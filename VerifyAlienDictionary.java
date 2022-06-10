//TC : O(N*K) n = no. of words and k = avg.length of word

//SC : O(1) //as map will be having max 26 characters 

class Solution {
    HashMap<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        if(words == null || words.length == 0)  return true;
        
        map = new HashMap<>();
        
        for(int i = 0; i< order.length(); i++){
            char ch = order.charAt(i);
            
            if(!map.containsKey(ch)){
                map.put(ch, i);
            }
        }//Created map of charactersin in order which will be having max 26 characters
        
        
        for(int i = 0; i< words.length - 1; i++){
            String s1 = words[i];
            String s2 = words[i+1];
            //Comparing 2 words everytime
            if(inValidOrder(s1,s2))  return false;
        }        
        return true;
    }
    
    public boolean inValidOrder(String s1, String s2){
            for(int k = 0; k < s1.length() && k < s2.length(); k++){
                if(s1.charAt(k) != s2.charAt(k)){
                    return map.get(s1.charAt(k)) > map.get(s2.charAt(k));
                }//If mismatch occurs, will check the indices from the map
            }        
            if(s1.length() > s2.length())   return true; // if no mismatch or index reaches at the length of s1/s2, return true
            return false;
    }
    
}