package week15;

import java.util.Arrays;

public class Programmers_자물쇠와_열쇠 {
	static int l, k, total = 0;
	static int[][] map, tempKey;

	public static boolean solution(int[][] key, int[][] lock) {
		l = lock.length;
		k = key.length;
		tempKey = new int[k][k];
		map = new int[l + 2 * (k - 1)][l + 2 * (k - 1)];
        
        //lock위치 가운데로
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < l; j++)
				if(lock[i][j] == 1) {
					map[k - 1 + i][k - 1 + j] = lock[i][j];
				}
				else {
					map[k - 1 + i][k - 1 + j] = -1;
					total++;
				}
		}

		for (int i = 0; i <= map.length - k; i++) {
			for (int j = 0; j <= map.length - k; j++) {
				loop:
				for (int d = 0; d < 4; d++) {
					key = rotate(key);

					int cnt = 0;
                    //자물쇠로 열 수 있는지 확인
					for (int a = 0; a < k; a++) {
						for (int b = 0; b < k; b++) {
							if (map[i + a][j + b] == 1 && key[a][b] == 1)
								continue loop;
							else if (map[i + a][j + b] == -1 && key[a][b] == 1)
								cnt++;
						}
					}

					if(cnt == total) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public static int[][] rotate(int key[][]) {

		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				tempKey[j][k - i - 1] = key[i][j];
			}
		}
        for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				System.out.print(tempKey[i][j] + " ");
			}
            System.out.println();
		}
        System.out.println();
		return tempKey;
	}
	
}
