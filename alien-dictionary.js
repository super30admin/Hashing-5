// Time, Space - O(NK) to build graph, O(1)
/**
 * @param {string[]} words
 * @return {string}
 */

let map;
    
let indegrees;
var alienOrder = function(words) {
    map = {};
    indegrees =  new Array(26).fill(0);
    
    if(words === null || words.length === 0) {
        return ''
    }
    
    let q = [];
    
    buildGraph(words);
    
    for(let ch of Object.keys(map)) {
        if(indegrees[ch.charCodeAt(0) - 'a'.charCodeAt(0)] === 0) {
            q.push(ch);
        }
    }
    let result = '';
    while(q.length !== 0) {
        let cur = q.shift();
        result += cur;
        
        for(let ch of map[cur]) {
            indegrees[ch.charCodeAt(0) - 'a'.charCodeAt(0)]--;
            if( indegrees[ch.charCodeAt(0) - 'a'.charCodeAt(0)] === 0) {
                q.push(ch);
            }
        }
    }
    
    if(result.length !== Object.keys(map).length) {
        return ""
    }
    
    return result;
};

var buildGraph = function(words) {
    for(let i=0;i<words.length;i++) {
        for(let char of words[i]) {
            if(!map[char]) {
                map[char] = new Set();
            }
        }
    }
    for(let i=0;i<words.length - 1;i++) {
        let first = words[i];
        let second = words[i+1];
        
        let m = first.length, n = second.length;
        if(m>n && first.startsWith(second)) {
            map = {};
            return;
        }
        for(let j = 0;j<n&&j<m;j++) {
            let out = first[j];
            let to = second[j];
            if(out!== to) {
                if(!map[out].has(to)) {
                    map[out].add(to);
                    indegrees[to.charCodeAt(0) - 'a'.charCodeAt(0)]++;
                }
                break;
            }
        }
    }
}
