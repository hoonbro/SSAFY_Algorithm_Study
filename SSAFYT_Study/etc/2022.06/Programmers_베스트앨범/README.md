# [Level3]  베스트 앨범
## 문제 링크
> https://programmers.co.kr/learn/courses/30/lessons/42579
## 알고리즘 분류
> 해시

## 코드
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Programmers_베스트앨범 {

	public static void main(String[] args) {
		String[] genres = { "classic", "pop", "classic", "classic", "pop", "test" };
		int[] plays = { 500, 600, 150, 800, 2500, 100 };
		solution(genres, plays);
	}

	static Map<String, Integer> cnt = new HashMap<>();
	static Map<String, ArrayList<Music>> map = new HashMap<>();

	public static int[] solution(String[] genres, int[] plays) {
		int len = plays.length;

		for (int i = 0; i < len; i++) {
			cnt.put(genres[i], cnt.getOrDefault(genres[i], 0) + plays[i]);
			
			if(!map.containsKey(genres[i]))
				map.put(genres[i], new ArrayList<>());
			
			map.get(genres[i]).add(new Music(plays[i], i));
		}
		
		//속한 노래가 많이 재생된 장르 찾기
		List<String> keyList = new ArrayList<>(cnt.keySet());
		Collections.sort(keyList, (o1, o2) -> (cnt.get(o2) - cnt.get(o1)));
		
		List<Integer> list = new ArrayList<>();
		for(String key : keyList) {
			Collections.sort(map.get(key));
			
			int size = map.get(key).size() >= 2 ? 2 : map.get(key).size();
			
			for(int i = 0; i < size; i++) {
				list.add(map.get(key).get(i).idx);
			}
		}
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		int[] answer = new int[cnt.size()*2];
		
		

		return null;
	}

	static class Music implements Comparable<Music>{
		int plays, idx;

		public Music(int plays, int idx) {
			this.plays = plays;
			this.idx = idx;
		}

		@Override
		public int compareTo(Music o) {
			if(this.plays == o.plays)
				return this.idx - o.idx;
			return o.plays - this.plays;
		}
	}
}
```
