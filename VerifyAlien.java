class VerifyAlien {
  /**
   * Time Complexity: O(n * k)
   * 
   *   k -> average word length in words
   * 
   * Space Complexity: O(1)
   */
  Map<Character, Integer> map;
  public boolean isAlienSorted(String[] words, String order) {
      if(words.length == 1) return true;
      map = new HashMap<>();
      
      for(int i = 0; i < 26; i++){
          map.put(order.charAt(i), i);
      }

      for(int i = 0; i < words.length-1; i++){
          
          if(!isSorted(words[i], words[i+1])) return false;
      }
      
      return true;
  }
  
  boolean isSorted(String word1, String word2) {
      if(word1.startsWith(word2)) { return word1.length() == word2.length(); }
      for(int i = 0; i < word1.length() && i < word2.length(); i++){
          char c1 = word1.charAt(i);
          char c2 = word2.charAt(i);
          if(c1 == c2){
              continue;
          } else if(map.get(c1) < map.get(c2)) { 
              return true;
          } else {
              return false;
          }
      }
      
      return true;
  }
}
