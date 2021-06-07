package week17;

public class Programmers_로또의_최고순위와_최저순위 {
	public int[] solution(int[] lottos, int[] win_nums) {
		int cnt = 0, zero = 0;
		for (int i = 0; i < lottos.length; i++) {
			for (int j = 0; j < win_nums.length; j++) {
				if (lottos[i] == 0) {
					zero++;
					break;
				}
				if (lottos[i] == win_nums[j]) {
					cnt++;
				}
			}
		}

		int high = 7 - (cnt + zero) > 6 ? 6 : 7 - (cnt + zero);
		int low = cnt < 2 ? 6 : 7 - cnt;
		return new int[] { high, low };
	}
}
