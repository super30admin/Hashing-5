// Time Complexity : The time complexity is O(mn) where m is the length of the array and n is the lenght of each word
// Space Complexity : The space complexity is O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class Solution {
    public boolean isAlienSorted(String[] words, String order) {

        Map<Character,Integer> map = new HashMap<>();

        //store the alien order in a map
        for(int i=0;i<order.length();i++){
            char ch = order.charAt(i);
            map.put(ch,i);
        }

        for(int i=0;i<words.length-1;i++){

            String word1 = words[i];
            String word2 = words[i+1];

            int len1 = 0;
            int len2 = 0;

            //compare two consecutive words to check is they are sorted or not
            while(len1 < word1.length() && len2 < word2.length()){

                char c1 = word1.charAt(len1);
                char c2 = word2.charAt(len2);

                if(c1 == c2){
                    len1++;
                    len2++;
                }
                else if(map.get(c1) > map.get(c2)){
                    return false;
                }
                else{
                    break;
                }
            }
            //when the second word is a prefic of first word
            if(len2 == word2.length() && len1 < word1.length()){
                return false;
            }
        }
        return true;
    }
}