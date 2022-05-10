# [Level1]  표 편집
## 문제 링크
> https://programmers.co.kr/learn/courses/30/lessons/81301
## 알고리즘 분류
> 구현

## 코드
```java
import java.util.Stack;

class Solution {
    public static int solution(String s) {
         s = s.replace("zero", "0");
        s = s.replace("one", "1");
        s = s.replace("two", "2");
        s = s.replace("three", "3");
        s = s.replace("four", "4");
        s = s.replace("five", "5");
        s = s.replace("six", "6");
        s = s.replace("seven", "7");
        s = s.replace("eight", "8");
        s = s.replace("nine", "9");
        
        return Integer.parseInt(s);
    }
}
```
