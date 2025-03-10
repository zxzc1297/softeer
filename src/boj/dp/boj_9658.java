package boj.dp;
import java.util.*;

/**
 * 문제
 * 돌 게임은 두 명이서 즐기는 재밌는 게임이다.
 *
 * 탁자 위에 돌 N개가 있다. 상근이와 창영이는 턴을 번갈아가면서 돌을 가져가며, 돌은 1개, 3개 또는 4개 가져갈 수 있다. 마지막 돌을 가져가는 사람이 게임을 지게 된다.
 *
 * 두 사람이 완벽하게 게임을 했을 때, 이기는 사람을 구하는 프로그램을 작성하시오. 게임은 상근이가 먼저 시작한다.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. (1 ≤ N ≤ 1000)
 *
 * 출력
 * 상근이가 게임을 이기면 SK를, 창영이가 게임을 이기면 CY을 출력한다.
 *
 * 예제 입력 1
 * 6
 * 예제 출력 1
 * SK
 */
public class boj_9658 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String[] dp = new String[N + 1];

        // 초기 값 설정
        dp[1] = "CY"; // 1개 남으면 CY 승
        if (N >= 2) dp[2] = "SK"; // 2개 남으면 SK 승
        if (N >= 3) dp[3] = "CY"; // 3개 남으면 CY 승
        if (N >= 4) dp[4] = "SK"; // 4개 남으면 SK 승

        // DP 점화식 적용
        for (int i = 5; i <= N; i++) {
            if (dp[i - 1].equals("SK") && dp[i - 3].equals("SK") && dp[i - 4].equals("SK")) {
                dp[i] = "CY"; // 모든 경우에서 창영이 이길 수 있도록 강제하면 CY 승
            } else {
                dp[i] = "SK"; // 한 개라도 창영이가 CY가 되면 SK 승
            }
        }

        // 정답 출력
        System.out.println(dp[N]);
        sc.close();
    }
}
