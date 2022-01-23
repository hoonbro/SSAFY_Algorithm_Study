# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [2539] 모자이크
> https://www.acmicpc.net/problem/2539
## 알고리즘 분류
> 이분탐색

## 코드
```java
public class Baek_2539_모자이크 {
	static int N, M, total, fault;
	static int max, ans = 1;
	static Pos[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken().trim());
		M = Integer.parseInt(st.nextToken().trim());
		total = Integer.parseInt(br.readLine().trim());
		fault = Integer.parseInt(br.readLine().trim());
		arr = new Pos[fault];
		
		
		for(int i = 0; i < fault; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[i] = new Pos(a, b);
		}
		Arrays.sort(arr);
		
		binarySearch();
		System.out.println(ans);
	}
	
	static void binarySearch() {
		int left = 0;
		int right = N > M ? N : M; 
		
		while(left<=right) {
			int mid = (left + right)/2;
			
			if(check(mid)) {
				ans = mid;
				right = mid-1;
			}else {
				left = mid+1;
			}
		}
	}
	
	static boolean check(int size) {
		int cnt = 0;
		int prev = 0;
		
		for(int i = 0; i < arr.length; i++) {
			Pos now = arr[i];
			
			if(now.x > size)
				return false;
			
			if(prev == 0 || prev + size <= now.y) {
				prev = now.y;
				cnt++;
				
				if(cnt > total)
					return false;
			}
		}
		
		return true;
	}
	
	static class Pos implements Comparable<Pos>{
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pos o) {
			return this.y - o.y;
		}
	}
}
```

## 문제 풀이
1. 행과 열의 최대값이 1,000,000이기 때문에 2차원 배열을 사용하면 공간 복잡도가 매우 높아진다.
1. 좌표 클래스를 선언해두고 잘못 칠해진 칸의 좌표들을 배열 `arr`에 자장한다.
1. 배열 arr을 y기준으로 정렬한다.
1. 이분 탐색을 사용해 색종이의 크기를 판단한 후, check 메서드를 사용해 해당 크기의 색종이로 잘못 칠해진 칸들을 덮을 수 있는지 확인한다.
