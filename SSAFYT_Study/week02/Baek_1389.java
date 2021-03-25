package week02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//키빈 베이컨의 6단계 법칙
//플로이드 와샬문제
public class Baek_1389 {
	static int[][] relation;
	static int n, m;
	static int min = 987654321;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		relation = new int[n+1][n+1];
		
		//배열 최대값으로 초기화
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(i!=j) 
					relation[i][j] = min;	
			}
		}
		
		//입력
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			relation[a][b] = 1;
			relation[b][a] = 1;
		}
		
		floyd();
		System.out.println(findMinIndex());
		
	}
	
	//플로이드 와샬 알고리즘
	static void floyd() {
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++)
					if(relation[i][j] > relation[i][k] + relation[k][j])
						relation[i][j] = relation[i][k] + relation[k][j];
			}
		}
	}
	
	//합이 가장 작은 인덱스 return
	static int findMinIndex() {
		int index = 0;
		for(int i = 1; i <= n; i++) {
			int total = 0;
			for(int j = 1; j <= n; j++) {
				total += relation[i][j];
			}
			if(min > total) {
				min = total;
				index = i;
			}
		}
		return index;
	}
}
