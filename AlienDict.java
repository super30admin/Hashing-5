class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                inDegree.putIfAbsent(c, 0);
            }
        }

        for (int i = 1; i < words.length; i++) {
            String word1 = words[i - 1];
            String word2 = words[i];
            int minLength = Math.min(word1.length(), word2.length());

            for (int j = 0; j < minLength; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);

                if (c1 != c2) {
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    break;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();

        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                queue.offer(c);
            }
        }

        while (!queue.isEmpty()) {
            char c = queue.poll();
            result.append(c);

            for (char next : graph.getOrDefault(c, new HashSet<>())) {
                inDegree.put(next, inDegree.get(next) - 1);
                if (inDegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }

        if (result.length() != inDegree.size()) {
            return "";
        }

        return result.toString();
    }
}

