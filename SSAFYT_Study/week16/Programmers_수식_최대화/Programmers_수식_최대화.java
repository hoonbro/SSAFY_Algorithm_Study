package week16;

import java.util.ArrayList;

public class Programmers_수식_최대화 {
	ArrayList<Long> num;
	ArrayList<Character> oper;
	boolean[] visited = new boolean[3];
	char[] op = new char[3];
	char[] c = {'+','-','*'};
	long answer = 0;
	
    public long solution(String expression) {
        num = new ArrayList<>();
        oper = new ArrayList<>();
        
        String s = "";
        for(int i = 0; i <expression.length(); i++) {
        	if(expression.charAt(i) >= '0' && expression.charAt(i) <= '9')
        		s += expression.charAt(i);
        	else {
        		num.add(Long.parseLong(s));
        		oper.add(expression.charAt(i));
        		s = "";
        	}
        }
        num.add(Long.parseLong(s));
        dfs(0);
        
        return answer;
    }
    
    public void dfs(int cnt) {
    	if(cnt == 3) {
    		ArrayList<Long> cNum = new ArrayList<Long>(num);
            ArrayList<Character> cOper = new ArrayList<Character>(oper);
            long l = 0;
    		for(int i = 0; i < 3; i++) {
    			for(int j = 0; j < cOper.size(); j++) {
    				if(op[i] != cOper.get(j))
    					continue;
    				
    				if(op[i] == '+') 
    					l = cNum.remove(j) + cNum.remove(j);
    				else if(op[i] == '-')
    					l = cNum.remove(j) - cNum.remove(j);
    				else if(op[i] == '*')
    					l = cNum.remove(j) * cNum.remove(j);
    				
    				cOper.remove(j);
    				cNum.add(j--, l);
    			}
    		}
            answer = Math.max(answer, Math.abs(cNum.get(0)));

    		return;
    	}
    	
    	for(int i = 0; i < 3; i++) {
    		if(!visited[i]) {
    			visited[i] = true;
    			op[cnt] = c[i];
    			dfs(cnt+1);
    			visited[i] = false;
    		}
    	}
    }
}
