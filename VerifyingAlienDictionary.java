//Time Complexity : O(l * n)
//Space Complexity : O(1)
//Did this code run successfully on leetcode : Yes
//Did you face any problem while coding this problem: No

class VerifyingAlienDictionary {
    HashMap<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        this.map = new HashMap<>();
        for(int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }
        for(int i = 0; i < words.length-1; i++) {
            String first = words[i];
            String second = words[i+1];
            if(notSorted(first, second)) return false;
        }
        return true;
    }

    private boolean notSorted(String first, String second) {
        for(int i = 0; i < first.length() && i < second.length(); i++) {
            if(first.charAt(i) != second.charAt(i)) {
                return map.get(first.charAt(i)) > map.get(second.charAt(i));
            }
        }
        return first.length() > second.length();
    }
}