//Time- O(n*K)
//Space - O(1)
class Solution {
    HashMap<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        map = new HashMap<>();
        for(int i=0; i<order.length(); i++){
            map.put(order.charAt(i),i);
        }
        for(int i=0; i< words.length-1;i++){
            String firstWord = words[i];
            String secondWord = words[i+1];
            if(!sortedString(firstWord, secondWord)){
                return false;
            }
        }
        return true;
        
    }

    private boolean sortedString(String firstWord, String secondWord){
        for(int i=0; i<firstWord.length() && i<secondWord.length(); i++){
            if(firstWord.charAt(i) != secondWord.charAt(i)){
                if(map.get(firstWord.charAt(i)) > map.get(secondWord.charAt(i))){
                    return false;
                }else {
                    return true;
                }
            }
        }
        if(secondWord.length() < firstWord.length()){
            return false;
        }
        return true;

    }
}