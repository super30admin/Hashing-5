// Time Complexity : O(nk) : Traversing all the characters in each word (k characters, n words)
// Space Complexity : O(1) Hashmap : 26 characters and queue takes o(1)(25 at most independent nodes)

// Given words, find the order
class Solution {
    public boolean alienOrder(String[] words) {
        
        // dependency array : Adjacent list
        HashMap<Character, HashSet<Character>> map = new HashMap<>();

        //create the graph for this list
        createGraph(map, words, indegree);
      
        // Push the independent nodes into the queue

        // indegree array will depict which are independent nodes
        int[] indegree = new int[26];
        StringBuilder result = new StringBuilder();

        Queue<Character> queue = new LinkedList<>();

        for (char c : map.keySet() ){

            if (indegree[c - 'a'] ==0 ){

                // push to queue if it is independent node
                queue.add(c);
                result.append(c);
            }

        }


        // BFS
        while ( !queue.isEmpty()){


            // poll the root 
            char curr = queue.poll();

            // From the Hashset in the adjacency list of that character 
            for (char c : map.get(curr)){

                // reduce the dependent nodes indegree
                indegree[c - 'a'] --;

                //check if they are independent nodes

                if (indegree[c- 'a'] == 0){

                    qeueu.push(c);
                    result.append(c);
                }
            }

            // if the length is not matching , inputs are not valid
            if (result.toString != map.size()){
                return "";
            }

            return result.toString();
        }
    }

    private void createGraph(HashMap<Character, HashSet<Character> map, String[] words, int[] indegree){

        // Hashmap updation
        for (String word: words ){

            // all unique characters update it to the empty first and then dependent character
            for (int i = 0; i < words.length ; i++){
                // Put character and its list
                map.put(word.CharAt(i), new HashSet<>());
            }

        }

        // iterate through words
        for (int i = 0; i < words.length ; i++){

            String first = words[i];
            String second = words[i + 1];


            // Edge case : abc ab : Input itself is invalid
            if (first.startsWith(second)  && first.length > second.length) {
                map.clear(); // remove the map so that it returns " " string
                return;
            }
            // iterate through characters
            for (int j = 0; j < first.length() && second.length() ; i++){

                char firstCh = first.CharAt(i);
                char secondCh = second.CharAt(j);

                if (map.get(firstCh).contains(secondCh)){

                    // Check if exists
                    map.get(firstCh).add(secondCh);

                    // update indegree 
                    // if it is dependent on any character 
                    // add in that indegree as 1.
                    indegree[secondCh - 'a']++;
                }

                break; // no need to check the next characters as first mismatched character found
            }



        }

    }
}