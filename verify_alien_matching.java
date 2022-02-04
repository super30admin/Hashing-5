// Time Complexity : O(n*k) [n- words , k - size of each word]
// Space Complexity : O(1): Hashmap stores 26 characters 

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        
        // Map to store the character and its priority
        HashMap<Character, Integer> map =  new HashMap<>();
        
        // update the hashmap
        for (int i = 0; i < order.length(); i++){
            
            char c = order.charAt(i);
            map.put(c, i);
           
        }
        
        // Iterate through each word and compare the two adjacent words
        
        for (int i = 0; i < words.length -  1; i++){
            
            // first word
            String first =  words[i];
            
            // second word
            String second = words[i + 1];
            
            boolean isSorted = false;
            
            // Iterate through each character in the word till they are not same
            for (int j = 0; j < first.length() && j < second.length(); j++){
                
                 // first char
                 char firstCh = first.charAt(j);
                
                 // second char
                char secondCh = second.charAt(j);
                
                 if (firstCh  != secondCh){
                    
                     // check the order : if not sorted, return false
                     if (map.get(firstCh) > map.get(secondCh)){
                         return false;
                     }
                      // if characters diff and are already sorted
                    else{
                        isSorted = true;
                        break;
                    }
                }
                
            }
            
            
            // apple app
            // If second word is completely verified but first word has more length
            if (!isSorted && first.length() > second.length()){
                return false;
            }
           
        }        
        return true;
    }
}