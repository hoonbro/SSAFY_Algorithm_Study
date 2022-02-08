# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [1068] 트리
> https://www.acmicpc.net/problem/1068
## 알고리즘 분류
> 그래프 탐색, DFS

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baek_1068_트리 {
	static int N, D, ans = 0;
	static Node[] nodes;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nodes = new Node[N + 1];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i <= N; i++) {
			nodes[i] = new Node(i);
		}

		int root = 0;
		for (int i = 0; i < N; i++) {
			int parent = Integer.parseInt(st.nextToken());

			if (parent == -1) {
				root = i;
				continue;
			}

			nodes[parent].child.add(nodes[i]);
		}
		D = Integer.parseInt(br.readLine());
		
		if (D != root) {
			deleteNode(root);
			dfs(root);
		}
		System.out.println(ans);
	}

	static void deleteNode(int idx) {
		for(Node child : nodes[idx].child) {
			if(child.idx == D) {
				nodes[idx].child.remove(child);
				return;
			}
			deleteNode(child.idx);
		}
	}

	static void dfs(int idx) {
		if (nodes[idx].child.size() == 0) {
			ans++;
			return;
		}
		
		for(Node child : nodes[idx].child) {
			dfs(child.idx);
		}
	}

	static class Node {
		int idx;
		ArrayList<Node> child = new ArrayList<>();

		public Node(int idx) {
			this.idx = idx;
		}
	}
}

```

## 문제 풀이
1. 인덱스와 자식리스트를 가진 클래스 Node를 생성한다.
1. N개 만큼의 nodes배열을 만든다.
1. 입력이 들어오는 노드들의 부모에 노드를 연결해 트리를 만든다.
1. deleteNode 메서드를 재귀적으로 돌며 삭제할 노드(D)를 삭제해준다.
1. dfs 메서드를 재귀적으로 돌며 자식이 없는 노드를 만날 경우(`leaf Node`) ans를 1씩 증가 시킨다.
1. ans 출력
