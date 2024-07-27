import java.util.*;

class Solution {
    public String alienOrder(String[] words) {
        // Tc: O(N) where N = tot num of characters across all words
        // Sc: O(C+E) where C = no of characters unique and E be num of edges
        Map<Character, Set<Character>> adjList = new HashMap<>();
        Map<Character, Integer> counts = new HashMap<>();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                counts.put(c, 0);
                adjList.putIfAbsent(c, new HashSet<>());
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    char out = word1.charAt(j), in = word2.charAt(j);
                    if (adjList.get(out).add(in)) {
                        counts.put(in, counts.get(in) + 1);
                    }
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (char c : counts.keySet()) {
            if (counts.get(c) == 0) {
                queue.add(c);
            }
        }

        while (!queue.isEmpty()) {
            char current = queue.poll();
            sb.append(current);
            for (char next : adjList.get(current)) {
                counts.put(next, counts.get(next) - 1);
                if (counts.get(next) == 0) {
                    queue.add(next);
                }
            }
        }

        if (sb.length() < counts.size()) {
            return "";
        }

        return sb.toString();
    }
}