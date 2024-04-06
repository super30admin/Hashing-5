class Solution {
    //TC: O(nk)
    //SC: O(1)
    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < order.length(); i++){
            char c = order.charAt(i);
            map.put(c,i);
        }
        for(int i = 0; i < words.length - 1; i++){
            String first = words[i];
            String second = words[i+1];
            if(notInOrder(first,second,map)){
                return false;
            }
        }
        return true;
    }
    private boolean notInOrder(String first, String second, HashMap<Character, Integer> map){
        int fl = first.length();
        int sl = second.length();
        for(int i = 0; i < fl && i < sl; i++){
            char fChar = first.charAt(i);
            char sChar = second.charAt(i);
            if(fChar != sChar){
                return map.get(fChar) > map.get(sChar);
            }
        }
        return fl > sl;
    }
}
