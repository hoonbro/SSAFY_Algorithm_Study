package week16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Programmers_보석쇼핑 {
	 public int[] solution(String[] gems) {

			Set<String> set = new HashSet<>(); 
			Map<String, Integer> map = new HashMap<>();
			Queue<String> q = new LinkedList<>();

			for (String gem : gems) {
				set.add(gem);
			}
			int size = set.size();
			for (int i = 0; i < size; i++) {
				map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
				q.offer(gems[i]);
			}
			int start= 0, end = gems.length;
			int idx = 0;
			if(size == map.size()) {
				return new int[] {(start+1), (start+q.size())};
			}
			
			for (int i = size; i < gems.length; i++) {
				map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
				q.offer(gems[i]);
				while(true) {
					String str = q.peek();
					if(map.get(str) > 1) {
						map.put(str, map.get(str) -1);
						q.poll();
						idx++;
					}else
						break;
				}
				if(map.size() == size && end > q.size()) {
					end = q.size();
					start = idx;
				}
			}
			
			return new int[] {(start+1), (start+end)};
	    }
}
