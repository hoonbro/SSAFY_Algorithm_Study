package etc._2022_02;

public class Programmers_파괴되지_않은_건물 {

	public static void main(String[] args) {
		int[][] board = { { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 } };
		int[][] skill = { { 1, 0, 0, 3, 4, 4 }, { 1, 2, 0, 2, 3, 2 }, { 2, 1, 0, 3, 1, 2 }, { 1, 0, 1, 3, 3, 1 } };

		System.out.println(solution(board, skill));
	}

	static int xLen, yLen;
	static int[][] arr;

	public static int solution(int[][] board, int[][] skill) {
		int answer = 0;
		xLen = board.length;
		yLen = board[0].length;
		arr = new int[xLen + 1][yLen + 1];

		for (int i = 0; i < skill.length; i++) {
			int degree = skill[i][0] == 1 ? skill[i][5] * -1 : skill[i][5];
			func(skill[i][1], skill[i][2], skill[i][3], skill[i][4], degree);

		}

		for (int i = 0; i < xLen; i++) {
			for (int j = 1; j < yLen; j++) {
				arr[i][j] += arr[i][j - 1];
			}
		}

		for (int j = 0; j < yLen; j++) {
			for (int i = 1; i < xLen; i++) {
				arr[i][j] += arr[i - 1][j];
			}
		}

		for (int i = 0; i < xLen; i++) {
			for (int j = 0; j < yLen; j++) {
				if (board[i][j] + arr[i][j] > 0)
					answer++;
			}
		}

		return answer;
	}

	static void func(int x1, int y1, int x2, int y2, int degree) {
		arr[x1][y1] += degree;
		arr[x2 + 1][y1] -= degree;
		arr[x1][y2 + 1] -= degree;
		arr[x2 + 1][y2 + 1] += degree;
	}
}
