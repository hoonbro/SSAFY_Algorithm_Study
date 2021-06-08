package week16;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Programmers_튜플 {
	public static int[] solution(String s) {
    	Set<String> set = new HashSet<>();
    	boolean[] check = new boolean[100001];
        s = s.substring(2, s.length()-2);
		String[] str = s.split("\\}\\,\\{");
		
		
        Arrays.sort(str, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		});
        
        int[] answer = new int[str.length];
        
        int cnt = 0;
        for(int i = 0; i < str.length; i++) {
        	String[] temp = str[i].split(",");
        	for(int j = 0; j < temp.length; j++) {
        		if(!set.contains(temp[j])) {
        			set.add(temp[j]);
        			answer[cnt++] = Integer.parseInt(temp[j]);
        		}
        	}
        }
        return answer;
    }
}
