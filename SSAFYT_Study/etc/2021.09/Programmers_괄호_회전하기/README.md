# [Level2] 괄호 회전하기
## 문제 링크
> https://programmers.co.kr/learn/courses/30/lessons/76502
## 알고리즘 분류
> 구현

## 코드
```java
package etc;

import java.util.Stack;

public class Programmers_괄호_회전하기 {
    public int solution(String s) {
        char[] arr = s.toCharArray();
        int answer = 0;
        
        if(s.length() %2 != 0)
            return 0;
        
        for(int i = 0; i < s.length(); i++){
            arr = rotate(arr);
            answer += isOk(arr);
        }
        
        return answer;
    }
    
    public char[] rotate(char[] arr){
        char temp = arr[0];
        
        for(int i = 0; i < arr.length-1; i++){
            arr[i] = arr[i+1];
        }
        arr[arr.length-1] = temp;
        
        return arr;
    }
    
    public int isOk(char[] arr){
        Stack<Character> st = new Stack<>();
        for(int i = 0 ; i < arr.length; i++){
            if(arr[i] == ')'){
                if(st.size() == 0 || st.peek() != '(')
                    return 0;
                st.pop();
            }else if(arr[i] == '}'){
                if(st.size() == 0 || st.peek() != '{')
                    return 0;
                st.pop();
            }else if(arr[i] == ']'){
                if(st.size() == 0 || st.peek() != '[')
                    return 0;
                st.pop();
            }else{
                st.push(arr[i]);
            }
        }
        if(st.size() == 0)
            return 1;
        else 
            return 0;
    }
}
```

