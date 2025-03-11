package boj.dp;
import java.util.*;

/**
 * 문제
 * 박스 나누기 게임은 두 박스를 이용해서 하는 게임이다.
 *
 * 처음에 한 박스에는 돌이 N개, 다른 박스에는 돌이 M개 들어있다. 두 사람은 턴을 번갈아가면서 게임을 진행한다.
 *
 * 각 사람은 박스를 하나 선택하고, 박스안에 들어있는 돌을 모두 비운다. 그 다음, 다른 박스에 들어있는 돌을 적절히 두 박스로 분배한다. 이때, 두 박스에는 돌이 적어도 1개 이상 있어야 한다.
 *
 * 두 박스에 돌을 각각 1개씩 만드는 사람이 게임을 이기게 된다.
 *
 * N과 M이 주어진다. 두 사람이 게임을 완벽하게 했을 때, 이기는 사람을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N과 M이 주어진다. (1 ≤ N, M ≤ 100, N과 M이 모두 1인 경우는 없다)
 *
 * 출력
 * 게임을 먼저 시작한 사람이 게임을 이기면 A를, 그 다음에 시작한 사람이 게임을 이기면 B를 출력한다.
 *
 * 예제 입력 1
 * 1 2
 * 예제 출력 1
 * A
 */
public class boj_11867 {
    static Boolean[][] dp = new Boolean[101][101];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.close();

        System.out.println(canWin(N, M) ? "A" : "B");
    }

    public static boolean canWin(int N, int M) {
        if (N == 1 && M == 1) return false; // (1,1)은 패배 상태
        if (dp[N][M] != null) return dp[N][M]; // 이미 계산된 경우

        boolean win = false;

        // N을 비우고 M을 나누기
        for (int a = 1; a < M; a++) {
            int b = M - a;
            if (b >= 1 && !canWin(Math.max(a, b), Math.min(a, b))) {
                win = true;
            }
        }

        // M을 비우고 N을 나누기
        for (int a = 1; a < N; a++) {
            int b = N - a;
            if (b >= 1 && !canWin(Math.max(a, b), Math.min(a, b))) {
                win = true;
            }
        }

        return dp[N][M] = win;
    }
}
