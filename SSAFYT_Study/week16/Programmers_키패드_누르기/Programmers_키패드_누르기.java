package week16;

public class Programmers_키패드_누르기 {
	public String solution(int[] numbers, String hand) {
    	StringBuilder sb = new StringBuilder();
    	int left = 10;
    	int right = 12;
    	int l_dis, r_dis;
    	int n;
    	for(int i = 0; i < numbers.length; i++) {
    		n = numbers[i];
    		if(n == 1 || n == 4 || n == 7) {
    			sb.append('L');
    			left = n;
    		}
    		else if(n == 3 || n == 6 || n == 9) {
    			sb.append('R');
    			right = n;
    		}
    		else {
    			if(n == 0)
    				n = 11;
    			
    			l_dis = Math.abs(n-left)/3 + Math.abs(n-left)%3;
    			r_dis = Math.abs(n-right)/3 + Math.abs(n-right)%3;
    			
    			if(l_dis > r_dis) {
    				sb.append("R");
    				right = n;
    			}
    			else if(r_dis > l_dis) {
    				sb.append("L");
    				left = n;
    			}
    			else {
    				if(hand.equals("right")) {
    					sb.append("R");
    					right = n;
    				}
    				else {
    					sb.append("L");
    					left = n;
    				}
    			}
    		}
    	}
        return sb.toString();
    }
}
