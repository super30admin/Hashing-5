// Time Complexity : O(n * L), n = number of strings in the dictionary and L=length of each string
// Space Complexity : O(1), since 26 characters constant
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
The idea is to get the indices of the characters in the strings of dictionary and compare the indices. Ideally,
since in a dictionary, the first string's characters' indices should be less than the consecutive strings'.
So for faster access of indices, we store the characters along with their indices of the order string in
a map.We also handle the cases where if the 1st string's length is greater than second's and vice versa.
 */
class Solution {
    Map<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        this.map = new HashMap<>();
        for(int i = 0 ; i < order.length() ; i++) {
            map.put(order.charAt(i), i);
        }

        for(int i = 0 ; i < words.length - 1 ; i++) {
            String firstWord = words[i];
            String secondWord = words[i + 1];
            if(notSorted(firstWord, secondWord))
                return false;
        }
        return true;
    }

    private boolean notSorted(String firstWord, String secondWord) {
        for(int i = 0 ; i < firstWord.length() && i < secondWord.length() ; i++) {
            char fChar = firstWord.charAt(i);
            char sChar = secondWord.charAt(i);
            if(fChar != sChar)
                return map.get(fChar) > map.get(sChar); //ideally it should be smaller
        }
        return firstWord.length() > secondWord.length(); //apple,app -> returns true and eventually false at line 13
    }
}