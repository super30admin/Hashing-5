// time: o(nk), space: o(1)
func alienOrder(words []string) string {
    if words == nil || len(words) == 0 {
        return ""
    }

    adjList, indegrees := buildGraph(words)
    q := []byte{} // o(1) space
    // o(26) - worse case we have all 26 characters to loop over - so o(1) time
    for parent, _ := range adjList {
        parentIdx := parent-'a'
        if indegrees[parentIdx] == 0 {
            q = append(q, parent)
        }
    }
    if len(q) == 0 {
        return ""
    }
    out := new(strings.Builder)
    for len(q) != 0 {
        dq := q[0]
        q = q[1:]
        out.WriteByte(dq)
        
        childSet := adjList[dq]
        for child, _ := range childSet.items {
            childIdx := child-'a'
            indegrees[childIdx]--
            if indegrees[childIdx] == 0 {
                q = append(q, child)
            }
        }
    }
    outStr := out.String()
    if len(outStr) != len(adjList) {
        return ""
    }
    return outStr
}

// time: o(nk)
// space: o(1)
func buildGraph(words []string) (map[byte]*set, []int){
    // { independant : [dependants] }
    // { parent : [edges to childs] }
    // here our parent is the first sorted word
    // and childs are the second chars that depend on parent char to be placed first
    
    graph := map[byte]*set{}
    for i := 0; i < len(words); i++ { // o(n)
        for j := 0; j < len(words[i]); j++ { // o(k)
            graph[words[i][j]] = newSet()
        }
    }
    // time: o(nk)
    
    
    indegrees := make([]int, 26)
    for i := 0; i < len(words)-1; i++ {     // o(n)
        word1 := words[i]
        word2 := words[i+1]
        if strings.HasPrefix(string(word1), string(word2)) && len(word1) != len(word2) {
            return map[byte]*set{}, nil
        }
        // otherwise when there is a diff in characters, word1 char is the parent(independent, has no dependency), and word2 char is the child/dependsOnParent
        for j := 0; j < len(word1) && j < len(word2); j++ {     // o(k)
            w1 := word1[j] // parent / independant node
            w2 := word2[j] // child / depends on parent
            
            if w1 != w2 {
                if !graph[w1].contains(w2){
                    indegrees[w2-'a']++
                    graph[w1].add(w2)
                }
                break
            }
        }
        
    }
    // time: o(2nk) : o(nk)
    // space: o(1) - map and indegrees array will only every store 26 chars
    return graph, indegrees
    
}



type set struct{
    items map[byte]struct{}
}
func newSet() *set { return &set{items: map[byte]struct{}{}} }
func (s *set) add(x byte){ s.items[x] = struct{}{} }
func (s *set) remove(x byte) { delete(s.items, x)}
func (s *set) contains(x byte) bool { _, ok := s.items[x]; return ok}
