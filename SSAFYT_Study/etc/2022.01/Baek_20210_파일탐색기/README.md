# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/14.svg" width="30"> [20210] 파일 탐색기
> https://www.acmicpc.net/problem/20210
## 알고리즘 분류
> 문자열

## 코드
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	static int N;
	static String[] str;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		str = new String[N];

		for (int i = 0; i < N; i++) {
			str[i] = br.readLine();
		}
		Arrays.sort(str, new Comparator<String>() {

			@Override
			public int compare(String s1, String s2) {
				int len1 = s1.length();
				int len2 = s2.length();
				int i = 0, j = 0;
				System.out.println(s1 + " " + s2);
				for (; i < len1 && j < len2; i++, j++) {
					char c1 = s1.charAt(i);
					char c2 = s2.charAt(j);
					// 숫자인지 체크
					boolean numeric1 = isNum(c1);
					boolean numeric2 = isNum(c2);
					
					// 둘다 숫자
					if (numeric1 && numeric2) {
						// 숫자 앞 0 갯수 세기
						int cnt1 = 0, cnt2 = 0;
						while (i < s1.length() && '0' == s1.charAt(i)) {
							cnt1++;
							i++;
						}
						while (j < s2.length() && '0' == s2.charAt(j)) {
							cnt2++;
							j++;
						}
						i = i >= len1 ? len1 : i;
						j = j >= len2 ? len2 : j;

						// 0을 제외한 숫자
						StringBuilder sb1 = new StringBuilder();
						StringBuilder sb2 = new StringBuilder();
						while (i < s1.length() && '0' <= s1.charAt(i) && s1.charAt(i) <= '9') {
							if ('0' <= s1.charAt(i) && s1.charAt(i) <= '9')
								sb1.append(s1.charAt(i));
							i++;
						}
						while (j < s2.length() && '0' <= s2.charAt(j) && s2.charAt(j) <= '9') {
							if ('0' <= s2.charAt(j) && s2.charAt(j) <= '9')
								sb2.append(s2.charAt(j));
							j++;
						}
						i--;
						j--;
						
						// 0을 제거 했으므로 길이가 긴 것이 더 큰 숫자
						if (sb1.length() > sb2.length())
							return 1;
						if (sb2.length() > sb1.length())
							return -1;
						
						// 길이가 같을 경우 한자리씩 비교하며 두 수 비교
						String num1 = sb1.toString();
						String num2 = sb2.toString();
						for (int a = 0, b = 0; a < num1.length() && b < num2.length(); a++, b++) {
							if (num1.charAt(a) > num2.charAt(b))
								return 1;
							else if (num1.charAt(a) < num2.charAt(b))
								return -1;
						}
						
						// 숫자까지 같다면 0의 갯수가 작은순
						if(cnt1 != cnt2)
							return cnt1 - cnt2;
					}
					// 둘다 문자
					if (!numeric1 && !numeric2) {
						// 같은 캐릭터값이 경우 무시
						c1 = s1.charAt(i);
						c2 = s2.charAt(j);
						if (c1 == c2)
							continue;
						
						boolean isUpper1 = c1 - 'a' < 0 ? true : false;
						boolean isUpper2 = c2 - 'a' < 0 ? true : false;

						int n1 = c1 - 'a' >= 0 ? c1 - 'a' : c1 - 'A';
						int n2 = c2 - 'a' >= 0 ? c2 - 'a' : c2 - 'A';

						// 둘다 대문자 이거나 둘다 소문자
						if ((isUpper1 && isUpper2) || (!isUpper1 && !isUpper2)) {
							return n1 - n2;
						}
						// c1 소문자 && c2대문자
						if (!isUpper1 && isUpper2) {
							// c1,c2가 같은 문자일 경우
							if (n1 == n2)
								return 1;

							// 다른 문자일 경우
							return n1 - n2;
						}
						// c1 대문자 && c2소문자
						if (isUpper1 && !isUpper2) {
							// c1,c2가 같은 문자일 경우
							if (n1 == n2)
								return -1;

							// 다른 문자일 경우
							return n1 - n2;
						}
					}
					
					// o1 문자, o2 숫자
					if (!numeric1 && numeric2) {
						return 1;
					}
					// o1 숫자, o2 문자
					if (numeric1 && !numeric2) {
						return -1;
					}
				}
				// 같은 문자인데 뒤에 다른 문자열이 붙는경우 더 깉 문자열이 뒤로
				if (len1 != i) {
					return 1;
				}
				if (len2 != j) {
					return -1;
				}
				return 0;
			}
		});

		for (String s : str) {
			sb.append(s).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
	}

	static boolean isNum(char c) {
		if ('0' <= c && c <= '9')
			return true;
		return false;
	}
}

```

## 문제 풀이
1. 문제에 나온 규칙을 지키며 naturl sort를 한다.
