package algo;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by abhinav on 9/6/15.
 */
public class RabinKarpImpl {

    private int R;
    private long Q;
    private int m;
    private long patHash;
    private long RM;
    private String pattern;

    public RabinKarpImpl(String pattern)    {
        this.R = 256;
        this.Q = BigInteger.probablePrime(31, new Random()).longValue();
        this.m = pattern.length();
        this.patHash = hash(pattern);
        this.pattern = pattern;

        //calc RM
        RM = 1;
        for(int i = 1; i < m; i++)  {
             RM = (RM * R) % Q;
        }
    }

    private long hash(String input) {
        long hash = 0;
        for(int i = 0; i < input.length(); i++)   {
            hash =  (hash * R + input.charAt(i)) % Q;
        }
        return hash;
    }

    public int search(String text)  {
        long textHash = hash(text.substring(0, m));

        if((textHash == patHash) && (check(text, pattern, 0))) {
            return 0;
        }

        for(int i = m; i < text.length(); i++)  {
            textHash = (textHash + Q - (text.charAt(i-m) * RM) % Q) % Q;
            textHash = (((textHash * R) % Q) + text.charAt(i)) % Q;

            if(textHash == patHash) {
                if(check(text, pattern, i - m + 1))  {
                    return i-m+1;
                }
            }
        }

        return -1;
    }

/*   public String maxRepeatingSubString(int len, String text)    {
       long textHash = hash(text.substring(0, len));
       HashMap<Long, >
   }*/

    private boolean check (String text, String pattern, int offset) {
        for(int i = 0; i < m; i++)  {
            if(text.charAt(i + offset) != pattern.charAt(i))    {
                 return false;
            }
        }
        return true;
    }

    public static void main(String[] args)  {
       /* String text = args[0];
        String pattern = args[1];*/

        // System.out.println(algo.patternExists("adsadasdasydas", "dasdas"));
        String text = "adsadasdasydas";
        String pattern = "ydas";

        RabinKarpImpl impl = new RabinKarpImpl(pattern);
        int offset = impl.search(text);
        System.out.println("Text    ::" + text);
        System.out.print("Pattern ::");
        for(int i=0; i<offset; i++) {
            System.out.print(".");
        }
    }


}
