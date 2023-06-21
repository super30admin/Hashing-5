//SC: O(1)
//TC: O(nk) n-> no. of words in words[] and k length of each word
class Solution {
    HashMap<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        map = new HashMap<>();
        if(words.length == 0 || order.length() == 0) return false;
        
        for(int i = 0; i< order.length();i++){
            char c = order.charAt(i);
            map.put(c, i);
        }
        
        for(int i = 0; i<words.length - 1;i++){
            String first = words[i];
            String second = words[i+1];
            
            if(notSorted(first, second)){
                return false;
            }      
        }
        return true;
    }
    
    private boolean notSorted(String first, String second){
        int fl = first.length();
        int sl = second.length();
        
        for(int i=0; i< fl && i < sl;i++){
            char fChar = first.charAt(i);
            char sChar = second.charAt(i);
            if(fChar != sChar){
               return map.get(fChar) > map.get(sChar);
            }
        }
        return fl > sl;
    }
    
}
