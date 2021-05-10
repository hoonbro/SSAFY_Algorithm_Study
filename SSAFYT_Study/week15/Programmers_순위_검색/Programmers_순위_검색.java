package week15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//https://wellbell.tistory.com/104
public class Programmers_순위_검색 {
	static Map<String, ArrayList<Integer>> map = new HashMap<>();
	static String[] str;
   public int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];
		
		//모든 경우의수 구해두기
		for(int i = 0; i < info.length; i++) {
			str = info[i].split(" ");

			dfs(0, "", Integer.parseInt(str[4]));
		}
		
		for(String s : map.keySet()) {
			Collections.sort(map.get(s));
		}
		
		String[] q;
		String q_str;
		int score;
		for(int i = 0; i <query.length; i++) {
			query[i] = query[i].replace("and ", "");
			q = query[i].split(" ");
			q_str = "";
			
			for(int j = 0; j <4; j++) {
				q_str += q[j];
			}
			
			score = Integer.parseInt(q[4]);
			if(map.containsKey(q_str)) {
                
                ArrayList<Integer> tmp = map.get(q_str);
                int num = binary(tmp,score);
                answer[i] = num;
			}
		}
		return answer;
	}
	
	static int binary(ArrayList<Integer> list, int score) {
		int start = 0;
		int end = list.size();
		int mid;
		while(start < end){
			mid = (start + end) / 2;
			if(list.get(mid) >= score) {
				end = mid;
			}
			
			else {
				start = mid + 1;
			}
		} 
		
		return list.size()-end;
	}
	
	
	static void dfs(int cnt, String s, int score) {
		if(cnt == 4) {
			ArrayList<Integer> al = new ArrayList<>();
			if(map.containsKey(s)) {
				al = map.get(s);
//				map.get(s).add(score);
//				map.put(s, map.get(s));
			}
			al.add(score);
			map.put(s, al);
			return;
		}
		
		dfs(cnt+1, s + str[cnt], score);
		dfs(cnt+1, s + "-", score);
	}
}
