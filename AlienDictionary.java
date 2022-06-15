import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//Time Complexity: O(n.k); where n is number of words and k is avg length of a word.
//Space Complexity: O(1) since map and queue will at most have 26 chars
public class AlienDictionary { 
	/**Approach: Graph + BFS**/
	HashMap<Character, HashSet<Character>> map;
    int[] inDegrees;
    public String alienOrder(String[] words) {
        map = new HashMap<>();
        inDegrees = new int[26];      
        buildGraph(words);           
        //BFS
        //Add all the independent nodes originally to queue to start.
        Queue<Character> q= new LinkedList<>(); 
        StringBuilder sb = new StringBuilder();
        for(Character key: map.keySet()){ //O(1)
            if(inDegrees[key - 'a'] == 0){
                q.add(key);
            }
        }
        //If no independent node, no solution possible.
        if(q.isEmpty()) return "";
        //Else Process the queue
        while(!q.isEmpty()){ 
            char c = q.poll();
            sb.append(c);
            //Reduce indegrees of its dependents
            for(char edge: map.get(c)){
                inDegrees[edge - 'a']--;
                //Add to queue, if any of these becomes independent.
                if(inDegrees[edge - 'a'] == 0){
                    q.add(edge);
                }
            }
        }
        if(sb.length() != map.size()) return "";//All chars not processed.     
        return sb.toString();
    }
    private void buildGraph(String[] words){
        //Fill adjacency map with empty hashset for all the chars
        for(String word: words){ //O(n)
            for(char c: word.toCharArray()){
                map.putIfAbsent(c, new HashSet<>());
            }            
        }
        //Fill adjacency map and inDegrees
        for(int i=0; i<words.length -1; i++){ //O(n)
            String first = words[i]; int fl = first.length();
            String second = words[i+1]; int sl = second.length();             
            //To handle edge case Apple, App as its not sorted at first.
            if(fl!=sl && first.startsWith(second)) {
                map = new HashMap();
                break;
            } 
            //else fill the map and array
            for(int j=0; j<fl && j<sl; j++){ //O(k)
                char fChar = first.charAt(j);
                char sChar = second.charAt(j);
                if(fChar != sChar){
                    //increment indegrees and avoid duplicate increment due to same edge from independent to dependent node
                    Set<Character> set = map.get(fChar);                    
                    if(!set.contains(sChar)) inDegrees[sChar - 'a']++;   
                    set.add(sChar);
                    break;
                }                
            }
        }        
    }
	
	/** Driver code to test above **/
	public static void main (String[] args) {	
		AlienDictionary ob  = new AlienDictionary();	
		String[] words = {"apple","app"};//{"wrt","wrf","er","ett","rftt"};
		
		System.out.println("Sorted unique letters in alien language: "+ ob.alienOrder(words));         
	}	
}
