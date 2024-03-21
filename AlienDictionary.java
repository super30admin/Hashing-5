/*
* Did this code successfully run on Leetcode : YES
* 
* Any problem you faced while coding this : NO
* 
* Time Complexity: O(n*k + 26 characters in queue)
    n = total words
    k = avg length of each word
* 
* Space Complexity: O(1)
    hashmap with 26 character keys and indegrees array with 26 elements.
* 
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AlienDictionary {
    class Solution {
        int[] indegrees;

        HashMap<Character, List<Character>> adjList;

        private void populateAdjacencyList(String[] words) {
            // populating the adj list keys because we are stopping whenever there is a
            // breach
            // this might affect the indegree calculation while applying topological sort
            for (int index = 0; index < words.length; index++) {
                for (int i = 0; i < words[index].length(); i++) {
                    char ch = words[index].charAt(i);

                    if (!adjList.containsKey(ch)) {
                        adjList.put(ch, new ArrayList<>());
                    }
                }
            }

            for (int index = 0; index < words.length - 1; index++) {
                String word1 = words[index];

                String word2 = words[index + 1];

                // apple and app -> invalid order, so clear the map with added charactes
                // so that queue dont find any indegree chars and return ""
                if (word1.startsWith(word2) && word1.length() > word2.length()) {
                    adjList.clear();
                    return;
                }

                for (int wordIndex = 0; wordIndex < word1.length() && wordIndex < word2.length(); wordIndex++) {
                    char word1Char = word1.charAt(wordIndex);

                    char word2Char = word2.charAt(wordIndex);

                    // breach
                    if (word1Char != word2Char) {
                        char preceedingChar = word1Char;

                        char nextChar = word2Char;

                        adjList.get(preceedingChar).add(nextChar);

                        indegrees[nextChar - 'a']++;

                        break;
                    }
                }
            }
        }

        public String alienOrder(String[] words) {
            indegrees = new int[26];

            adjList = new HashMap<>();

            populateAdjacencyList(words);

            Queue<Character> queue = new LinkedList<>();

            StringBuilder str = new StringBuilder();

            for (char key : adjList.keySet()) {
                if (indegrees[key - 'a'] == 0) {
                    queue.add(key);
                    str.append(key);
                }
            }

            while (!queue.isEmpty()) {
                char ch = queue.poll();

                // process edges of ch
                for (char node : adjList.get(ch)) {
                    indegrees[node - 'a']--;

                    if (indegrees[node - 'a'] == 0) {
                        queue.add(node);
                        str.append(node);
                    }
                }
            }

            if (str.length() == adjList.size()) {
                return str.toString();
            }

            return "";
        }
    }
}
