class VerifyAlienDictionary {
    
    // Time Complexity: O(m) -- (n * maxLen(word))    (where m -> total number of chars in all strings of words)
    // Space Complexity: O(1)
    
    public boolean isAlienSorted(String[] words, String order) {
        // Edge Case Checking
        if(words == null || words.length == 0 || order == null || order.length() == 0)
            return false;
        
        // Create an array and store the order -- in which the elements are expected to be sorted
        int[] arr = new int[26];
        for(int i = 0; i < order.length(); i++){
            arr[order.charAt(i) - 'a'] = i;   
        }
        
        // Check every adjacent pair of words to see if they are sorted - if any of the pair is not sorted - return false
        for(int i = 1; i < words.length; i++){
            boolean result = areSorted(words[i-1], words[i], arr);
            if(!result)
                return result;
        }
        return true;
    }
    
    private boolean areSorted(String s1, String s2, int[] arr){
        int n = s1.length();
        int m = s2.length();
        int i = 0; int j = 0;
        
        // traverse the two strings
        while(i < n && j < m){
            // Until the characters are same - keep traversing
            if(s1.charAt(i) == s2.charAt(j)){
                i++; j++;
            }
            //  when the characters are different - then check their order and return true/false
            else{
                return arr[s1.charAt(i) - 'a'] < arr[s2.charAt(j) - 'a'];
            }
        }
            
        // After the loop - if the len(first string) > len(second string) -- return false
        if(n > m)
            return false;
            
        return true;
    }
}