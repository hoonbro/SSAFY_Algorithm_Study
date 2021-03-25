# [12865] 평범한배낭
## 분류
> DP

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	static int N, K;
	static Product[] products;
	
	static class Product {
		int weight, value;

		public Product(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		products = new Product[N];
		
		int w, v;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			products[i] = new Product(w, v);
		}
		
		int[] dp = new int[K+1];
		for(int i = 0; i < N; i++) {
			for(int j = K; j-products[i].weight >= 0; j--) {
				if(dp[j] < dp[j - products[i].weight] + products[i].value) {
					dp[j] = dp[j - products[i].weight] + products[i].value;
				}
			}
		}
		System.out.println(dp[K]);
	}
}

```

## 문제 풀이
적어야되는데?
