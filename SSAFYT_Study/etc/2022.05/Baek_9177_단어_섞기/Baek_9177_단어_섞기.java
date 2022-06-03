import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_9177_단어_섞기{
	static int N, len1, len2, len3;
	static char[] word1, word2, target;
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			word1 = st.nextToken().toCharArray();
			word2 = st.nextToken().toCharArray();
			target = st.nextToken().toCharArray();

			len1 = word1.length;
			len2 = word2.length;
			len3 = target.length;

			visited = new boolean[len1+1][len2+1];

			sb.append("Data set ").append(i).append(": ").append(bfs() ? "yes" : "no").append("\n");
		}

		System.out.println(sb.toString());
	}

	static boolean bfs() {
		Queue<Word> q = new LinkedList<>();
		q.offer(new Word(0, 0, 0));
		visited[0][0] = true;

		while (!q.isEmpty()) {
			Word w = q.poll();

			if (w.idx3 == len3 ) {
				return true;
			}

			if (w.idx1 < len1 && word1[w.idx1] == target[w.idx3] && !visited[w.idx1 + 1][w.idx2]) {
				visited[w.idx1 + 1][w.idx2] = true;
				q.offer(new Word(w.idx1 + 1, w.idx2, w.idx3 + 1));
			}

			if (w.idx2 < len2 && word2[w.idx2] == target[w.idx3] && !visited[w.idx1][w.idx2+1]) {
				visited[w.idx1][w.idx2 + 1] = true;
				q.offer(new Word(w.idx1, w.idx2 + 1, w.idx3 + 1));
			}
		}

		return false;
	}

	static class Word {
		int idx1, idx2, idx3;

		public Word(int idx1, int idx2, int idx3) {
			this.idx1 = idx1;
			this.idx2 = idx2;
			this.idx3 = idx3;
		}
	}
}