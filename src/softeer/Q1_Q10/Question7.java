package softeer.Q1_Q10;

import java.util.Scanner;

/*
두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.

제약조건
두 정수 A와 B는 1이상 9이하의 정수이다.

입력형식
첫째 줄에 테스트 케이스의 개수 T가 주어진다.
각 테스트 케이스는 한 줄로 이루어져 있으며, 각 줄에 A와 B가 주어진다.

출력형식
각 테스트 케이스마다 "Case #(테스트 케이스 번호): "를 출력한 다음, A+B를 출력한다.
테스트 케이스 번호는 1부터 시작한다.

입력예제1
5
1 1
2 3
3 4
9 8
5 2
출력예제1
Case #1: 2
Case #2: 5
Case #3: 7
Case #4: 17
Case #5: 7
 */
public class Question7 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int caseAmount = sc.nextInt();

        int[][] list  = new int[caseAmount][2];

        for (int[] caseNum : list ) {
            caseNum[0] = sc.nextInt();
            caseNum[1] = sc.nextInt();
        }

        int index = 0;
        for (int[] caseNum : list ) {
            index++;
            System.out.print("Case #"+index+": ");
            System.out.println(caseNum[0] + caseNum[1]);
        }
        sc.close();
    }
}
