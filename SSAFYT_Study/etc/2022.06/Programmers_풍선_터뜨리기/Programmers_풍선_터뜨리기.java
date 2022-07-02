import java.util.*;

public class Programmers_풍선_터뜨리기 {
    public int solution(int[] a) {
        int answer = 2;
        int len = a.length;

        if(len == 1)
            return 1;

        int[] left = new int[len];
        int[] right = new int[len];

        int l = Integer.MAX_VALUE;
        int r = Integer.MAX_VALUE;

        for(int i = 0; i < len; i++){
            l = Math.min(a[i], l);
            left[i] = l;
        }

        for(int i = len-1; i >= 0; i--){
            r = Math.min(a[i], r);
            right[i] = r;
        }



        for(int i = 1; i < len-1; i++){
            if(a[i] > left[i] && a[i] > right[i])
                continue;

            answer++;
        }

        return answer;
    }
}
