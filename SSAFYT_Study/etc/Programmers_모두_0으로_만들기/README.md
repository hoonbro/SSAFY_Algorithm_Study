# [Level2] 모두 0으로 만들기
## 문제 링크
> https://programmers.co.kr/learn/courses/30/lessons/76503
## 알고리즘 분류
> 위상정렬

## 코드
```java
package etc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Programmers_모두_0으로_만들기 {
    public long solution(int[] a, int[][] edges) {
        long answer = 0;
        int indegree[] = new int[a.length];
        long[] arr = new long[a.length];
        boolean visited[] = new boolean[a.length];
        ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>(); 
        Queue<Integer> q = new LinkedList<>();
        
        
        int sum = 0;
        for(int i = 0; i < a.length; i++){
            sum += a[i];
            arr[i] = a[i];
            al.add(new ArrayList<Integer>());
        }
        //모든 점들의 가중치를 0으로 만드는 것이 불가능
        if(sum != 0)
            return -1;
       
        for(int i = 0; i < edges.length; i++){
            al.get(edges[i][0]).add(edges[i][1]);
            al.get(edges[i][1]).add(edges[i][0]);
            indegree[edges[i][0]]++;
            indegree[edges[i][1]]++;
        }
        
        for(int i = 0; i < indegree.length; i++) {
        	if(indegree[i] == 1)
        		q.offer(i);
        }
        
        while(!q.isEmpty()) {
        	int now = q.poll();
            visited[now] = true;
        	for(int i = 0; i < al.get(now).size(); i++) {
                int next = al.get(now).get(i);
                
                //방향성 그래프가 아니기 때문에 방문 체크
                if(visited[next])
                    continue;
                
        		indegree[next]--;
        		
                arr[next] += arr[now];
                answer += Math.abs(arr[now]);
                arr[now] = 0;
        		
        		if(indegree[next] == 1)
        			q.offer(next);
        	}
        }
        
        //모든 가중치가 0인지 마지막 체크
        for(int i = 0; i < a.length; i++){
            if(arr[i] != 0)
                return -1;
        }
        
        return answer;
    }
}
```
## 문제 풀이
> 오랜만에 다시 풀어보는 위상정렬
> 한번에 풀지 못했고 위상정렬이 어떻게 진행되는지 검색을 통해 확인하고 문제를 풀었다(문제를 꾸준히 풀자!)
* 방향성이 없는 그래프에서의 위상정렬 문제이다.
1. 모든 노드들의 합이 0이 되지 않는다면 답이 아니므로 한번 체크해준다.
2. 방향성이 없는 그래프이기 때문에 인접리스트에 각각 넣어주고 indegree 또한 각각 증가 시켜준다.
3. 마찬가지로 방향성이 없는 그래프이기 때문에 방문체크를 해줬고, 위상정렬을 진행하며 다음값에 현재값을 더해주고 현재값을 0으로 만들어준다.(처음엔 arr배열이 아닌 a배열을 그대로 사용했는데 int를 벗어날 수 있는 숫자가 나와 틀렸었다 -> `숫자의 범위 확인을 잘하자`)
4. 마지막으로 노드들을 확인하며 모든 가중치가 0인지를 체크해준다.
