/*
// Time Complexity :O(N*M)
// Space Complexity : O(N*M)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
*/



#include<iostream>
#include<vector>
#include<unordered_map>
#include<queue>

using namespace std;


class Solution {
    const int alpha_size {26};
    const int asc{97};
    vector<int> inorder;
    unordered_map<int,vector<bool>> umap;
    queue<int> pq;

    void initial(string& s1,string& s2){
        int s1_len = s1.size();
        int s2_len = s2.size();
        int min_len = min(s1_len,s2_len);
        int i = 0;
        for(;i<min_len;++i){
            //if the characters are unequal
            if(s1.at(i)!=s2.at(i)){
                int idx_1 = s1.at(i) - asc;
                int idx_2 = s2.at(i) - asc;
                //adding the presence of the character associated with the index1
                if(umap.find(idx_1) == umap.end()){
                    umap[idx_1] = vector<bool>(alpha_size,false);
                }
                if(umap.find(idx_2) == umap.end()){
                    umap[idx_2] = vector<bool>(alpha_size,false);
                }
                if(umap[idx_1].at(idx_2) == false){
                    umap[idx_1].at(idx_2) = true;
                    //adding inorder of the index2
                    inorder.at(idx_2)++;
                }
                 
                break;
            }
        }
        
        // to take care of residual characters.
        for (char c : s1) {
            int idx = c - asc;
            if (umap.find(idx) == umap.end()) {
                umap[idx] = vector<bool>(alpha_size, false);
            }
        }
        for (char c : s2) {
            int idx = c - asc;
            if (umap.find(idx) == umap.end()) {
                umap[idx] = vector<bool>(alpha_size, false);
            }
        }
        i--;
        if(i == min_len-1 && s1_len>s2_len){
            cout<<"clear"<<endl;
            umap.clear();
            //display(umap);
        }
    }

    void display(vector<int>& v1){
        int sz = v1.size();
        for(int i{};i<sz;++i){
            cout<<char(i+asc)<<v1.at(i)<<" ";
        }
        cout<<endl;
    }

    void display(vector<bool>& v1){
        int sz = v1.size();
        for(int i{};i<sz;++i){
            cout<<char(i+asc)<<v1.at(i)<<" ";
        }
        cout<<endl;
    }

    void display(unordered_map<int,vector<bool>>& u_map){
        for(auto x:u_map){
            cout<<char(x.first+asc)<<" : ";
            display(x.second);
        }
    }

public:
    string alienOrder(vector<string>& words) {

        // initialization of data structures;
        string res{};
        inorder.resize(alpha_size,0);
        int sz = words.size();
        //base case if size is 1
        if(sz == 1){
            string res{};
            vector<bool> tp(alpha_size,true);
            for(auto c:words.at(0)){
                int idx = c-asc;
                if(tp.at(idx)){
                    res+=c;
                    tp.at(idx) = false;
                }
            }
            return res;
        }

        for(int j{};j<sz-1;++j){

            //initial(words.at(i),words.at(i+1));
            //shifting intial here due to edge cases
            string s1 = words.at(j);
            string s2 = words.at(j+1);
            int s1_len = s1.size();
            int s2_len = s2.size();
            int min_len = min(s1_len,s2_len);
            int i = 0;
            for(;i<min_len;++i){
                //if the characters are unequal
                if(s1.at(i)!=s2.at(i)){
                    int idx_1 = s1.at(i) - asc;
                    int idx_2 = s2.at(i) - asc;
                    //adding the presence of the character associated with the index1
                    if(umap.find(idx_1) == umap.end()){
                        umap[idx_1] = vector<bool>(alpha_size,false);
                    }
                    if(umap.find(idx_2) == umap.end()){
                        umap[idx_2] = vector<bool>(alpha_size,false);
                    }
                    if(umap[idx_1].at(idx_2) == false){
                        umap[idx_1].at(idx_2) = true;
                        //adding inorder of the index2
                        inorder.at(idx_2)++;
                    }
                    
                    break;
                }
            }
            
            // to take care of residual characters.
            for (char c : s1) {
                int idx = c - asc;
                if (umap.find(idx) == umap.end()) {
                    umap[idx] = vector<bool>(alpha_size, false);
                }
            }
            for (char c : s2) {
                int idx = c - asc;
                if (umap.find(idx) == umap.end()) {
                    umap[idx] = vector<bool>(alpha_size, false);
                }
            }
            i--;
            if(i == min_len-1 && s1_len>s2_len){
                cout<<"clear"<<endl;
                umap.clear();
                return "";
            }

        }

        //display umap
        //display(umap);

        for(int i{};i<alpha_size;++i){
            if(umap.find(i)!=umap.end() && inorder.at(i)==0){
                pq.push(i);
                inorder.at(i) = -1;
            }
        }
        
        while(!pq.empty()){
            int temp = pq.front();
            //to display character and its array
            //cout<<char(temp+asc)<<endl;
            //display(inorder);
            pq.pop();
            res+=char(temp+asc);
            for(int i{};i<alpha_size;++i){
                if(umap[temp].at(i) == true){
                    inorder.at(i)--;
                    umap[temp].at(i) = false;
                }
            }
            for(int i{};i<alpha_size;++i){
                if(umap.find(i)!=umap.end() && inorder.at(i)==0){
                    pq.push(i);
                    inorder.at(i) = -1;
                }
            }
        }

        // for cycle
        if(res.size()!= umap.size()) return "";
        
        return res;
    }
};