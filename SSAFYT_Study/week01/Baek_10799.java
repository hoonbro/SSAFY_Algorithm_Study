package week01;
import java.io.BufferedReader;
import java.io.InputStreamReader;
//쇠막대기
public class Baek_10799 {
	public static void main(String[] args)  throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int total = 0;
		int bar =0;
		
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '(')
				bar++;
			
			else {
				bar--;
				
				if(s.charAt(i-1) == '(') {
					total += bar;
				}
				
				else
					total++;
			}
		}
		
		System.out.println(total);
	}
}
