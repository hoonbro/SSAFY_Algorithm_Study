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
