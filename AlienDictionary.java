import java.util.*;

public class AlienDictionary {

    // TC: O(N * K) where N is number of words and K is max length of word
    // SC: O(1)
    public String alienOrder(String[] words) {
        Map<Character, List<Character>> adjacencyList = new HashMap<>();
        int[] indegree = new int[26];
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                adjacencyList.put(ch, new ArrayList<>());
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            if (w1.length() > w2.length() && w1.startsWith(w2)) {
                return "";
            }
            for (int j = 0; j < w1.length() && j < w2.length(); j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    adjacencyList.get(w1.charAt(j)).add(w2.charAt(j));
                    indegree[w2.charAt(j) - 'a']++;
                    break;
                }
            }
        }
        Queue<Character> queue = new LinkedList<>();
        for (char key : adjacencyList.keySet()) {
            if (indegree[key - 'a'] == 0){
                queue.add(key);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char curr = queue.poll();
            sb.append(curr);
            for (char neighbor : adjacencyList.get(curr)) {
                indegree[neighbor - 'a']--;
                if (indegree[neighbor - 'a'] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        if (sb.length() < adjacencyList.size()) {
            return "";
        }
        return sb.toString();
    }
}
