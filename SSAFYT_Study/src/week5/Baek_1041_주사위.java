package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//주사위
public class Baek_1041_주사위 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		long N = Integer.parseInt(br.readLine());
		int[] dice = new int[6];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 6; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}

		long res = 0;
		if (N == 1) {
			Arrays.sort(dice);
            for (int i = 0; i < 5; i++) {
                res += dice[i];
            }
            
        } else {
			dice[0] = Math.min(dice[0], dice[5]);
			dice[1] = Math.min(dice[1], dice[4]);
			dice[2] = Math.min(dice[2], dice[3]);

			Arrays.sort(dice,0,3);

			long min1 = dice[0]; // 1면
			long min2 = dice[0] + dice[1]; // 2면
			long min3 = dice[0] + dice[1] + dice[2]; // 3면
			
			res += (min1 * ((N - 1) * (N - 2) * 4 + (N - 2) * (N - 2)));
			res += (min2 * ((N - 1) * 4 + (N - 2) * 4));
			res += (min3 * 4); // 3개인 면 4개 고정
		}
		System.out.println(res);
	}
}
