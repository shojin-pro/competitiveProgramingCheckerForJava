import java.util.*;
import java.io.*;

public class Main {
    static FastScanner sc = new FastScanner(System.in);
    static PrintWriter pw = new PrintWriter(System.out);
    static StringBuilder sb = new StringBuilder();
    static long mod = (long) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        int t = sc.nextInt();
        for(int i = 0; i < t; i++) solve();
        pw.flush();
    }

    public static void solve() {
        int n = sc.nextInt();
        int c = sc.nextInt();
        int[] cnt = new int[c+1];
        HashSet<Integer> set = new HashSet<>();
        boolean cnt1 = false;
        for(int i = 0; i < n; i++){
            int v = sc.nextInt();
            if(v == 1) cnt1 = true;
            set.add(v);
            cnt[v]++;
        }
        if(!cnt1){
            pw.println("No");
            return;
        }
        for(int i = 0; i < c; i++){
            cnt[i+1] += cnt[i];
        }
        for(int v : set){
            int q = (int)Math.sqrt(v);
            for(int i = 2; i <= q; i++){
                boolean chk = true^cnt[v/(i-1)-1] - cnt[v/i-1] > 0;
                //pw.println((v/(i-1)-1) + " " + (v/i-1));
                chk ^= set.contains(i);
                if(!chk){
                    pw.println("No");
                    return;
                }
            }
        }
        pw.println("Yes");
    }
}

class FastScanner {
    private BufferedReader reader = null;
    private StringTokenizer tokenizer = null;

    public FastScanner(InputStream in) {
        reader = new BufferedReader(new InputStreamReader(in));
        tokenizer = null;
    }

    public FastScanner(FileReader in) {
        reader = new BufferedReader(in);
        tokenizer = null;
    }

    public String next() {
        if (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public String nextLine() {
        if (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                return reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken("\n");
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public String[] nextArray(int n) {
        String[] a = new String[n];
        for (int i = 0; i < n; i++)
            a[i] = next();
        return a;
    }

    public int[] nextIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = nextInt();
        return a;
    }

    public long[] nextLongArray(int n) {
        long[] a = new long[n];
        for (int i = 0; i < n; i++)
            a[i] = nextLong();
        return a;
    }
}
