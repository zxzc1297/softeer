package softeer.Q1_Q10;

import java.util.Arrays;
import java.util.Scanner;
/*
남우는 나무를 2개 심으려고 합니다. 나무는 주어진 n개의 위치 중 서로 다른 두 곳에 꼭 심어야만 하며, 1번 위치부터 n번 위치까지 각 위치마다 토양의 비옥함에 해당하는 값 Fi가 주어졌을 때 남우는 나무가 심어지는 두 위치 a, b에서 토양의 비옥함의 곱인 Fa ∗ Fb가 최대가 되도록 나무를 심으려고 합니다. 남우가 적절한 위치에 나무를 심을 수 있도록 하는 프로그램을 작성해보세요.



만약 n이 3이고 다음과 같이 토양의 비옥함이 순서대로 5, -1, 4인 경우 5, 4 위치에 나무를 심으면 비옥함의 곱이 20으로 최대가 됩니다.


제약조건
2 ≤ n ≤ 100
-100 ≤ Fi ≤ 100
입력형식
첫 번째 줄에는 n이 주어집니다.
두 번째 줄에는 각 위치에서의 토양의 비옥함에 해당하는 정보 Fi가 각각 공백을 사이에 두고 주어집니다. Fi가 음수인 경우도 주어질 수 있음에 유의합니다.

출력형식
첫 번째 줄에 가능한 Fa ∗ Fb 값 중 최댓값을 출력합니다. 1 ≤ a, b ≤ n, a ≠ b 를 만족하는 경우 중 최대를 구해야 함에 유의합니다.

입력예제1
2
1 -1
출력예제1
-1
입력예제2
2
-1 -1
출력예제2
1
*/
public class Question6 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int length = sc.nextInt();

        int[] arrays = new int[length];

        for(int i = 0; i < length; i++){
            arrays[i] = sc.nextInt();
        }

        Arrays.sort(arrays);

        System.out.println(Math.max(arrays[0] * arrays[1], arrays[length-2] * arrays[length-1]));

        sc.close();
    }
}
