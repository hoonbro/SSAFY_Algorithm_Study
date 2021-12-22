package etc._2021_12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Programmers_후보키 {
	static int Len, N;
	static ArrayList<String> al = new ArrayList<>();
	static boolean[] visited;
	
    public int solution(String[][] relation) {
        int answer = 0;
        return answer;
    }
    
    static void subSet(int idx, int cnt, String[][] relation) {
    	if(idx == N) {
    		if(cnt == N)
    			return;
    		
    		String key = "";
    		for(int i = 0; i < N; i++) {
    			if(!visited[i]) {
    				key += i;
    			}
    		}
    		
    		Set<String> set = new HashSet<>();
    		for(int i = 0; i < Len; i++) {
    			StringBuilder sb = new StringBuilder();
    			for(int j = 0; j < N; j++) {
    				if(!visited[j]) {
    					sb.append(relation[i][j]);
    				}
    			}
    			
    			if(set.contains(sb.toString())) {
    				return;
    			}else
    				set.add(sb.toString());
    		}
    		
    		for(String s : al) {
    			int duplicate = s.length();
    			
    			for(int i = 0; i < key.length(); i++){
    				if(s.contains(String.valueOf(key.charAt(i))))
    					duplicate--;
    			}
    			
    			if(duplicate == 0)
    				return;
    		}
    		
    		al.add(key);
    		
    		return;
    	}
    	
    	visited[idx] = true;
    	subSet(idx+1, cnt + 1 ,relation);
    	visited[idx] = false;
    	subSet(idx+1, cnt, relation);
    }
}
