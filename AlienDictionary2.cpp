class Solution {
public:
    string alienOrder(vector<string>& words) {
        vector<int> indegree(26, 0);
        unordered_map<char, vector<char>> graph;
        unordered_set<char> uniqueChars;

        for (auto word : words) {
            uniqueChars.insert(word.begin(), word.end());
        }

        for (int i=0;i<words.size()-1;i++) {
            string w1 = words[i];
            string w2 = words[i+1];

            if (w1.length() > w2.length() && w1.find(w2) == 0) return "";
            int minLen = min(w1.length(), w2.length());
            for (int i=0;i<minLen;i++) {
                char parent = w1[i];
                char child = w2[i];
                if (parent != child) {
                    graph[parent].push_back(child);
                    indegree[child-'a']++;
                    break;
                }
            } 
        }
        queue<char> q;
        for (auto itr: uniqueChars) {
            if (indegree[itr-'a'] == 0) {
                q.push(itr);
            }
        }

        string answer;
        while (!q.empty()) {
            auto node = q.front();
            q.pop();

            answer+=node;

            for (int i=0;i<graph[node].size();i++) {
                indegree[graph[node][i]-'a']--;
                if (indegree[graph[node][i]-'a'] == 0) {
                    q.push(graph[node][i]);
                }
            }
        }
        return answer.length() < uniqueChars.size() ? "" : answer;
    }
};
