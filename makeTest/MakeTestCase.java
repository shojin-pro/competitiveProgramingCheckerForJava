import java.util.*;
import java.io.*;

public class MakeTestCase {
    static PrintWriter pw = new PrintWriter(System.out);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int t = 1;
        int N = 30;
        int C = 15;
        pw.println(t);
        pw.println(N + " " + C);
        for(int i = 0; i < N; i++){
            sb.append((int)(Math.random()*(C-1))+1).append(" ");
        }
        pw.println(sb.toString());
        pw.flush();
    }
}