# [Level2] 후보키
## 문제 링크
> https://programmers.co.kr/learn/courses/30/lessons/42890
## 알고리즘 분류
> 부분집합, 비트마스킹

## 코드
```java
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Programmers_후보키 {
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
```
## 문제 풀이

1. relation의 부분 집합들부터 확인해가며 가능한 부분집합들을 정한다.
1. 부분 집합들이 최소성 만족하는지 비트마스킹을 통해 확인한다.
1. 유일성을 만족한다면 유일성을 만족하는지 확인한다.
1. 유일성과 최소성 모두 만족한다면 keyList에 후보키를 등록해주고 다른 부분집합을 모두 탐색한다.
