package week15;

public class Programmers_문자열_압축 {
static int ans = 123456789;
    
	public static int solution(String s) {
        if(s.length() == 1)
            return 1;

		for (int i = 1; i <= s.length() / 2; i++) {
			check(s, i);
		}

		return ans;
	}

	static void check(String s, int size) {

		String now = "", next = "", result = "";
		int cnt = 1;
		for (int i = 0; i <= s.length(); i+=size) {
			int start = i;
			int end = i+size;
			if (end > s.length())
				end = s.length();
			now = next;
			next = s.substring(start, end);

			if (now.equals(next)) {
				cnt++;
			} else {
				if(cnt == 1)
					result += now;
				else
					result += String.valueOf(cnt) + now;
				cnt = 1;
			}
		}
		if(cnt == 1)
			result += next;
		else
			result += String.valueOf(cnt) + next;
		
		ans = Math.min(ans, result.length());
	}

}
