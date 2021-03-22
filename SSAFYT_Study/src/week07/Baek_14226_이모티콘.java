package week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_14226_이모티콘 {
	static int S, ans = 0;
	static boolean[][] visited; // 현재 길이, clip길이
	static Queue<Emo> q;

	static class Emo {
		int len;
		int clip;
		int time;

		Emo(int len, int clip, int time) {
			this.len = len;
			this.clip = clip;
			this.time = time;
		}
	}

	static void bfs() {
		visited = new boolean[1001][1001];
		q = new LinkedList<>();

		visited[1][0] = true;
		q.offer(new Emo(1, 0, 0));

		while (!q.isEmpty()) {
			Emo emo = q.poll();
			if (emo.len == S) {
				ans = emo.time;
				break;
			}
			
			// 복사
			q.add(new Emo(emo.len, emo.len, emo.time + 1));

			// 붙여넣기
			if (emo.clip != 0 && emo.len + emo.clip< 1001 && !visited[emo.len + emo.clip][emo.clip]) {
				visited[emo.len + emo.clip][emo.clip] = true;
				q.add(new Emo(emo.len + emo.clip, emo.clip, emo.time + 1));
			}

			// 삭제
			if (emo.len > 0 && emo.len-1 < 1001 && !visited[emo.len - 1][emo.clip]) {
				visited[emo.len - 1][emo.clip] = true;
				q.add(new Emo(emo.len - 1, emo.clip, emo.time + 1));
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
		bfs();
		System.out.println(ans);
	}
}
