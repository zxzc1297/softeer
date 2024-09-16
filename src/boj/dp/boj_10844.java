package boj.dp;
import java.util.*;

/**
 * 문제
 * 45656이란 수를 보자.
 *
 * 이 수는 인접한 모든 자리의 차이가 1이다. 이런 수를 계단 수라고 한다.
 *
 * N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구해보자. 0으로 시작하는 수는 계단수가 아니다.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.
 *
 * 출력
 * 첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.
 *
 * 예제 입력 1
 * 1
 * 예제 출력 1
 * 9
 * 예제 입력 2
 * 2
 * 예제 출력 2
 * 17
 */
public class boj_10844 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();  // 계단 수의 길이 N
        sc.close();

        // dp 배열: dp[i][j] = 길이가 i이고 마지막 자리가 j인 계단 수의 개수
        long[][] dp = new long[N + 1][10];
        long MOD = 1_000_000_000;

        // 초기값 설정: 길이가 1인 계단 수
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        // 점화식 적용
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j > 0) dp[i][j] += dp[i - 1][j - 1];  // j-1에서 j로 오는 경우
                if (j < 9) dp[i][j] += dp[i - 1][j + 1];  // j+1에서 j로 오는 경우
                dp[i][j] %= MOD;
            }
        }

        // N자리 계단 수의 개수를 모두 합산
        long result = 0;
        for (int i = 0; i <= 9; i++) {
            result += dp[N][i];
        }
        result %= MOD;

        // 결과 출력
        System.out.println(result);
    }
}
