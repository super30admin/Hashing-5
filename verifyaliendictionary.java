import java.util.HashMap;

//Time Complexity:O(N*L)
//Space Complexity:O(1)
//First, we store each character’s rank in a hash map using the alien dictionary.
//Then we compare each pair of adjacent words to check their order using the rank map.
//If any word comes later but should appear earlier, return false — else everything's good.
public class verifyaliendictionary {
    HashMap<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order)
    {
        this.map = new HashMap<>();
        for(int i=0;i<order.length();i++)
        {
            char c = order.charAt(i);
            map.put(c,i);
        }

        for(int i=0;i<words.length-1;i++)
        {
            String first = words[i];
            String second = words[i+1];

            if(notSorted(first, second)) return false;
        }

        return true;
    }

    private boolean notSorted(String first, String second)
    {
        for(int i=0;i<first.length()&&i<second.length();i++)
        {
            char fChar = first.charAt(i);
            char sChar = second.charAt(i);

            if(fChar != sChar)
            {
                return map.get(fChar)>map.get(sChar);
            }
        }
        return first.length() > second.length();
    }
}
