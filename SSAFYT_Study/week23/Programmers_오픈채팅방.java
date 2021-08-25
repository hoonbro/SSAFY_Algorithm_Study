package week23;

import java.util.*;

public class Programmers_오픈채팅방 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public String[] solution(String[] record) {
        String[] answer = new String[record.length];
        
        Map<String, String> map = new HashMap<>();
        
        for(String r : record){
            String[] str = r.split(" ");
            
            //enter
            if(str[0] == "Enter" || str[0] == "Change") {
            	map.put(str[1], str[2]);
            }
        }
        
        for(int i = 0; i < record.length; i++){
            String[] str = record[i].split(" ");
            
            //enter
            if(str[0] == "Enter") {
            	answer[i] = map.get(str[1]) + "님이 들어왔습니다.";
            }
            
            //leave
            else if(str[0] == "Leave")
            	answer[i] = map.get(str[1]) + "님이 나갔습니다.";
        }
        
        return answer;
    }
}
