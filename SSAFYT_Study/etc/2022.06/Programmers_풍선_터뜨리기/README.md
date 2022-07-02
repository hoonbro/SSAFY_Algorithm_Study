# [Level3]  풍선 터뜨리기
## 문제 링크
> https://programmers.co.kr/learn/courses/30/lessons/68646
## 알고리즘 분류
> 그리디

## 코드
```java
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
```
