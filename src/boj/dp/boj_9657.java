package boj.dp;
import java.util.*;

/**
 * 문제
 * 돌 게임은 두 명이서 즐기는 재밌는 게임이다.
 *
 * 탁자 위에 돌 N개가 있다. 상근이와 창영이는 턴을 번갈아가면서 돌을 가져가며, 돌은 1개, 3개 또는 4개 가져갈 수 있다. 마지막 돌을 가져가는 사람이 게임을 이기게 된다.
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
public class boj_9657 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        boolean[] dp = new boolean[N + 1];

        // 초기값 설정
        dp[1] = true; // SK 승리
        if (N >= 2) dp[2] = false; // CY 승리
        if (N >= 3) dp[3] = true; // SK 승리
        if (N >= 4) dp[4] = true; // SK 승리

        for (int i = 5; i <= N; i++) {
            dp[i] = !(dp[i - 1] && dp[i - 3] && dp[i - 4]);
        }

        System.out.println(dp[N] ? "SK" : "CY");

        sc.close();
    }
}
