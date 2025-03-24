package boj.dp;
import java.util.*;

/**
 * 문제
 * 인류의 차세대 인공지능 자원 캐기 로봇인 WOOK은 인간 대신 자원을 캐는 로봇이다. WOOK은 언제나 제한된 범위 내에서 자원을 탐색하며, 왼쪽 위 (1, 1)부터 오른쪽 아래 (N, M)까지 자원을 탐색한다. WOOK은 한 번에 오른쪽 또는 아래쪽으로 한 칸 이동할 수 있으며, 그 외의 방향으로 움직이는 것은 불가능하다. WOOK은 자신이 위치한 (x, y)에 자원이 있는 경우에만 해당 자원을 채취할 수 있다. WOOK이 탐사할 영역에 대한 정보가 주어질 때, WOOK이 탐색할 수 있는 자원의 최대 숫자를 구해라!
 *
 * 입력
 * 첫째 줄에 WOOK이 탐사할 영역의 세로길이 N(1≤N≤300)과 가로길이 M(1≤M≤300)이 주어진다. 그 다음 N행 M열에 걸쳐 탐사영역에 대한 정보가 주어진다. 숫자는 공백으로 구분된다. (자원은 1, 빈 땅은 0으로 표시된다)
 *
 * 출력
 * WOOK이 수집할 수 있는 최대 광석의 개수를 출력하라.
 *
 * 예제 입력 1
 * 5 4
 * 0 1 0 0
 * 0 0 1 0
 * 1 1 0 0
 * 1 0 1 0
 * 1 1 0 0
 * 예제 출력 1
 * 4
 */
public class boj_14430 {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt(); // 세로 길이
    int M = sc.nextInt(); // 가로 길이
    int[][] map = new int[N][M]; // 자원 지도
    int[][] dp = new int[N][M]; // DP 배열

        for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            map[i][j] = sc.nextInt();
        }
    }

    // DP 초기값 설정
    dp[0][0] = map[0][0];

    // 첫 번째 행 초기화
        for (int j = 1; j < M; j++) {
        dp[0][j] = dp[0][j - 1] + map[0][j];
    }

    // 첫 번째 열 초기화
        for (int i = 1; i < N; i++) {
        dp[i][0] = dp[i - 1][0] + map[i][0];
    }

    // DP 배열 채우기
        for (int i = 1; i < N; i++) {
        for (int j = 1; j < M; j++) {
            dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + map[i][j];
        }
    }

        System.out.println(dp[N - 1][M - 1]); // 최종 결과 출력
        sc.close();
}
}
