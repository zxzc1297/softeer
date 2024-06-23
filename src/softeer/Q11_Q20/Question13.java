package softeer.Q11_Q20;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/*
루팡은 배낭을 하나 메고 은행금고에 들어왔다. 금고 안에는 값비싼 금, 은, 백금 등의 귀금속 덩어리가 잔뜩 들어있다. 배낭은 W ㎏까지 담을 수 있다.
각 금속의 무게와 무게당 가격이 주어졌을 때 배낭을 채울 수 있는 가장 값비싼 가격은 얼마인가?

루팡은 전동톱을 가지고 있으며 귀금속은 톱으로 자르면 잘려진 부분의 무게만큼 가치를 가진다.

제약조건
1 ≤ N ≤ 106인 정수

1 ≤ W ≤ 104인 정수

1 ≤ Mi, Pi ≤ 104인 정수

입력형식
첫 번째 줄에 배낭의 무게 W와 귀금속의 종류 N이 주어진다. i + 1 (1 ≤ i ≤ N)번째 줄에는 i번째 금속의 무게 Mi와 무게당 가격 Pi가 주어진다.

출력형식
첫 번째 줄에 배낭에 담을 수 있는 가장 비싼 가격을 출력하라.

입력예제1
100 2
90 1
70 2
출력예제1
170
 */
public class Question13 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int answer = 0;
        int lastPrice = 0;
        int total = sc.nextInt();
        int typeCnt = sc.nextInt();

        HashMap<Integer, Integer> list = new HashMap<>();
        int[] priceList = new int[typeCnt];

        for(int i=0; i<typeCnt; i++){
            int weight = sc.nextInt();
            int price = sc.nextInt();
            priceList[i] = price;
            if(list.containsKey(price)){
                list.put(price, weight + list.get(price));
            } else {
                list.put(price, weight);
            }
        }

        Arrays.sort(priceList);

        for(int i=priceList.length -1; i>=0; i--){
            int getWeight = list.get(priceList[i]);
            int getPrice = priceList[i];

            if(lastPrice != getPrice){
                if(total < getWeight){
                    answer += total * getPrice;
                    break;
                } else {
                    answer += getWeight * getPrice;
                    total -= getWeight;
                }
            }

            lastPrice = getPrice;
        }

        System.out.print(answer);

        sc.close();
    }
}
