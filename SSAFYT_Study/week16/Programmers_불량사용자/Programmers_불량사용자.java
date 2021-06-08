package week16;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Programmers_불량사용자 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String u[] = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String b[] = {"fr*d*", "abc1**"};
		solution(u,b);
	}

	static int uLen, bLen;
	static String[] compare, user;
	static boolean[] visited;
	static Set<String> set = new HashSet<>();
	
	public static int solution(String[] user_id, String[] banned_id) {

		uLen = user_id.length;
		bLen = banned_id.length;
		user = user_id;
		compare = new String[bLen];
		visited = new boolean[uLen];
		System.out.println(bLen);
		for (int i = 0; i < bLen; i++) {
			compare[i] = banned_id[i].replace("*", ".");
		}

		dfs(0, "");
		
		return set.size();
	}

	public static void dfs(int idx, String s) {
		if(idx == bLen) {
			StringBuilder sb = new StringBuilder("");
			for(int i = 0; i < uLen; i++) {
				if(s.contains(user[i]))
					sb.append(user[i]);
			}
			set.add(sb.toString());
			return;
		}
		
		for (int i = 0; i < uLen; i++) {
			if (visited[i] && !user[i].matches(compare[idx]))
				continue;

			visited[i] = true;
			dfs(idx + 1, s + " " + user[i]);
			visited[i] = false;
		}
	}
}
