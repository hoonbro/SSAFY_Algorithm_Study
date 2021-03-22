package week01;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//최단경로, 다익스트라 문제
//https://hsp1116.tistory.com/42 다익스트라 참고 사이트
public class Baek_1753 {
	static int V,E,K;
	static int INF = Integer.MAX_VALUE;
	static ArrayList<ArrayList<value>> al;
	static int[] distance;
	
	//객체를 비교할때는 compareable을 사용해야한다.
	//객체는 비교할 대상이 없기 때문에 비교할 대상을 만들어줘야 한다.
	static class value implements Comparable<value>{
		int to;
		int weight;
		
		value(int from, int weight){
			this.to = from;
			this.weight = weight;
		}
		
        public int compareTo(value o){
            return weight - o.weight;
        }
	}
	
	//다익스트라 알고리즘(최단경로, 한정점에서 모든 정점)
	static void dijkstra() {
		//우선순위 큐를 사용하여 구현(최소 heap);
		//큐를 사용하게 되면 시간초과가 나온다.
		//우선순위 큐를 사용해 최소거리를 우선으로 확인하며 불필요한 계산을 줄인다.
		PriorityQueue<value> pq = new PriorityQueue<>();
		pq.add(new value(K, 0));
		distance[K] = 0;
		
		while(!pq.isEmpty()) {
			int now_val = pq.peek().to;
			int now_wei = pq.poll().weight;
			
			for(int i = 0; i < al.get(now_val).size(); i++) {
				int next_val = al.get(now_val).get(i).to;
				int next_wei = al.get(now_val).get(i).weight;
				
				if(distance[next_val] > now_wei + next_wei) {
					distance[next_val] = now_wei + next_wei;
					pq.add(new value(next_val, (now_wei + next_wei)));
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		//2차원 arraylist를 사용한다.
		al = new ArrayList<ArrayList<value>>();
		distance = new int[V+1];
	
		Arrays.fill(distance, INF);
		
		for(int i =1; i <= V+1; i++) {
			al.add(new ArrayList<value>());
		}
		
		//인접행렬로 풀면 메모리 초과 나온다
		//정점 갯수가 2만개여서 2억개의 int배열을 할당하기 때문에 메모리가 터짐
		
//		for(int i = 1; i < V+1; i++) {
//			for(int j = 1; j < V+1; j++) {
//				graph[i][j] = Integer.MAX_VALUE;
//			}
//			distance[i] = Integer.MAX_VALUE;
//		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			al.get(a).add(new value(b, weight));
		}
		
		dijkstra();
		
		for(int i =1; i < distance.length; i++) {
			if(distance[i] == INF)
				System.out.println("INF");
			else
				System.out.println(distance[i]);
		}
	}

}

