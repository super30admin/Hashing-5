// Time Complexity : O(m*n), m -> Number of words, n -> Average length of each word
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem2;

public class VerifyAlienDictionary {
	public boolean isAlienSorted(String[] words, String order) {
		if (words == null || words.length < 2) {
			return true;
		}

		int[] orderMap = new int[26];

		for (int i = 0; i < order.length(); i++) {
			orderMap[order.charAt(i) - 'a'] = i;
		}

		for (int i = 0; i < words.length - 1; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				if (j >= words[i + 1].length()) {
					return false;
				}
				if (words[i].charAt(j) != words[i + 1].charAt(j)) {
					if (orderMap[words[i].charAt(j) - 'a'] > orderMap[words[i + 1].charAt(j) - 'a']) {
						return false;
					}
					break;
				}
			}
		}

		return true;
	}

	public static void main(String[] args) {
		VerifyAlienDictionary obj = new VerifyAlienDictionary();
		String[] words = { "hello", "leetcode" };
		String order = "hlabcdefgijkmnopqrstuvwxyz";

		System.out.println("Are the given words in order? " + (obj.isAlienSorted(words, order) ? "Yes" : "No"));
	}
}
