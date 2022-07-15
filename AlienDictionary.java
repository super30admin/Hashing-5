import java.util.*;

public class AlienDictionary {
    // TC : O(m * k) m => number of words k => average length of a word in words array
    // SC : O(1)
    int[] indegrees;
    Map<Character, List<Character>> map;
    public String alienOrder(String[] words){
        if(words == null || words.length == 0) return "";

        indegrees = new int[26];
        map = new HashMap<>();
        Queue<Character> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        buildGraph(words);

        for(char c : map.keySet()) {
            if(indegrees[c - 'a'] == 0){
                q.add(c);
                sb.append(c);
            }
        }

        while(!q.isEmpty()){
            char curr = q.poll();
            List<Character> children = map.get(curr);
            if(children != null){
                for(char child : children) {
                    indegrees[child - 'a']--;
                    if(indegrees[child - 'a'] == 0) {
                        q.add(child);
                        sb.append(child);
                    }
                }
            }

        }
        if(sb.length() != map.size()) return "";
        return sb.toString();
    }

    private void buildGraph(String[] words) {
        for(String word : words) {
            for(int i=0; i < word.length();i++) {
                char c = word.charAt(i);
                if(!map.containsKey(c)) {
                    map.put(c, new ArrayList<>());
                }
            }
        }

        for(int i=0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i + 1];
            int m = first.length();
            int n = second.length();

            if(first.startsWith(second) && m > n) { // this will handle -> ["apple", "app"]
                map.clear();
                return;
            }
            for(int j=0; j < m && j < n; j++) {
                char out = first.charAt(j);
                char in = second.charAt(j);
                if(out != in) {
                    map.get(out).add(in);
                    indegrees[in - 'a']++;
                    break;
                }
            }
        }
    }
}
