public class Solution {
    public String countAndSay(int n) {
      String res = "1";
      while (--n > 0) {
        StringBuilder sb = new StringBuilder();
        char[] resc = res.toCharArray();
        for (int i = 0; i < resc.length; i++) {
            int count = 1;
            while ((i + 1) < resc.length && resc[i] == resc[i + 1]) {
                count++;
                i++;
            }
            sb.append(String.valueOf(count) + String.valueOf(resc[i]));
        }
        res = sb.toString();
      }
      return res;
    }
}
