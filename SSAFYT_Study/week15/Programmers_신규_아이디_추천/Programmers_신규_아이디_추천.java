package week15;

public class Programmers_신규_아이디_추천 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	 public String solution(String id) {
 		id = id.toLowerCase();
	
	//2단계
	id = id.replaceAll("[^a-z0-9._-]", "");

	
	//3단계
	id = id.replaceAll("[.]{2,}", ".");
	
	//4단계
	if(id.length()>0) {
		if(id.charAt(0) == '.')
			id = id.substring(1);
		
		if(id.length() > 0 && id.charAt(id.length()-1) == '.') 
			id = id.substring(0, id.length()-1);
	}
	//5단계
	if(id.length() == 0)
		id = "a";
	//6단계
	if(id.length() >=16) {
		id = id.substring(0, 15);
		if(id.length()>0) {
			if(id.charAt(0) == '.')
				id = id.substring(1);
			
			if(id.length() > 0 && id.charAt(id.length()-1) == '.') 
				id = id.substring(0, id.length()-1);
		}
	}
	
	//7단계
	if(id.length() <= 2) {
		String s = String.valueOf(id.charAt(id.length()-1));
		while(id.length() != 3) {
			id += s;
		}
	}
 return id;
}

}
