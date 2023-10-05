import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// Time Complexity : O(V+E)
// Space Complexity : O(V+E)
// Did this code successfully run on Leetcode : Yes


public class AlienDictionary {
    HashMap<Character, HashSet<Character>> map;
    int[] indegrees;
    public String alienOrder(String[] words) {
        map = new HashMap<>();
        indegrees = new int[26];

        // build the graph
        buildGraph(words);

        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        for(char c: map.keySet()){
            if(indegrees[c - 'a'] == 0){
                q.add(c);
                sb.append(c);
            }
        }
        while(!q.isEmpty()){
            char currChar = q.poll();
            HashSet<Character> dependencySet = new HashSet<>(map.get(currChar));

            for(char c : dependencySet){
                indegrees[c - 'a']--;
                if(indegrees[c - 'a'] == 0){
                    q.add(c);
                    sb.append(c);
                    if(sb.length() == map.size()) return sb.toString();
                }
            }
        }
        if(sb.length() == map.size()) return sb.toString();
        return "";
    }

    private void buildGraph(String[] words){
        for(String word: words){
            for(char c: word.toCharArray()){
                if(!map.containsKey(c)){
                    map.put(c, new HashSet<>());
                }
            }
        }

        for(int i=0; i<words.length-1; i++){
            String first = words[i];
            String second = words[i+1];

            if(first.length() != second.length() && first.startsWith(second)){
                map.clear();
                break;
            }
            for(int k=0; k<first.length() && k<second.length(); k++){
                char fChar = first.charAt(k);
                char sChar = second.charAt(k);
                if(fChar != sChar){
                    HashSet<Character> set = map.get(fChar);
                    if(!set.contains(sChar)){
                        set.add(sChar);
                        indegrees[sChar-'a']++;
                    }
                    break;
                }
            }
        }
    }
}