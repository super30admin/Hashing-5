// Time Complexity : O(N*k) to build the graph and O(v + e) to perform BFS
// Space Complexity : O(v+e)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

/*
I create an adjacency list of all the connected components present in the strings. The adjacency list is a hashmap of characters to set of characters that lexicographically occur after it. 
I compare each word with it's subsequent neighbour and check for the first character in both words that are differing from each other - this gives us the information that the
character of the first word occurs before the character of the second word, which means there is a link from the first character to the second character. I update the adjacency list
accordingly and increment indegree of the second character. After this I perform topological sort on the graph formed and keep track of all the characters which have been visited and append
them to the result. If all characters have been visited, I return the result if not I return an empty string.   
*/

public class Solution {
    public string AlienOrder(string[] words) {
        Dictionary<char, HashSet<char>> adjacencyList = new();

        for(int i = 0; i < words.Length; i++)
        {
            for(int j = 0; j < words[i].Length; j++)
            {
                if(!adjacencyList.ContainsKey(words[i][j]))
                {
                    adjacencyList[words[i][j]] = new();
                }
            }
        }


        int[] indegree = new int[26];
        StringBuilder order = new();

        BuildOrderGraph(words, adjacencyList, indegree);

        if(adjacencyList.Count == 0)
        {
            return "";
        }

        // BFS
        Queue<char> q = new();
        
        foreach(char key in adjacencyList.Keys)
        {
            if(indegree[key-'a'] == 0)
            {
                q.Enqueue(key);
            }
        }

        while(q.Count != 0)
        {
            char ch = q.Dequeue();
            order.Append(ch);

            HashSet<char> connectedComponentSet = adjacencyList[ch];
            foreach(char component in connectedComponentSet)
            {
                indegree[component-'a'] -= 1;
                if(indegree[component-'a'] == 0)
                {
                    q.Enqueue(component);
                }
            }
        }

        Console.WriteLine(order.ToString());

        return adjacencyList.Count == order.Length ? order.ToString() : "";
    }

    public void BuildOrderGraph(string[] words, Dictionary<char, HashSet<char>> adjacencyList ,int[] indegree)
    {
        for(int i = 0; i < words.Length - 1; i++)
        {
            string firstWord = words[i];
            string secondWord = words[i+1];

            if(firstWord.StartsWith(secondWord) && firstWord.Length > secondWord.Length)
            {
                adjacencyList.Clear();
                return;
            }
            
            for(int j = 0; j < firstWord.Length && j < secondWord.Length; j++)
            {
                char firstChar = firstWord[j];
                char secondChar = secondWord[j];
                
                if(firstChar != secondChar)
                {
                    if(!adjacencyList[firstChar].Contains(secondChar))
                    {
                        adjacencyList[firstChar].Add(secondChar);
                        indegree[secondChar - 'a'] += 1;
                    }

                    break;
                }
            }
        }
    }
}