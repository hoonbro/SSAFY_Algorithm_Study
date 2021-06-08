package week16;

public class Programmers_징검다리_건너기 {
	public int solution(int[] stones, int k) {
        int answer = 0;
        int left = 1;
        int right =  200000000;
        int mid;
        while(left <= right) {
        	mid = (left + right)/2;
        	if(available(mid, stones, k)) {
        		answer = mid;
        		left = mid+1;
        	}else 
        		right = mid-1;
        }
        return answer+1;
    }
    
    public boolean available(int mid, int[] stones, int k) {
    	int cnt = 0;
    	for(int i : stones) {
    		if(i-mid <= 0) 
    			cnt++;
    		else
    			cnt = 0;
    		
    		if(cnt == k)
    			return false;
    	}
    	return true;
    }
}
