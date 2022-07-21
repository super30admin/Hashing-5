
class Solution {

    //Time Complexity : 0(m*n) where m is the length of the array and n is the average length of the words in it. This time is mainly for building the graph and is the higher order term.
    //Space Complexity: 0(l) where l is the size of the list in hashmap. I won't consider other spaces as hashmap can have at max 26 character which is constant and my queue also can have only 26 characters. So the only space is occupied by list of character in the map


    int [] indegrees;   //indegrees array to store the incoming edges to a node. It helps in detecting a cycle as well
    HashMap<Character, List<Character>> map;//a hashmap to store the mapping or the edges from one character to another

    public String alienOrder(String[] words) {
        if(words == null || words.length == 0){
            return "";
        }

        indegrees = new int[26];    //the indegrees array can have 26 chars at max so 26 indices representing 26 chars
        map = new HashMap<>();
        Queue<Character> q = new LinkedList<>();    //a queue to carry out a BFS to check whether there is a valid order or not
        StringBuilder sb = new StringBuilder(); //a string builder to store the result or the order

        buildgraph(words);  //I call the buildgraph method to build the graph of characters and incoming and outgoing edges

        for(char c : map.keySet()){ //I iterate over my keyset and add all the character that have indegrees 0 to my queue as chars with indegrees 0 means they are not dependent on others and are a perfect place to start my BFS from
            if(indegrees[c - 'a'] == 0){    //if indegrees is 0, means I can add that to my queue for further processing of its edges
                q.add(c);
                sb.append(c);   //as well as add it to my result
            }
        }

        while(!q.isEmpty()){    //I carry out a BFS then
            char curr = q.poll();   //get the 1st character
            List<Character> children = map.get(curr);   //get the list of character dependent on the character or nodes having edges from that particular character
            if(children != null){   //their might come a time when a character with indegree 0 might not have any dependent character. So I put this condition
                for(char child : children){ //navigating through all of it's children
                    indegrees[child - 'a']--;   //and subtracting the value of indegrees for that particular character since I have visited that
                    if (indegrees[child - 'a'] == 0){   //If the indegrees is 0, means I can add it to my queue as well as my result and it does not have a cycle or conflict
                        q.add(child);
                        sb.append(child);
                    }
                }
            }
        }

        if(sb.length() != map.size()){  //finally, if the size of the map and size of the result is not same, then it means I don't have a valid order. So i return an empty string
            return "";
        }
        return sb.toString();   //otherwise I return the result which contains the valid order
    }

    public void buildgraph(String [] words){

        for(String word: words){    //I go over the words and put it in the hashmap
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);    //also I get the 1st character out of each word
                if(!map.containsKey(c)){
                    map.put(c, new ArrayList<>());  //and create an empty array list to store all of it's edges into it.
                }
            }
        }
        for(int i = 0; i < words.length - 1; i++){  //Now I compare 2 words in order to find the order
            String first = words[i];    //I take 1st and 2nd word and so on
            String second = words[i + 1];
            int m = first.length(); //I get the length
            int n = second.length();
            if(first.startsWith(second) && m > n){  //there might be a case when 1st word is apple, and 2nd word is app. In this case 1st 3 chars are matching, but after that there is no particular order. So i clear out my map as that will help me return an empty string as there is no order associated to it
                map.clear();
                return;
            }
            for(int j = 0; j < m && j < n; j++){    //I go over the length of both of my strings
                char out = first.charAt(j); //get the characters of both the strings
                char in = second.charAt(j);
                if(out != in){  //if it is not equal
                    map.get(out).add(in);   //I add the dependent node to the character it is dependent upon
                    indegrees[in - 'a']++;  //also increment the value by 1 of that character in my indegrees array
                    break;  //I break because I am not concerned about the relative order after that and move to my other pair of words
                }
            }
        }
    }
}