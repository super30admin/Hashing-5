class Solution {

    HashMap<Character, List<Character>> map;
    int[] indegree;

    public String alienOrder(String[] words) {
        
        if(words == null || words.length == 0) return "";

        map = new HashMap();

        // Only lower case letters
        indegree = new int[26];

        buildGraph(words);

        Queue<Character> queue = new LinkedList();
        StringBuilder result = new StringBuilder();

        // Check for elements in hash map which has a indegree 0 and add them to queue
        for(char key : map.keySet())
        {
            if(indegree[key - 'a'] == 0)
            {
                queue.add(key);
                result.append(key);
            }
        }

        // None of the characters had an indegree 0
        if(queue.size() == 0) return "";

        while(!queue.isEmpty())
        {
            char curr = queue.poll();

            List<Character> edges = map.get(curr);

            if(edges == null) continue;

            for(char edge : edges)
            {
                indegree[edge - 'a']--;

                if(indegree[edge - 'a'] == 0)
                {
                    queue.add(edge);
                    result.append(edge);
                }
            }
        }

        // This happens when we still have some characters in indegree which never became 0, this denotes that our graph had a cycle
        if(result.length() != map.size()) return "";

        return result.toString();
    }

    private void buildGraph(String[] words)
    {
        // Traverse the array first to create entries in map for characters as key and values as empty array list
        for(String word : words)
        {
            for(int j = 0; j < word.length(); j++)
            {
                char c = word.charAt(j);

                if(!map.containsKey(c)) map.put(c, new ArrayList());
            }
        }

        // Now traverse the array once again and update the values of hashmap and indegrees array
        // For example if t comes before e then we go to the map key t and to its array list we add e and also we increase the 
        // value of e in indegree array because it is t -> e. t is an outgoing character and e is an incoming character
        for(int i = 0; i < words.length - 1; i++)
        {
            String first = words[i];
            String second = words[i + 1];

            int n = first.length(), m = second.length();

            // Assume we had first string as "abc" and second string as "ab", even now we have to return "" but the return type of func
            // is void what we do is just clear the map and return. This is because in the main function after traversing map
            // we are checking if queue is empty then we return ""

            if(first.startsWith(second) && n > m)
            {
                map.clear();
                return;
            }
            
            for(int j = 0; j < n && j < m; j++)
            {
                char outgoing = first.charAt(j);
                char incoming = second.charAt(j);

                if(outgoing != incoming)
                {
                    map.get(outgoing).add(incoming);
                    indegree[incoming - 'a']++;
                    break;
                }
            }
        }
    }
}