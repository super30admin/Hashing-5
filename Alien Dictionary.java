// 269. Alien Dictionary

// There is a new alien language that uses the English alphabet. However, the order of the letters is unknown to you.
// You are given a list of strings words from the alien language's dictionary. Now it is claimed that the strings in words are sorted lexicographically by the rules of this new language.
// If this claim is incorrect, and the given arrangement of string in words cannot correspond to any order of letters, return *™
// Otherwise, return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there are multiple solutions, return any of them.
// Example 1:
// Input: words = ["wrt", "wrf", "er","ett" ,"rftt"]
// Output: "wertf"
// Example 2:
// Input: words＝【"z","x"］
// Output: "zx"
// Example 3:
// Input: words＝【"z","x","z"］
// Output: "*
// Explanation: The order is invalid, so return "

// //bfs tc- O(v+ E) sc- O(V+E)
// public class Main {
//     HashMap<Character, HashSet<Character>> map;
//     int[] indegrees;
    
//     public String alienOrder(String[] words){
//         this.map = new HashMap<>();
//         this.indegrees = new int[26];
        
//         buildGraph(words);
//         if(map.size() == 0) return "";//in case of {"apple", "app"} invalid dicstionary
        
//         StringBuilder sb = new StringBuilder();
//         Queue<Character> q = new LinkedList<>();
        
//         for(char c: map.keySet()){
//             if(indegrees[c-'a'] == 0){
//                 q.add(c);
//                 sb.append(c);
//             }
//         }
        
//         while(!q.isEmpty()){
//             char curr = q.poll();
//             HashSet<Character> neighbours = map.get(curr);
//             for(char ne: neighbours){
//                 indegrees[ne - 'a']--;
//                 if(indegrees[ne - 'a'] == 0){
//                     q.add(ne);
//                     sb.append(ne);
//                 }
//             }
//         }
        
//         if(sb.length() == map.size()) return sb.toString();
//         return "";
//     }
    
//     private void buildGraph(String[] words){//creating adjacency matrix 
//         for(String word: words){
//             for(char c: word.toCharArray()){
//                 if(!map.containsKey(c)){
//                     map.put(c, new HashSet<>());
//                 }   
//             }
//         }
        
//         for(int i = 0; i < words.length -1; i++){
//             String first = words[i];
//             String second = words[i+1];
            
//             if(first.length() > second.length() && first.startsWith(second)){//in case of {"apple", //"app"} invalid dicstionary
//                 map.clear();
//                 break;
//             }
            
//             for(int k = 0; k < first.length() && k < second.length(); k++){
//                 char fChar = first.charAt(k);
//                 char sChar = second.charAt(k);
                
//                 if(fChar != sChar){
//                     HashSet<Character> set = map.get(fChar);
//                     if(!set.contains(sChar)){
//                         set.add(sChar);//sChar is dependent on fChar so adding schar to fchar set
//                         indegrees[sChar - 'a']++;//increamenting indegrees array index value
//                     }
//                     break;
//                 }
//             }
//         }
//     }
    
    
//     public void main(String[] args) {
        
//         String[] words = new String[]{"z","x"};
//         System.out.println("Hello World!");
//         System.out.println(alienOrder(words));
        
        
//     }
    
// }



//dfs tc- O(v+ E) sc- O(V+E)
public class Main {
    HashMap<Character, HashSet<Character>> map;
    boolean[] path;
    boolean[] visited;
    StringBuilder sb;
    
    public String alienOrder(String[] words){
        this.map = new HashMap<>();
        this.path = new boolean[26];
        this.visited = new boolean[26];
        
        buildGraph(words);
        if(map.size() == 0) return "";//in case of {"apple", "app"} invalid dicstionary
        
        this.sb = new StringBuilder();
        
        for(char c: map.keySet()){
            if(!visited[c - 'a'] && hasCycle(c)){
                return "";
            }
        }
       
        return sb.reverse().toString();
    }
    
    private boolean hasCycle(char c){
        if(path[c-'a']) return true;
        if(visited[c-'a']) return false;
        
        path[c-'a'] = true;
        
        HashSet<Character> neighbours = map.get(c);
        for(char ne : neighbours){
            if(!visited[ne-'a'] && hasCycle(ne)){
                return true;
            }
        }
        
        //backtrack
        path[c-'a'] = false;
        visited[c-'a'] = true;
        
        sb.append(c);
        
        return false;
    }
    
    private void buildGraph(String[] words){//creating adjacency matrix 
        for(String word: words){
            for(char c: word.toCharArray()){
                if(!map.containsKey(c)){
                    map.put(c, new HashSet<>());
                }   
            }
        }
        
        for(int i = 0; i < words.length -1; i++){
            String first = words[i];
            String second = words[i+1];
            
            if(first.length() > second.length() && first.startsWith(second)){//in case of {"apple", //"app"} invalid dicstionary
                map.clear();
                break;
            }
            
            for(int k = 0; k < first.length() && k < second.length(); k++){
                char fChar = first.charAt(k);
                char sChar = second.charAt(k);
                
                if(fChar != sChar){
                    HashSet<Character> set = map.get(fChar);
                    if(!set.contains(sChar)){
                        set.add(sChar);//sChar is dependent on fChar so adding schar to fchar set
                       
                    }
                    break;
                }
            }
        }
    }
    
    
    public void main(String[] args) {
        
        String[] words = new String[]{"z","x"};
        System.out.println("Hello World!");
        System.out.println(alienOrder(words));
        
        
    }
    
}