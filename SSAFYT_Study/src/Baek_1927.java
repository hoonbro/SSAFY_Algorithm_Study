import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

//최소힙 구현
public class Baek_1927 {
	public static class minHeap{
		ArrayList<Integer> heap;
		
		//힙 생성자
		public minHeap() {
			heap = new ArrayList<>();
			// 인덱스 0 채우기(1부터 사용할거임)
			heap.add(0);
		}
		
		//최소힙 입력
		public void insert(int n) {
			heap.add(n);
			int idx = heap.size()-1;
			
			//모든 부모가 자식보다 작을때까지 swap
			while(idx > 1 && heap.get(idx/2) > heap.get(idx)) {
				int temp = heap.get(idx/2);
				heap.set(idx/2, heap.get(idx));
				heap.set(idx, temp);
				idx /= 2;
			}
		}
		
		public void delete() {
			//heap 사이즈 0보다 작으면 0 출력
			if(heap.size() <= 1) {
				System.out.println(0);
				return;
			}
			
			int deleteN = heap.get(1);
			
			//마지막 인덱스값을 인덱스 1에 넣고 마지막 인덱스 삭제
			heap.set(1, heap.get(heap.size()-1));
			heap.remove(heap.size()-1);
			
			//인덱스 1부터 시작하므로
			int idx = 1;
			
			//idx * 2가 존재할 경우 반복, 왼쪽으로 내려갈 것이기 때문에 idx*2
			while(idx*2 < heap.size()) {
				int min = heap.get(idx*2);
				int minIdx = idx*2;
				
				//오른쪽자식이 왼쪽자식보다 작으면 min을 오른쪽 자식으로
				if(idx*2+1 < heap.size() && heap.get(idx*2) > heap.get(idx*2+1)) {
					min = heap.get(idx*2+1);
					minIdx = idx*2+1;
				}
				
				//현재 인덱스가 min보다 작으면 종료
				if(heap.get(idx) < min)
					break;

				//swap
				int temp = heap.get(idx);
				heap.set(idx, heap.get(minIdx));
				heap.set(minIdx, temp);
				idx = minIdx;
			}
			System.out.println(deleteN);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		minHeap heap = new minHeap();
		
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());

			if (x == 0) {
				heap.delete();
				continue;
			}
			
			heap.insert(x);
		}
	}
}
