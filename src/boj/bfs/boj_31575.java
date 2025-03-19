package boj.bfs;
import java.util.*;

/**
 * 문제
 * 전날에 비해 비트코인의 시세가 백만원이나 오른 어느 아침, 진우는 거래소에 가서 비트코인을 매도하려고 한다. 현재 비트코인의 시세가 점점 떨어지고 있기 때문에 진우는 최대한 빨리 거래소에 가야 한다.
 *
 * 도시는 가로
 * $N$, 세로
 * $M$ 크기의 격자 모양으로 이루어졌다. 진우는 북서쪽 끝에 있고 거래소는 남동쪽 끝에 있다. 도시의 일부 구역은 공터 또는 도로라서 진우가 지나갈 수 있지만, 어떤 구역은 건물이 있어서 진우가 갈 수 없다.
 *
 * 진우는 최대한 빨리 거래소에 가야 하므로, 동쪽(오른쪽) 또는 남쪽(아래쪽)으로만 이동하여 거래소로 도착할 수 있어야 한다. 진우를 도와 거래소로 갈 수 있는지 구하는 프로그램을 작성하여라. 진우의 현재 위치가 거래소일 수 있다.
 *
 * 입력
 * 첫 번째 줄에 도시의 가로 크기
 * $N$과 세로 크기
 * $M$ (
 * $1 \le N, M \le 300$)이 주어진다.
 *
 * 두 번째 줄부터
 * $M$개의 줄에는 도시의 형태를 나타내는
 * $N$개의 정수가 공백을 사이에 두고 주어진다. 각 칸이 1인 경우 진우가 갈 수 있는 칸을 의미하고 0인 경우 진우가 갈 수 없는 칸을 의미한다.
 *
 * 왼쪽 위의 끝 칸과 오른쪽 아래의 끝 칸은 모두 1이다.
 *
 * 출력
 * 첫 번째 줄에 진우가 거래소로 갈 수 있으면 Yes를, 그렇지 않으면 No를 출력한다.
 *
 * 예제 입력 1
 * 5 4
 * 1 1 1 1 1
 * 0 1 0 0 1
 * 1 0 0 0 1
 * 0 0 0 1 1
 * 예제 출력 1
 * Yes
 */
public class boj_31575 {
    static int N, M;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dx = {1, 0}; // 동, 남
    static int[] dy = {0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 가로 (열 개수)
        M = sc.nextInt(); // 세로 (행 개수)
        grid = new int[M][N];
        visited = new boolean[M][N];

        // 격자 입력
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        sc.close();

        // BFS 실행
        if (bfs()) System.out.println("Yes");
        else System.out.println("No");
    }

    static boolean bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int y = now[0], x = now[1];

            // 도착점 도달 확인
            if (y == M - 1 && x == N - 1) return true;

            // 2방향 탐색 (동, 남)
            for (int d = 0; d < 2; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];

                // 범위 내 & 갈 수 있는 길 & 미방문
                if (ny < M && nx < N && grid[ny][nx] == 1 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    queue.offer(new int[]{ny, nx});
                }
            }
        }
        return false; // 끝까지 도달 못함
    }
}
