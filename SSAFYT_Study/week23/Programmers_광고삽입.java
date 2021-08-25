package week23;

public class Programmers_광고삽입 {

	public static void main(String[] args) {
		String a = "99:59:59";
		String b = "25:00:00";
		String[] log = {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"};
		System.out.println(solution(a,b,log));
	}

	public static String solution(String play_time, String adv_time, String[] logs) {
		int play = change(play_time);
		int adv = change(adv_time);

		int[] arr = new int[play + 1];
		for (int i = 0; i < logs.length; i++) {
			String[] log = logs[i].split("-");
			int start = change(log[0]);
			int end = change(log[1]);

			for (int j = start; j <= end; j++) {
				arr[j] += j - start + 1;
				
			}
		}
		int max = 0, result = 0;
		for(int i = 1; i <= play-adv; i++) {
			int cnt = arr[i + adv] - arr[i];
			
			if(cnt > max) {
				max = cnt;
				result = i;
			}
		}
		String answer = String.format("%02d:%02d:%02d", result/3600, (result%3600)/60, result%60); 
		return answer;
	}

	public static int change(String s) {
		String time[] = s.split(":");
		return Integer.parseInt(time[0]) * 3600 + Integer.parseInt(time[1]) * 60 + Integer.parseInt(time[2]);
	}
}
