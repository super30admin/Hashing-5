/*
Time: O(n)
Space: O(n)

- Find edges between letters from order of string
- Do topological sorting
- Group the alphabet into independent ordered sets. 
  Start dfs from first letter of sorted alphabet, find the reachables univisted letters.. Then do the same with next unvisited letter
- Merge the independent ordered sets into one ordered set (based on their lexicographical order)
*/


class Solution {
public:
    /**
     * @param words: a list of words
     * @return: a string which is correct order
     */

    vector<vector<int>> v;
    vector<int> vis;

    bool red = false;

    void get_edge(string &s1, string &s2){
        int i;
        for(i=0; i<min(s1.length(), s2.length()); i++){
            if(s1[i] != s2[i]){
                int a = s1[i] - 'a', b = s2[i] - 'a';
                v[a].push_back(b);
                return;
            }
        }

        //s2 finished early
        if(i == s2.length())  red = true;  
    }

    string merge_strings(vector<string> &v){
        int n = v.size();
        string ret = "";
        vector<int> pos(n, 0);

        while(1){
            int mini = 0;
            bool found = false;
            for(int i=0;i<n;i++){
                if(pos[i] == v[i].length())  continue;

                if(pos[mini] == v[mini].length() || (v[mini][pos[mini]] >= v[i][pos[i]] ))
                    mini = i, found = true;
            }

            //cout<<mini<<" "<<v[mini][pos[mini]]<<endl;

            if(!found)  return ret;
            ret += v[mini][pos[mini]];
            pos[mini]++;
        }

        return ret;
    }

    stack<int> st;

    void top_sort(int u, int p){
        if(vis[u] == 1){
            red = true;
            return;
        }
        if(vis[u]==2){
            return;
        }

        vis[u] = 1;

        for(int adj: v[u]){
            top_sort(adj, u);
        }

        st.push(u);
        vis[u] = 2;
    }

    string str = "";
    void dfs(int u, int p){
        if(vis[u])  return;
        vis[u] = 1;

        str += ('a' + u);

        for(int adj: v[u]){
            if(adj == p)  continue;
            dfs(adj, u);
        }

    }
    
    string alienOrder(vector<string> &words) {
        v = vector<vector<int>>(26, vector<int>());
        vis = vector<int>(26, 0);
        
        for(int i=1;i<words.size();i++)  get_edge(words[i-1], words[i]);
     

        vector<bool> exists(26, 0);
        for(string &w: words){
            for(auto &c: w){
                exists[c-'a'] = 1;
            }
        }

        string sorted = "";

        for(int i=0;i<26;i++){
            if(!exists[i])  continue;

            top_sort(i, -1);
        }
        if(red){
            cout<<"No valid"<<endl;
            return "";
        } 

        vector<int> nodes;

        while(!st.empty()){
            sorted += ('a'+st.top());
            st.pop();
        }
        
        cout<<sorted<<endl;

        vector<string> strs;
        vis = vector<int>(26, 0);
        for(int i=0;i<sorted.length();i++){
            str = "";
            
            int idx = sorted[i] - 'a';
            if(!vis[idx]){
                //cout<<"* "<<sorted[i]<<endl;
                dfs(idx, -1);
                strs.push_back(str);
            }
        }
        //cout<<strs.size()<<endl;
        //return strs[0];

        for(auto &s: strs)  cout<<s<<endl;
        return merge_strings(strs);
    }
};