// Time Complexity : O(m*n), m -> Number of words, n -> Average length of each word
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AlienDictionary {
	Map<Character, Set<Character>> map;
	int[] indegrees;
	boolean flag;

	public String alienOrder(String[] words) {
		if (words == null || words.length == 0) {
			return "";
		}

		map = new HashMap<>();
		indegrees = new int[26];

		buildGraph(words);

		Queue<Character> queue = new LinkedList<>();
		StringBuilder sb = new StringBuilder();

		for (char ch : map.keySet()) {
			if (indegrees[ch - 'a'] == 0) {
				queue.add(ch);
				sb.append(ch);
			}
		}

		if (queue.size() == 0) {
			return "";
		}

		while (!queue.isEmpty()) {
			char ch = queue.poll();
			Set<Character> set = map.get(ch);

			for (char adj : set) {
				indegrees[adj - 'a']--;
				if (indegrees[adj - 'a'] == 0) {
					queue.add(adj);
					sb.append(adj);
				}
			}
		}

		return sb.length() != map.size() ? "" : sb.toString();
	}

	private void buildGraph(String[] words) {
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				char ch = words[i].charAt(j);
				if (!map.containsKey(ch)) {
					map.put(ch, new HashSet<>());
				}
			}
		}
		// Build Adjacency list
		flag = true;
		for (int i = 0; i < words.length - 1; i++) {
			String first = words[i];
			String second = words[i + 1];

			int m = first.length();
			int n = second.length();
			// Edge Case

			for (int j = 0; j < m && j < n; j++) {
				char out = first.charAt(j);
				char in = second.charAt(j);

				if (out != in) {
					if (map.get(out).add(in)) {
						indegrees[in - 'a']++;
					}
					flag = false;
					break;
				}
			}

			if (m > n && flag) {
				map = new HashMap<>();
				return;
			}
		}
	}

	public static void main(String[] args) {
		AlienDictionary obj = new AlienDictionary();
		String[] words = { "wrt", "wrf", "er", "ett", "rftt" };

		System.out.println("Order of words in the alien dictionary: \'" + obj.alienOrder(words) + "\'");
	}

}
