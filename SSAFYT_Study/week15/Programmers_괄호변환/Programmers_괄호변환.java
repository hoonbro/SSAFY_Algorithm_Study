package week15;

public class Programmers_괄호변환 {
	public String solution(String p) {    
        return separate(0, p);
    }
    
    public static String separate(int start, String s){
        int cnt1 = 0, cnt2 = 0;
        boolean flag = true;
        for(int i = start; i < s.length(); i++){
            if(s.charAt(i) == '(')
                cnt1++;
            else{
                cnt2++;
                if(cnt2 > cnt1)
                    flag = false;
            }

            if(cnt1 == cnt2){
                //균형잡힌 문자열
                if(flag){
                    System.out.println(s.substring(start, i+1));
                    return s.substring(start, i+1) + separate(i+1, s);
                }
                else{
                    System.out.println(s.substring(start, i+1) + " " + s.substring(i+1, s.length()));
                    return change(s.substring(start, i+1),  s.substring(i+1, s.length()));
                }
                //균형x
            }
        }
        return "";
    }
    
    public static String change(String u, String v){
        String s = "(";
        s += separate(0, v);
        s += ")";
        u = u.substring(1, u.length()-1);
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < u.length(); i++){
            if(u.charAt(i) == ')')
                sb.append('(');
            else
                sb.append(')');
        }
        return s+sb.toString();
    }
}
