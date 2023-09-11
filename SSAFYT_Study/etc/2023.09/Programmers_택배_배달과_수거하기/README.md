# [Level2] 택배 배달과 수거하기

> https://school.programmers.co.kr/learn/courses/30/lessons/150369
## 알고리즘 분류
> 그리디

## 코드
```java
package _2023_09;

public class Programmers_택배_배달과_수거하기 {
    static int userMax = 0, priceMax = 0;

    public static void main(String[] args) {
        int[] deliveries = {1, 0, 2, 0, 1, 0, 2};
        int[] pickups = {0, 2, 0, 1, 0, 2, 0};

        solution(2, 7, deliveries, pickups);
    }

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int d = 0;
        int p = 0;
        for(int i = n-1; i >= 0; i--){
            d += deliveries[i];
            p += pickups[i];

            while(d > 0 || p > 0){
                d -= cap;
                p -= cap;

                answer += (i+1)*2;
            }
        }

        return answer;
    }
}

```

