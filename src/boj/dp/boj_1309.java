package boj.dp;
import java.util.*;

/**
 * 문제
 * 어떤 동물원에 가로로 두칸 세로로 N칸인 아래와 같은 우리가 있다.
 *
 *
 *
 * 이 동물원에는 사자들이 살고 있는데 사자들을 우리에 가둘 때, 가로로도 세로로도 붙어 있게 배치할 수는 없다. 이 동물원 조련사는 사자들의 배치 문제 때문에 골머리를 앓고 있다.
 *
 * 동물원 조련사의 머리가 아프지 않도록 우리가 2*N 배열에 사자를 배치하는 경우의 수가 몇 가지인지를 알아내는 프로그램을 작성해 주도록 하자. 사자를 한 마리도 배치하지 않는 경우도 하나의 경우의 수로 친다고 가정한다.
 *
 * 입력
 * 첫째 줄에 우리의 크기 N(1≤N≤100,000)이 주어진다.
 *
 * 출력
 * 첫째 줄에 사자를 배치하는 경우의 수를 9901로 나눈 나머지를 출력하여라.
 *
 * 예제 입력 1
 * 4
 * 예제 출력 1
 * 41
 */
public class boj_1309 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        final int MOD = 9901;

        // DP 배열 초기화
        int[][] dp = new int[N + 1][3];
        dp[1][0] = 1; // 사자를 배치하지 않음
        dp[1][1] = 1; // 사자를 왼쪽에 배치
        dp[1][2] = 1; // 사자를 오른쪽에 배치

        // DP 계산
        for (int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
        }

        // 결과 출력
        int result = (dp[N][0] + dp[N][1] + dp[N][2]) % MOD;
        System.out.println(result);

        scanner.close();
    }
}
