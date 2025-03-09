package boj.dp;
import java.util.*;

/**
 * 문제
 * 정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 7가지가 있다. 합을 나타낼 때는 수를 1개 이상 사용해야 한다.
 *
 * 1+1+1+1
 * 1+1+2
 * 1+2+1
 * 2+1+1
 * 2+2
 * 1+3
 * 3+1
 * 정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고, 정수 n이 주어진다. n은 양수이며 1,000,000보다 작거나 같다.
 *
 * 출력
 * 각 테스트 케이스마다, n을 1, 2, 3의 합으로 나타내는 방법의 수를 1,000,000,009로 나눈 나머지를 출력한다.
 *
 * 예제 입력 1
 * 3
 * 4
 * 7
 * 10
 * 예제 출력 1
 * 7
 * 44
 * 274
 */
public class boj_15988 {
    static final int MOD = 1_000_000_009;
    static int[] dp = new int[1_000_001];  // n 최대값이 1,000,000

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // 테스트 케이스 개수

        // DP 테이블 초기화
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        // dp 배열 미리 채우기 (1 ≤ n ≤ 1,000,000)
        for (int i = 4; i <= 1_000_000; i++) {
            dp[i] = ((dp[i - 1] + dp[i - 2]) % MOD + dp[i - 3]) % MOD;
        }

        // 테스트 케이스 실행
        while (T-- > 0) {
            int n = sc.nextInt();
            System.out.println(dp[n]);  // n을 만들 수 있는 방법의 수 출력
        }

        sc.close();
    }
}
