# [Level1] 약수의 개수와 덧셈
## 문제 링크
> https://programmers.co.kr/learn/courses/30/lessons/77884
## 알고리즘 분류
> 구현

## 코드
```java
package etc;

public class Programmers_약수의_개수와_덧셈 {
	public int solution(int left, int right) {
        int answer = 0;
        
        for(int i = left; i <= right; i++){
            int cnt = 0;
            for(int j = 1; j <= i; j++){
                if(i % j == 0)
                    cnt++;
            }
            
            if(cnt % 2 == 0)
                answer += i;
            else
                answer -= i;
        }
        return answer;
    }
}
```

