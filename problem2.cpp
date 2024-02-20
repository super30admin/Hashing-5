/*
// Time Complexity : O(N)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
create hashmap for the order. then compare consecutively if smaller continue onto next word
else if larger return false
if consecutively true then return true;
*/

#include<iostream>
#include<vector>
#include<string>

using namespace std;

class Solution {
    vector<int> hash_map{};
    bool foo(string& s1,string& s2){
        int l1 = s1.size();
        int l2 = s2.size();
        for(int i{};i<l1 && i<l2;++i){
            //cout<<hash_map.at(s1.at(i)-97)<<" "<<hash_map.at(s2.at(i)-97)<<endl;
            int v1 = hash_map.at(s1.at(i)-97);
            int v2 = hash_map.at(s2.at(i)-97);
            if(v1 == v2){
                continue;
            }
            else if(v1<v2){
                return true;
            }
            else{
                return false;
            } 
        }
        if(l1>l2){
            return false;
        }
        return true;
    }
    void display(vector<int>& v1){
        for(int& v:v1){
            cout<<v<<" ";
        }
        cout<<endl;
    }
public:
    bool isAlienSorted(vector<string>& words, string order) {
        int word_length = words.size();
        if (word_length < 2) return true;
        int sz = order.length();
        hash_map.resize(sz,0);
        for(int i{};i<sz;++i){
            hash_map.at(order.at(i)-97) = i;
        }
        //display(hash_map);
        for(int i{};i<word_length-1;++i){
            if(foo(words.at(i),words.at(i+1)) == false){
                return false;
            }
        }
        return true;
    }
};