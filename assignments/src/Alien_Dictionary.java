// Approach: Topological Sort on Graphs
// Time: time for (building graph + DFS) = O(nl + C) = O(nl) building graph only
// Space: O(1)

import java.util.*;

class Alien_Dictionary {
    public String alienOrder(String[] words) {

        boolean[][] adjMatrix = new boolean[26][26];
        Map<Character, Integer> inDegree = new HashMap<>();

        buildGraph(words, adjMatrix, inDegree);

        Queue<Character> q = new LinkedList<>();

        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                q.add(c);
            }
        }

        StringBuilder order = new StringBuilder();

        while (!q.isEmpty()) {
            char c = q.remove();
            order.append(c);

            for (int i = 0; i<26; i++) {
                if (adjMatrix[c-'a'][i]) {
                    char nChar = (char)(i+'a');
                    inDegree.put(nChar, inDegree.get(nChar)-1);

                    if (inDegree.get(nChar) == 0) {
                        q.add(nChar);
                    }
                }
            }
        }

        if (inDegree.size() != order.length()) {
            order = new StringBuilder();
        }
        return order.toString();
    }

    private void buildGraph(String[] words, boolean[][] adjMatrix, Map<Character, Integer> inDegree) {

        for (String word : words) {

            for (int i = 0; i<word.length(); i++) {
                inDegree.put(word.charAt(i), 0);
            }
        }

        for (int i = 1; i<words.length; i++) {
            String a = words[i-1], b = words[i];
            int j = 0;

            while (j<a.length() && j<b.length()) {
                char aChar = a.charAt(j), bChar = b.charAt(j);
                int aCharIndx = aChar - 'a', bCharIndx = bChar - 'a';

                if (aChar != bChar) {
                    if (!adjMatrix[aCharIndx][bCharIndx]) {
                        adjMatrix[aCharIndx][bCharIndx] = true;
                        inDegree.put(bChar, inDegree.getOrDefault(bChar,0)+1);
                    }
                    break;
                }
                j++;
            }

            // For cases: wars, war
            if (j==b.length() && a.length()>j) {
                inDegree.clear();
            }
        }
    }
}