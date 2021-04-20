package week13.추가;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2531회전초밥 {

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int[] belt = new int[N];
      for(int i=0;i<N;i++) {
         belt[i] = Integer.parseInt(br.readLine());
      }
      int MAX =1;
      int[] check = new int[d+1];  // 몇번 초밥 선택 됐는지 확인할 것. ex )2번 선택 ->check[2] ++;
      int cnt =0;
      for(int i=0;i<k;i++) {  //처음 k개만큼의 초밥을 미리 선택한다. 
         check[belt[i]]++;
         if(check[belt[i]] >1) {  //이미 선택했다면 가짓수(cnt)를 증가하지 않는다.
            continue;
         }
         cnt++;  // 선택되지 않은 초밥이라면 가짓수 증가
      }
      if(check[c] ==0) {  // 쿠폰 초밥이 선택되지않았다면 가짓수 증가
         cnt++;
      }
      MAX = cnt;  
      int before =0;
      for(int i=k;i<N+k-1;i++) { // 앞에서 하나 빼고 뒤에서 하나 추가
         
         if(i-k <0) {  // 앞에서하나 뺄때 0번보다 밑에 인덱스가 되버릴 때 보정함.
            before = N-(i-k);
         }
         else { // 그경우 아니면 그냥 첫번쨰 초밥 빼기
            before = i-k;
         }
         
         
         if(belt[before] == belt[i%N]) {  // 뺀초밥이랑 들어온 초밥같으면 밑에 안봄.
            continue;
         }
         
         
      
         check[belt[before]]--;  // 첫번째 초밥 빼기
         check[belt[i%N]]++;    // 새로운 초밥 넣기
         if(check[belt[before]] == 0) {  // 뺀초밥이 1개 였다면 가짓수 -1
            cnt--;
         }
         if(check[belt[i%N]] ==1) {  // 들어온 초밥이 현재 없는 초밥이라면 가짓수 +1
            cnt++;
         }
         
         if(check[c] ==0) {  // 쿠폰 초밥이 가짓수 포함안되있다면 가짓수 +1
            MAX = (MAX > cnt+1) ? MAX : cnt+1;  //맥스
         }
         
         
         MAX = (MAX > cnt) ? MAX : cnt;  // 맥스
      }
      
   
      
      System.out.println(MAX);
   }

}