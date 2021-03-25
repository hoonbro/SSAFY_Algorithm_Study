package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_8382_방향전환 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		int startX, endX, startY, endY;
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());
			
			int max = Math.max(Math.abs(startX - endX), Math.abs(startY - endY));
			int min = Math.min(Math.abs(startX - endX), Math.abs(startY - endY));
			
			sb.append("#").append(t).append(" ");
			if(max == min) {
				sb.append(max*2).append("\n");
				continue;
			}
			
			min = max-min;
			max *= 2;
			if(min%2 == 0)
				sb.append(max).append("\n");
			else
				sb.append(max-1).append("\n");
		}
		System.out.println(sb.toString());
	}
}
