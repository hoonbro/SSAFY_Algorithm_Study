# [Level3] 이모티콘 할인행사

> https://school.programmers.co.kr/learn/courses/30/lessons/150368
## 알고리즘 분류
> 중복 조합

## 코드
```java
package _202309;

public class Programmers_이모티콘_할인행사 {
    static int userMax = 0, priceMax = 0;

    public static void main(String[] args) {
        //int[][] users = {{40, 10000}, {25, 10000}};
        //int[] emoticons = {7000, 9000};

        int[][] users = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        int[] emoticons = {1300, 1500, 1600, 4900};

        solution(users, emoticons);
    }

    public static int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};

        int[] discounts = new int[emoticons.length];

        combi(users, emoticons, discounts, 0);

        System.out.println(userMax + " "  + priceMax);

        return new int[]{userMax,priceMax};

    }

    public static  void combi(int[][] users, int[] emotions, int[] discounts, int cnt){
        if(cnt == emotions.length){
            calc(users, emotions, discounts);
            return;
        }

        for(int i = 10; i <= 40; i+=10){
            discounts[cnt] = i;

            combi(users, emotions, discounts, cnt+1);
        }
    }

    public static void calc(int[][] users, int[] emotions, int[] discounts){
        int totalUser = 0;
        int totalPrice = 0;

        loop:
        for(int i = 0; i < users.length; i++){
            int percent = users[i][0];
            int minPrice = users[i][1];

            int priceSum = 0;
            for(int j = 0; j <emotions.length; j++){
                if(percent > discounts[j])
                    continue;

                int price = emotions[j] * (100 - discounts[j])  / 100;

                priceSum += price;

                if(priceSum >= minPrice){
                    totalUser++;
                    continue loop;
                }
            }

            totalPrice += priceSum;
        }

        if(totalUser > userMax){
            userMax = totalUser;
            priceMax = totalPrice;
        }else if(totalUser == userMax){
            priceMax = Math.max(priceMax, totalPrice);
        }
    }
}

```

