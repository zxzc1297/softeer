package boj.dp;
import java.math.BigInteger;
import java.util.*;

/**
 * 문제
 * 2×n 직사각형을 2×1과 2×2 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
 *
 * 아래 그림은 2×17 직사각형을 채운 한가지 예이다.
 *
 *
 *
 * 입력
 * 입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스는 한 줄로 이루어져 있으며, 정수 n이 주어진다.
 *
 * 출력
 * 입력으로 주어지는 각각의 n마다, 2×n 직사각형을 채우는 방법의 수를 출력한다.
 *
 * 제한
 * 0 ≤ n ≤ 250
 * 예제 입력 1
 * 2
 * 8
 * 12
 * 100
 * 200
 * 예제 출력 1
 * 3
 * 171
 * 2731
 * 845100400152152934331135470251
 * 1071292029505993517027974728227441735014801995855195223534251
 */
public class boj_1793 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger[] dp = new BigInteger[251];

        dp[0] = BigInteger.ONE;
        dp[1] = BigInteger.ONE;

        for (int i = 2; i <= 250; i++) {
            dp[i] = dp[i - 1].add(dp[i - 2].multiply(BigInteger.valueOf(2)));
        }

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            System.out.println(dp[n]);
        }

        sc.close();
    }
}
