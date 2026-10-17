/**
 * Time Complexity:  * O(N * L) to build graph and O(V + E ) to traverse the graph. (N is number of words, L is average word length, V Vertices and E Edges)
 * Space Complexity: O(V + E)
 */
class Solution {
    int[] indegree = new int[26];

    public String alienOrder(String[] words) {

        Map<Character, Set<Character>> dependency = findDependency(words);
        if (dependency == null)
            return "";
        String result = sortTopologically(dependency);
        return result;

    }

    Map<Character, Set<Character>> findDependency(String[] words) {
        Map<Character, Set<Character>> dependency = new HashMap<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (!dependency.containsKey(word.charAt(i))) {
                    dependency.put(word.charAt(i), new HashSet<>());
                }
            }

        }

        for (int i = 0; i < words.length - 1; i++) {
            String fWord = words[i];
            String sWord = words[i + 1];

            boolean doesCharMatch = true;
            int indexOfChar = 0;
            int lengthFWord = fWord.length();
            int lengthSWord = sWord.length();

            while (doesCharMatch && indexOfChar < lengthFWord && indexOfChar < lengthSWord) {
                if (fWord.charAt(indexOfChar) != sWord.charAt(indexOfChar)) {
                    doesCharMatch = false;
                } else {
                    indexOfChar++;
                }
            }

            if (!doesCharMatch) {
                if (!dependency.get(fWord.charAt(indexOfChar)).contains(sWord.charAt(indexOfChar))) {
                    dependency.get(fWord.charAt(indexOfChar)).add(sWord.charAt(indexOfChar));
                    indegree[sWord.charAt(indexOfChar) - 'a'] += 1;
                }
            } else if (!fWord.equals(sWord) && fWord.startsWith(sWord)) {
                return null;
            }
        }
        int[] indegreeDup = indegree;
        return dependency;
    }

    String sortTopologically(Map<Character, Set<Character>> dependency) {
        StringBuilder result = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (char key : dependency.keySet()) {
            if (indegree[key - 'a'] == 0) {
                queue.offer(key);
                result.append(key);
            }
        }

        while (!queue.isEmpty()) {
            char out = queue.poll();
            if (dependency.containsKey(out)) {
                for (char ch : dependency.get(out)) {
                    indegree[ch - 'a']--;
                    if (indegree[ch - 'a'] == 0) {
                        queue.offer(ch);
                        result.append(ch);
                    }
                }
            }

        }

        if (dependency.size() == result.length()) {
            return result.toString();
        }
        return "";
    }
}