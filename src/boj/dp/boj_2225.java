package boj.dp;
import java.util.*;

/**
 * 문제
 * 0부터 N까지의 정수 K개를 더해서 그 합이 N이 되는 경우의 수를 구하는 프로그램을 작성하시오.
 *
 * 덧셈의 순서가 바뀐 경우는 다른 경우로 센다(1+2와 2+1은 서로 다른 경우). 또한 한 개의 수를 여러 번 쓸 수도 있다.
 *
 * 입력
 * 첫째 줄에 두 정수 N(1 ≤ N ≤ 200), K(1 ≤ K ≤ 200)가 주어진다.
 *
 * 출력
 * 첫째 줄에 답을 1,000,000,000으로 나눈 나머지를 출력한다.
 *
 * 예제 입력 1
 * 20 2
 * 예제 출력 1
 * 21
 * 예제 입력 2
 * 6 4
 * 예제 출력 2
 * 84
 */
public class boj_2225 {
    static final int MOD = 1000000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();  // 목표 합
        int K = sc.nextInt();  // 사용 가능한 수의 개수

        int[][] dp = new int[K + 1][N + 1]; //k의 개수를 사용해 n을 만드는 갯수

        // 초기 상태 설정: 하나의 수로 합을 만드는 경우의 수
        for (int i = 0; i <= N; i++) {
            dp[1][i] = 1;
        }

        // DP 테이블 채우기
        for (int i = 2; i <= K; i++) {  // 수의 개수를 늘려가며 계산
            for (int j = 0; j <= N; j++) {  // 목표 합을 늘려가며 계산
                dp[i][j] = dp[i - 1][j];
                if (j > 0) {
                    dp[i][j] = (dp[i][j] + dp[i][j - 1]) % MOD;
                }
            }
        }

        System.out.println(dp[K][N]);
        sc.close();
    }
}
