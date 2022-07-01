import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Programmers_124_나라의_숫자 {

	public String solution(int n) {
        StringBuilder sb = new StringBuilder();
    
        
        while(n > 0){
            if(n%3 == 0)    {
                sb.append(4);
            }else if(n %3 == 2){
                sb.append(2);
            }else{
                sb.append(1);
            }
            
            n=(n-1)/3;
        }
        
        return sb.reverse().toString();
    }
}
