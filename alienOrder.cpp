// Time complexity: O(nl) where n is the number of words and l is the average length of each word
// Space complexity: O(nl) - the value of the dict might be longer - but still 26
//                    O(1) -> technically


/* 
This is a graph problem 
the starting point would be letter without any in-degrees
We can create a graph with the letters that follows a particular letter 
If there are multiple letters following a letter, we add it at the end of the dependency vector and that will take care of the ordering

Start implementing by getting all the independent letters (indegree = 0) and add it to the queue
Add all the dependents or tge edges to the queue 
Everytime we pop frm the queue, add to the result
*/


class Solution {
public:
    string alienOrder(vector<string>& words) {
        if(words.size() == 0)
            return "";
        // build the indegrees array
        // build the adjacency list (matrix is not usefel - its not bi-directional)
        unordered_map<char, vector<char>> graph;
        vector<int> indegrees (26, 0); // initialise all letters to 0
        string result = "";
        
        buildGraph(words, graph, indegrees);
        queue<char> q;

        // push all the nodes with 0 indegrees
        for(auto ve: graph) {
            if (indegrees[ve.first - 'a'] == 0)
                q.push(ve.first);
        }

        // if there are no independent edges, that means there is no solution - return ""
        if(q.size() == 0)
            return "";
        
        // start popping from the queue and adding to result
        while(!q.empty()) {
            vector<char> dependents = graph[q.front()];
            result  += q.front();
            q.pop();

            // push to result only when all the indegrees are 0 - i.e., they are totally independent 
            // to avoid having cycles
            for (char c : dependents) {
                indegrees[c-'a']--;
                if(indegrees[c-'a'] == 0)
                    q.push(c);
            }
        }

        // if there is a cycle, our result would have been smaller than the number of letters in the map
        if(graph.size() != result.length())
            return "";
        
        return result;
    }
private:
    void buildGraph(vector<string>& words, unordered_map<char, vector<char>>& graph, vector<int>& indegrees) {
        // initialise all the vertices with empty edges - important because some might not have edges
        for(string word: words) {
            for(char c:word) {
                if(graph.find(c) == graph.end()) {
                    graph[c] = vector<char>();
                }
            }
        }
        
        for(int i = 0; i<words.size()-1; i++) {
            for(int j = 0; j<words[i].length() && j<words[i+1].length(); j++) {
                char char1 = words[i][j];
                char char2 = words[i+1][j];
                // if one is a substring of the other, no way to figure out the order. So clear the map and return 
                if(words[i].rfind(words[i+1], 0) == 0 && words[i].length() > words[i+1].length()) {
                    graph.clear();
                    return;
                }

                // update the indegrees and the edges in the graph
                if(char1 != char2) {
                    indegrees[char2-'a']++;
                    graph[char1].push_back(char2);
                    break;
                }
            }
        }
    }
};