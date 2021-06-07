package week17;

import java.util.HashMap;
import java.util.Map;

public class Programmers_다단계_칫솔_판매 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	Map<String, Person> map = new HashMap<>();
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int[] answer = new int[enroll.length];

		for(String s : enroll) {
			map.put(s, new Person(null, s, 0));
		}
		
		for(int i = 0; i < referral.length; i++) {
			map.get(enroll[i]).parent = map.get(referral[i]);
		}
		
		for(int i = 0; i < seller.length; i++) {
			map.put(seller[i], calc(amount[i]*100, map.get(seller[i]))); 
		}
		
		for(int i = 0; i <answer.length; i++) {
			answer[i] = map.get(enroll[i]).amount;
		}
		
		return answer;
	}
	
	Person calc(int amount, Person seller) {
		
		int temp = amount / 10;
		seller.amount += amount - temp;
		if(seller.parent != null && temp >=1) {
			map.put(seller.parent.name, calc(temp, seller.parent));
		}
		return seller;
	}
	
	class Person{
		Person parent;
		String name;
		int amount;
		
		public Person(Person parent, String name, int amount) {
			this.parent = parent;
			this.name = name;
			this.amount = amount;
		}
	}

}
