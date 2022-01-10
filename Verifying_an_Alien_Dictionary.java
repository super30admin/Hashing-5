class Solution {
    HashMap<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        if(words == null || words.length == 0) return false;
        map = new HashMap<>();
        
        for(int i  =0; i < order.length(); i++){
            char c = order.charAt(i);
            map.put(c, i+1);
        }
        
        for(int i = 0; i < words.length-1; i++){
            String first = words[i];
            String second = words[i+1];
            
            if(isNotSorted(first, second)){
                return false;
            }
        }
        return true;
        
    }
    private boolean isNotSorted(String first, String second){
            int m = first.length();
            int n = second.length();
        
            for(int i = 0; i < m && i < n; i++){
                    char fc= first.charAt(i);
                    char sc = second.charAt(i);
                    if(fc != sc){
                        return map.get(fc) > map.get(sc);
                    }
            }
        return m > n;
    }
}

//TC: O(n*k)
//SC: O(n)
