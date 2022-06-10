//TC : O(N * K) N = no. of words and K = avg.length of word

//SC : O(1) (Beacuse map and degree will be having max 26 characters, queue will also be having max 26 characters)

class Solution {
    int[] degree;
    HashMap<Character, Set<Character> > map;
    public String alienOrder(String[] words) {
        if(words == null || words.length == 0)  return "";
        
        map = new HashMap<>();
        degree = new int[26];
        createGraph(words);
        
        StringBuilder result = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        
        for(Character ch : map.keySet()){
            if(degree[ch - 'a'] == 0){
                queue.add(ch);
            }
        } // Initiali queue in which chars are there having degree = 0
        
        if(queue.isEmpty()) return "";
        
        while(!queue.isEmpty()){
            char ch = queue.poll();
            result.append(ch); // Append char nin result/order of language
            Set<Character> set = map.get(ch);
            // For dependents of character from map, update the degree and add it to queue if it becomes 0
            for(Character ce : set){
                degree[ce - 'a']--;
                if(degree[ce - 'a'] == 0)
                    queue.add(ce);
            }
        }
        
        if(result.length() != map.size())   return ""; //If all characters are not there in order, return empty string
        return result.toString();
        
    }
    public void createGraph(String[] words){
        for(String s : words){
            for(int i = 0; i< s.length(); i++){
                if(!map.containsKey(s.charAt(i))){
                    map.put(s.charAt(i), new HashSet<>());
                }
            }
        }// Now map is ready to put the dependencies of characters
        
        //Start comparing 2 words
        for(int i = 0; i< words.length - 1; i++){
            String s1 = words[i];
            String s2 = words[i+1];
            
            if(s1.length() != s2.length() && s1.startsWith(s2)){
                map = new HashMap<>();
                break;
            }
            //If s1 starts from s2, which is not valid input so order will be empty string
           
           for(int k = 0; k< s1.length() && k < s2.length() ; k++){
                char fc = s1.charAt(k);
                char sc = s2.charAt(k);
               
               if(fc != sc){
                   Set<Character> li = map.get(fc);
                   
                   if(!li.contains(sc)){
                       map.get(fc).add(sc);
                       degree[sc - 'a']++;
                       
                   }
                   break;
                   
                   //Whenever mismatch happends, we should stop building the edges for that character
               }
           }
            
            
        }
    }
}