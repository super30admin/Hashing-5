//Time Complexity: O(N * L) where N is the number of words and L is the average length of a word.
//Space Complexity: O(1) map size is constant (only 26 characters)

//Build a rank map from each alien character to its index in `order`.
//For each adjacent pair, compare chars left→right; on first mismatch, ensure rank(first[i]) < rank(second[i]) or return false.
//If all chars match up to min length, ensure first.length() <= second.length(); if any check fails return false, else true.

class Solution {
    HashMap<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        this.map = new HashMap<>();
        for(int i=0; i<order.length(); i++){
            char c = order.charAt(i);
            map.put(c, i);
        }

        for(int i=0; i<words.length-1; i++){
            String first = words[i];
            String second = words[i+1];

            if(notSorted(first, second)) return false;
        }

        return true;
    }

    private boolean notSorted(String first, String second){

        for(int i=0; i<first.length() && i < second.length(); i++){
            char fChar = first.charAt(i);
            char sChar = second.charAt(i);

            if(fChar != sChar){
                return map.get(fChar) > map.get(sChar);  // apple, app
            }
        }

        return first.length() > second.length();
    }
}
