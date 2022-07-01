import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Programmers_멀쩡한_사각형 {

    public long solution(int w, int h) {
        long answer = 1;
        
        long a = Math.max(w, h);
        long b = Math.min(w, h);
        long gcd = makeGcd(a, b);
        
        answer = a*b - (a+b - gcd);
        
        return answer;
    }
    
    public long makeGcd(long w, long h){
        if(w%h == 0)
            return h;
        
        return makeGcd(h, w%h);
    }
}
