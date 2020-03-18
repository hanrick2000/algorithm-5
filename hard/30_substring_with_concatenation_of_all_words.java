public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || words == null || words.length == 0 || words[0].length() == 0 || s.length() < words.length * words[0].length()) {
            return res;
        }
        int wlen = words[0].length();
        int window = wlen * words.length;
        for (int i = 0; i < wlen; i++) {
            int match = 0;
            Map<String, Integer> map = map(words);
            for (int j = 0; i + j + wlen <= s.length(); j+= wlen) {
                String temp = s.substring(i + j, i + j + wlen);
                Integer count = map.get(temp); 
                if (count != null) {
                    map.put(temp, count - 1);
                    if (count == 1) {
                        match++;
                    }
                } 
                if (j >= window) {
                    temp = s.substring(i + j - window, i + j - window + wlen);
                    count = map.get(temp); 
                    if (count != null) {
                        map.put(temp, count + 1);
                        if (count == 0) {
                            match--;
                        }
                    } 
                }
                if (match == map.size()) {
                    res.add(i + j - window + wlen);
                }
            }
        }
        return res;
    }
    private Map<String, Integer> map(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : words) {
            Integer count = map.get(s);
            if (count == null) {
                map.put(s, 1);
            } else {
                map.put(s, count + 1);
            }
        }
        return map;
    }
}
