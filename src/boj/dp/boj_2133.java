package boj.dp;
import java.util.*;

/**
 * 문제
 * 3×N 크기의 벽을 2×1, 1×2 크기의 타일로 채우는 경우의 수를 구해보자.
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 30)이 주어진다.
 *
 * 출력
 * 첫째 줄에 경우의 수를 출력한다.
 *
 * 예제 입력 1
 * 2
 * 예제 출력 1
 * 3
 */
public class boj_2133 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        if (N % 2 != 0) {
            System.out.println(0);  // 3xN 크기이기 때문에 N이 홀수일 경우 0
            return;
        }

        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[2] = 3;  // 3x2 타일링 경우의 수 초기화

        for (int i = 4; i <= N; i += 2) {
            dp[i] = dp[i - 2] * 3;  // 기본 패턴으로 3가지 방법을 곱함
            for (int j = 4; j <= i; j += 2) {
                dp[i] += dp[i - j] * 2;  // 이전 상태에서 더 많은 경우를 고려
            }
        }

        System.out.println(dp[N]);
        sc.close();
    }
}
