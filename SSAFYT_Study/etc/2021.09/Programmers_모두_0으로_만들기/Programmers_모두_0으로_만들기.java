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
