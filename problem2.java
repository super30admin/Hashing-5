import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// TIme Complexity: O(V+E)+(n*l)
// Space Complexity: O(n)
public class problem2 {
    HashMap<Character, HashSet<Character>> map;
    int[] inDegree;
  //  boolean[] visited;
    public String alienOrder(String[] words) {
        //int k=26;
        this.map = new HashMap<>();
        this.inDegree= new int[26];

        buildGraph(words);
        if (map.size()==0)
        {return "";}
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for(char key: map.keySet()){
            if (inDegree[key-'a'] == 0){
                queue.add(key);
                sb.append(key);
            }
        }
        while(!queue.isEmpty()){
            char key = queue.poll();
            for (char c: map.get(key)){
                inDegree[c-'a']--;
                if (inDegree[c-'a'] == 0){
                    queue.add(c);
                    sb.append(key);

                }
            }

        }
        if (sb.length()!=map.size())
        {
            return "";
        }
        return sb.toString();
    }
    private void buildGraph(String[] words) {
        for (String word : words) {
            for (char c : word.toCharArray()) {
                map.put(c, new HashSet<>());
            }
        }
        for (int i=0;i<words.length-1;i++) {
            String first= words[i];
            String second= words[i+1];

            if (first.startsWith(second) && first.length()> second.length()) {
                map.clear();
                return;
            }

            for (int j=0;j<first.length() && j< second.length();j++) {
                char fChar= first.charAt(j);
                char sChar= second.charAt(j);

                if (fChar!=sChar) {
                    HashSet<Character> set = map.get(fChar);
                    if (!set.contains(sChar)) {
                        set.add(sChar);
                        inDegree[sChar-'a']++;
                    }
                    break;
                }
            }
        }
    }
}
