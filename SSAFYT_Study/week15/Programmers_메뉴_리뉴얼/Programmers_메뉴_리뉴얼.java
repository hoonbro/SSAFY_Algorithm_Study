package week15;
import java.util.*;
public class Programmers_메뉴_리뉴얼 {
	  int max;
	    char[] arr;
	    boolean[] alpha;
	    ArrayList<String> al;
	    
	    public String[] solution(String[] orders, int[] course) {
	        alpha = new boolean[26];
	        
	        for(int i = 0; i < orders.length; i++){
	            for(int j = 0; j < orders[i].length(); j++){
	                if(!alpha[orders[i].charAt(j) - 'A'])
	                    alpha[orders[i].charAt(j) - 'A'] = true;
	            }
	        }
	        ArrayList<String> result = new ArrayList<>();
	        for(int i = 0; i < course.length; i++){
	            max = 0;
	            al = new ArrayList<>();
	            arr = new char[course[i]];  
	            combi(orders, 0,0,course[i]);
	            
	            for(int j = 0; j < al.size(); j++)
	                result.add(al.get(j));
	        }
	        Collections.sort(result);
	        String[] answer = new String[result.size()];
	        for(int i = 0; i < result.size(); i++){
	            answer[i] = result.get(i);
	        }
	        return answer;
	    }
	    
	    public void combi(String[] orders, int idx, int cnt, int size){
	        if(cnt == size){
	            int c = 0;
	            loop:
	            for(int i = 0; i < orders.length; i++){
	                for(int j = 0; j < size; j++){
	                    if(orders[i].length() < size)
	                        continue loop;
	                    if(!orders[i].contains(String.valueOf(arr[j]))){
	                        continue loop;
	                    }
	                }
	                c++;
	            }
	            if(c >= 2){
	                if(max < c){
	                    max = c;
	                    al.clear();
	                    al.add(new String(arr));
	                }
	                else if(max == c){
	                    al.add(new String(arr));
	                }
	            }
	            return;
	        }
	        
	        for(int i = idx; i < 26; i++){
	            if(!alpha[i])
	                continue;
	            
	            arr[cnt] = (char)(i+'A');
	            combi(orders, i+1, cnt+1, size);
	        }
	    }
}
