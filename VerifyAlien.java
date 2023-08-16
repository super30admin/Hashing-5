// TC:O(nk)   ...k= avg length of words
// SC:O(1)

class Solution {
    HashMap<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        
        map = new HashMap();
        for(int i=0;i<order.length();i++){
            map.put(order.charAt(i),i);
        }
        
        int n= words.length;
        for(int i=0;i<n-1;i++){
            String first= words[i];
            String second= words[i+1];
            if(isNotSorted(first,second)) return false;
        }
        return true;
    }
    
    private boolean isNotSorted(String first, String second){
        for(int i=0; i< first.length() && i< second.length(); i++){
            char fChar= first.charAt(i);
            char sChar= second.charAt(i);
            
            if(fChar!=sChar){
                return map.get(fChar)>map.get(sChar);
            }
        }
        
        if(first.length()> second.length()) return true;
        return false;
    }
}
