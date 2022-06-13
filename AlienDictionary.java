public class AlienDictionary {
  /**
   * 
   * Time Complexity: O(n * k)
   * 
   * Space Complexity: O(1)
   */
  public String alienOrder(String[] words) {
    int[] indegrees = new int[26];
    Map<Character, List<Character>> map = new HashMap<>();
    
    for(String word : words){
        for(int i = 0; i < word.length(); i++){
            char curr = word.charAt(i);
            if(!map.containsKey(curr)){
                map.put(curr, new ArrayList<>());
            }
        }
    }
    
    // find indegrees and create the adjacency list
    for(int i = 0; i < words.length - 1; i++){
        String word1 = words[i];
        String word2 = words[i+1];
        
        // when first word starts with the 2nd word, we have a wrong order
        if(word1.startsWith(word2) && word1.length() > word2.length()) return "";
        
        for(int j = 0; j < word1.length() && j < word2.length(); j++){
            if(word1.charAt(j) != word2.charAt(j)){
                indegrees[word2.charAt(j) - 'a']++;
                List<Character> list = map.get(word1.charAt(j));
                list.add(word2.charAt(j));
                break;
            }
        }
    }
    
    Queue<Character> queue = new LinkedList<>();
    
    // add all characters with 0 indegrees into queue
    for(char c : map.keySet()){
        if(indegrees[c - 'a'] == 0){
            queue.add(c);
        }
    }
    
    StringBuilder result = new StringBuilder();
    while(!queue.isEmpty()){
        char c = queue.poll();
        
        result.append(c);
        List<Character> list = map.get(c);
        for(Character p : list){
            indegrees[p - 'a']--;
            if(indegrees[p - 'a'] == 0){
                queue.add(p);
            }
        }
    }
    
    if(result.toString().length() != map.size()) return "";
    
    return result.toString();
  }
}
