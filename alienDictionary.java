/*
Problem: https://leetcode.com/problems/alien-dictionary/

There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.
You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted lexicographically by the rules of this new language.
Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.
A string s is lexicographically smaller than a string t if at the first letter where they differ, the letter in s comes before the letter in t in the alien language. If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.

Example 1:
Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"

Example 2:
Input: words = ["z","x"]
Output: "zx"

Example 3:
Input: words = ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "".

*/
class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0)
            return "";
        
        HashMap<Character, List<Character>> adjacencyList = new HashMap<>();
        int indegrees[] = new int[26];
        Queue<Character> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        
        if (!buildGraphAndIndegrees(words, adjacencyList, indegrees)) {
            return "";
        }
        
        for (char c : adjacencyList.keySet()) {
            if (indegrees[c - 'a'] == 0) {
                queue.add(c);
                sb.append(c);
            }
        }
        
        while (!queue.isEmpty()) {
            char c = queue.poll();
            List<Character> children = adjacencyList.get(c);
            
            if (children != null) {
                for (char child : children) {
                    --indegrees[child - 'a'];
                    
                    if (indegrees[child - 'a'] == 0) {
                        queue.offer(child);
                        sb.append(child);
                    }
                }
            }
        }
        
        return (sb.length() == adjacencyList.size() ? sb.toString() : "");
    }
    
    private boolean buildGraphAndIndegrees(String words[], HashMap<Character, List<Character>> adjacencyList,int indegrees[]) {
        
        for (String word : words) {
            for (int i = 0; i < word.length(); ++i) {
                if (!adjacencyList.containsKey(word.charAt(i))) {
                    adjacencyList.put(word.charAt(i), new ArrayList<>());
                }
            }
        }
        
        for (int i = 1; i < words.length; ++i) {
            String word1 = words[i - 1];
            String word2 = words[i];
            int len1 = word1.length();
            int len2 = word2.length();
            int w1 = 0, w2 = 0;
            
            while (w1 < len1 && w2 < len2) {
                if (word1.charAt(w1) != word2.charAt(w2)) {
                    adjacencyList.get(word1.charAt(w1)).add(word2.charAt(w2));
                    ++indegrees[word2.charAt(w2) - 'a'];
                    break;
                }
                ++w1; ++w2;
            }
            
            // The case where we are comparing word1 = "apple" with word2 = "app"
            if (w2 == len2 && len1 > len2) {
                return false;
            }
        }
        
        return true;
    }
}