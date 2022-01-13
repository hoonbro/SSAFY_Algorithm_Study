# [Level2] 2개 이하로 다른 비트
## 문제 링크
> https://programmers.co.kr/learn/courses/30/lessons/77885
## 알고리즘 분류
> 구현

## 코드
```java
package etc;
    public long[] solution(long[] numbers) {
		
        long[] answer = new long[numbers.length];
        
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] %2 == 0){
                answer[i] = numbers[i] + 1;
                continue;
            }
            
            String s = "0" + Long.toBinaryString(numbers[i]);
            StringBuilder binary = new StringBuilder(s);
            
            int zeroIdx = binary.lastIndexOf("0");
            binary.setCharAt(zeroIdx, '1');
            binary.setCharAt(zeroIdx + 1, '0');
            
            answer[i] = Long.parseLong(binary.toString(), 2);
        }
        return answer;
    }
}
```

