# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/9.svg" width="30"> [1713] 후보 추천하기
## 문제 링크
> https://www.acmicpc.net/problem/1713
## 알고리즘 분류
> 구현, 시뮬레이션

## 코드
```java
package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Baek_1713_후보_추천하기 {
	static int N, T;
	static int[] record;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		T = Integer.parseInt(br.readLine());
		record = new int[N];
		
		ArrayList<Student> al = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		int n;
		loop:
		for(int i = 0; i < T; i++) {
			n = Integer.parseInt(st.nextToken());
			
			// 게시된 학생이 추천을 받으면 추천수 증가
			for(Student s : al) {
				if(s.num == n) {
					s.cnt++;
					Collections.sort(al);
					continue loop;
				}
			}
			
			// 사진틀이 비어있다면
			if(al.size() == N) {
				al.remove(N-1);
				al.add(new Student(n, 1, i));
			}
			else {
				al.add(new Student(n, 1, i));
			}
			
			Collections.sort(al);
		}
		
		Collections.sort(al, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o1.num - o2.num;
			}
		});
		
		for(Student s : al) {
			System.out.print(s.num + " ");
		}
	}
	
	static class Student implements Comparable<Student>{
		int num, cnt, time;

		public Student(int num, int cnt, int time) {
			this.num = num;
			this.cnt = cnt;
			this.time = time;
		}

		@Override
		public int compareTo(Student o) {
			return o.cnt == this.cnt ? o.time-this.time : o.cnt - this.cnt;
		}
	}
}

```

## 문제 풀이
* 단순한 구현문제이다.
* 배열리스트와 객체의 Comparator를 사용해 문제를 해결했다.
* 게시된 학생이 추천을 받으면 추천수증가, 사진틀이 비어있다면 사진을 게시하고, 사진틀이 가득차 있다면 가장 오래된 학생을 제거해준다.

