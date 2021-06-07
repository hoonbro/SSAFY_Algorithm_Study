package week17;

public class Programmers_행렬_테두리_회전하기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	int[][] map;
	int[] answer;
    public int[] solution(int rows, int columns, int[][] queries) {
        answer = new int[queries.length];
        map = new int[rows+1][columns+1];
        
        int idx = 1;
        for(int i = 1; i <= rows; i++) {
        	for(int j = 1; j <= columns; j++) {
        		map[i][j] = idx++;
        	}
        }
        
        for(int i = 0; i < queries.length; i++) {
        	rotate(i, queries[i][0], queries[i][1], queries[i][2], queries[i][3]);
        }
        return answer;
    }
    
    public void rotate(int idx, int minX, int minY, int maxX, int maxY) {
    	int temp = map[minX][minY];
    	int min = temp;
    	for(int i = minX; i < maxX; i++) {
    		map[i][minY] = map[i+1][minY];
    		min = Math.min(min, map[i][minY]);
    	}
    	for(int i = minY; i < maxY; i++) {
    		map[maxX][i] = map[maxX][i+1];
    		min = Math.min(min, map[maxX][i]);
    	}
    	for(int i = maxX; i > minX; i--) {
    		map[i][maxY] = map[i-1][maxY];
    		min = Math.min(min, map[i][maxY]);
    	}
    	for(int i = maxY; i > minY; i--) {
    		map[minX][i] = map[minX][i-1];
            min = Math.min(min, map[minX][i]);
    	}
    	map[minX][minY+1] = temp;
        answer[idx] = min;
    }
}
