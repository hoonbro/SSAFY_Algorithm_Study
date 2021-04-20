package week13.추가;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3238_이항계수_구하기 {
    static long N, R, T, P;
    static long fac[];
    static long power(long x, long y, long p) {
        long ans = 1;
 
        while(y > 0) {
            if(y % 2 == 1)
                ans = (ans*x)%p;
             
            y = y >>1;
            x =(x*x )% p; 
        }
        return ans;
    }
     
    static long nCr(long n, long r, long p) {
        if(r==0)
            return 1L;
         
        return (fac[(int) n] * power(fac[(int) r], p-2, p) % p * power(fac[(int) (n-r)], p-2, p) % p)% p; 
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        T = Integer.parseInt(br.readLine());
        
        fac = new long[1000001];
        fac[0] =1;
        for(int i = 1; i <P; i++) {
        	fac[i] = (fac[i-1]*i)%P;
        }
         
        long temp, ans = 1;
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Long.parseLong(st.nextToken());
            R = Long.parseLong(st.nextToken());
            P = Long.parseLong(st.nextToken());
            ans = nCr(N, R, P);
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }
}