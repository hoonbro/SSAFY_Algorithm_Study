package etc._2022_03;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Programmers_후보키 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
//		String[][] relation = {{"100","100","ryan","music","2"}, {"200","200","apeach","math","2"}, {"300","300","tube","computer","3"}, {"400","400","con","computer","4"}, {"500","500","muzi","music","3"}, {"600","600","apeach","music","2"}};
		String[][] relation = {{"a","1","aaa","c","ng"},{"b","1","bbb","c","g"},{"c","1","aaa","d","ng"},{"d","2","bbb","d","ng"}};
		solution(relation);
	}

	static int N, M, ans = 0;
	static int[] arr;
	static Set<String> set;
	static ArrayList<Integer> keyList;
	
    public static  int solution(String[][] relation) {
        N = relation.length;
        M = relation[0].length;
        keyList = new ArrayList<>();
        
        for(int i = 1; i <= M; i++) {
        	arr = new int[i];
        	dfs(0, 0, 0, i, relation);
        }
        
        System.out.println(ans);
        return ans;
    }
    
    static void dfs(int idx, int key, int cnt, int total, String[][] relation) {
    	if(cnt == total) {
    		for(int k : keyList) {
    			if((k & key) == k)
    				return;
    		}
    		check(total, relation, key);
    		return;
    	}
    	
    	for(int i = idx; i < M; i++) {
    		arr[cnt] = i;
    		dfs(i + 1, (key | (1 << i)), cnt+1, total, relation);
    	}
    }
    
    static void check(int total, String[][] relation, int key) {
    	set = new HashSet<>();
    	StringBuilder sb;
    	for(int i = 0; i < N; i++) {
    		sb = new StringBuilder();
    		for(int j = 0; j < total; j++) {
    			 sb.append(relation[i][arr[j]]).append(" ");
    		}
    		if(set.contains(sb.toString())) {
    			return;
    		}
    		set.add(sb.toString());
    	}
    	
    	ans++;
    	keyList.add(key);
    }
    
    static String makeKey() {
    	StringBuilder sb = new StringBuilder();
    	for(int i : arr) {
    		sb.append(i);
    	}
    	return sb.toString();
    }
    
}
