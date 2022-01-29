# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [2831] 댄스 파티
> https://www.acmicpc.net/problem/2831
## 알고리즘 분류
> 그리디

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Baek_2831_댄스파티 {
	static int N, pairs = 0; 
	static ArrayList<Integer>[] men = new ArrayList[2];
	static ArrayList<Integer>[] women = new ArrayList[2];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());

		for(int i = 0; i < 2; i++) {
			men[i] = new ArrayList<Integer>();
			women[i] = new ArrayList<Integer>();
		}
		
		String[] menArr = br.readLine().split(" ");
		String[] womenArr = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			int menHeight = Integer.parseInt(menArr[i]); 
			int womenHeight = Integer.parseInt(womenArr[i]); 

			if(menHeight < 0)
				men[0].add(menHeight*-1);
			else	
				men[1].add(menHeight);
			
			if(womenHeight < 0)
				women[1].add(womenHeight*-1);
			else	
				women[0].add(womenHeight);
		}
		
		for(int i = 0; i < 2; i++) {
			Collections.sort(men[i]);
			Collections.sort(women[i]);
		}
		
		findPair(0);
		findPair(1);
		
		System.out.println(pairs);
	}
	
	static void findPair(int type) {
		for(int i = 0, j = 0; i < men[type].size() && j < women[type].size();) {
            //0번 타입 : 남자가 크고 여자가 작은 커플
            //1번 타입 : 여자가 크고 남자가 작은 커플
			int tall = type == 0 ? men[type].get(i) : women[type].get(j);
			int small = type == 0 ? women[type].get(j) : men[type].get(i);
			
            //더 큰 쪽(남자 or 여자)의 index를 증가 시킴
			if(tall <= small) {
				if(type == 0)
					i++;
				else
					j++;
				continue;
			}
			
			pairs++;
			i++;
			j++;
		}
	}
}

```

## 문제 풀이
1. 배열리스트를 사용해 선호 이성 유형을 정리한다.
   1. 키 큰 여자를 선호하는 남자
   2. 키 작은 여자를 선호하는 여자
   3. 키 큰 남자를 선호하는 여자
   4. 키 작은 남자를 선호하는 여자
1. 입력을 받으며 쉬운 비교를 위해 위 예시의 1번과 3번을 0번 인덱스의 배열 리스트에 넣고, 2번과 4번을 같은 1번 인덱스에 넣는다.
1. 각 배열리스트를 오름차순으로 정렬한다.
1. findPair 메서드를 사용해 0, 1번 타입의 커플을 찾는다.
   1. 타입에 맞게 큰 사람(tall)과 작은사람(small)을 각각의 배열리스트에서 뽑는다.
   1. 비교하며 tall > small 이라면 맞는 짝이므로 정답을 1증가시키고 각 인덱스를 1씩 증가시킴
   1. tall <= small이라면 큰 사람의 인덱스를 증가시키며 비교
