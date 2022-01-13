# [Level3] 110 옮기기
## 문제 링크
> https://programmers.co.kr/learn/courses/30/lessons/77886
## 알고리즘 분류
> 구현, Stack 활용

## 코드
```java
package etc;

import java.util.Stack;

public class Programmers_110_옮기기 {
	  public String[] solution(String[] s) {
	        String[] answer = new String[s.length];
	        
	        for(int i = 0; i < s.length; i++){
	            Stack<Character> st = new Stack<>();
	            StringBuilder sb = new StringBuilder();
	            
	            int cnt = 0;
	            for(int j = 0; j < s[i].length(); j++){
	                char n3 = s[i].charAt(j);
	                
	                if(st.size() < 2 || n3 != '0'){
	                    st.push(n3);                    
	                    continue;
	                }
	                
	                char n2 = st.pop();
	                char n1 = st.pop();
	                
	                if(!(n1 == '1' && n2 == '1')){
	                    st.push(n1);
	                    st.push(n2);
	                    st.push(n3);
	                    continue;
	                }
	                
	                cnt++;
	            }
	            boolean flag = false;
	            while(!st.isEmpty()){
	                char now = st.pop();
	                if(now == '1')
	                    sb.append(now);
	                else{
	                    if(!flag){
	                        for(int j = 0; j < cnt; j++){
	                            sb.append("011");
	                        }
	                        flag = true;
	                    }
	                    sb.append(now);
	                }
	            }
	            if(!flag)
	                for(int j = 0; j < cnt; j++){
	                    sb.append("011");
	                }
	            answer[i] = sb.reverse().toString();
	        }
	        return answer;
	    }
}
```
## 문제 풀이
> Stack을 활용해 반대로 생각하며 풀었다.
1. 110이 나올때 마다 cnt를 늘려주고 110이 아닌 것들은 stack에 넣는다.
2. stack에서 하나씩 빼가며 StringBuilder에 넣어주고 처음으로 만나는 0뒤에 cnt 횟수만큼 110을 넣어준다.
